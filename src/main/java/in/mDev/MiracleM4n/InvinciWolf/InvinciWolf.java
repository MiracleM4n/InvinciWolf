package in.mDev.MiracleM4n.InvinciWolf;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;

public class InvinciWolf extends JavaPlugin {
    PluginDescriptionFile pdfFile;
    PluginManager pm;

    IWEntityListener eListener = null;
    IWPlayerListener pListener = null;
    IWCommandExecutor cExecutor = null;
    IWConfigListener cListener = null;

    YamlConfiguration iConfig = null;
    File iConfigF = null;

    //Strings
    public String notNumber = "That is not a number";
    public String negativeInterger = "Cant Use Negative Values";
    public String farAway = "Too far away";
    public String cantFind = "No Wolves Found";
    public String noPermissions = "You don't have permissions to use this";
    public String wolfTele = "Wolves Teleported";
    public String pOffline = "Wolf's Owner Is Offline Don't Kill Their Wolves";

    //Integers
    public Integer maxTeleportRadius = 64;
    public Integer maxAdminTeleportRadius = 128;
    public Integer maxWolves = 10;

    // Permissions
    public PermissionHandler permissions;
    Boolean permissionsB = false;

    // GroupManager
    public AnjoPermissionsHandler gmPermissions;
    Boolean gmPermissionsB = false;

    public void onEnable() {
        pdfFile = getDescription();
        pm = getServer().getPluginManager();

        iConfigF = new File(getDataFolder(), "config.yml");
        iConfig = YamlConfiguration.loadConfiguration(iConfigF);

        setupPerms();

        eListener = new IWEntityListener(this);
        pListener = new IWPlayerListener(this);
        cExecutor = new IWCommandExecutor(this);
        cListener = new IWConfigListener(this);

        cListener.checkConfig();
        cListener.readConfig();

        pm.registerEvent(Event.Type.ENTITY_DAMAGE, eListener, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY, pListener, Event.Priority.Highest, this);

        getCommand("getwolves").setExecutor(cExecutor);

        System.out.println("[" + (pdfFile.getName()) + "]" + " version " + pdfFile.getVersion() + " is enabled!");
    }

    public void onDisable() {
        System.out.println("[" + (pdfFile.getName()) + "]" + " version " + pdfFile.getVersion() + " is disabled!");
    }

    void setupPerms() {
        Plugin permTest;

        permTest = pm.getPlugin("Permissions");
        if (permTest != null) {
            permissions = ((Permissions) permTest).getHandler();
            permissionsB = true;
            log("[" + pdfFile.getName() + "] " + permTest.getDescription().getName() + " v" +  (permTest.getDescription().getVersion()) + " found hooking in.");
            return;
        }

        permTest = pm.getPlugin("GroupManager");
        if (permTest != null) {
            gmPermissionsB = true;
            log("[" + pdfFile.getName() + "] " + permTest.getDescription().getName() + " v" +  (permTest.getDescription().getVersion()) + " found hooking in.");
            return;
        }

       log("[" + pdfFile.getName() + "] No Permissions plugins were found defaulting to permissions.yml/SuperPerms Plugin.");
    }

    void log(Object loggedObject) {
        getServer().getConsoleSender().sendMessage(loggedObject.toString());
    }

    Boolean checkPermissions(Player player, String node) {
        if (permissionsB)
            if (permissions.has(player, node))
                return true;

        if (gmPermissionsB)
            if (gmPermissions.has(player, node))
                return true;

        return player.hasPermission(node) || player.isOp();

    }

    @SuppressWarnings("unused")
    Boolean checkPermissions(Player player, String node, Boolean useOp) {
        if (permissionsB)
            if (permissions.has(player, node))
                return true;

        if (gmPermissionsB)
            if (gmPermissions.has(player, node))
                return true;

        if (useOp)
            return player.isOp();

        return player.hasPermission(node);

    }
}
