package in.mDev.MiracleM4n.InvinciWolf;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
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

    public void onEnable() {
        pdfFile = getDescription();
        pm = getServer().getPluginManager();

        iConfigF = new File(getDataFolder(), "config.yml");
        iConfig = YamlConfiguration.loadConfiguration(iConfigF);

        eListener = new IWEntityListener(this);
        pListener = new IWPlayerListener(this);
        cExecutor = new IWCommandExecutor(this);
        cListener = new IWConfigListener(this);

        cListener.checkConfig();
        cListener.readConfig();

        pm.registerEvents(eListener, this);
        pm.registerEvents(pListener, this);

        getCommand("getwolves").setExecutor(cExecutor);

        System.out.println("[" + (pdfFile.getName()) + "]" + " version " + pdfFile.getVersion() + " is enabled!");
    }

    public void onDisable() {
        System.out.println("[" + (pdfFile.getName()) + "]" + " version " + pdfFile.getVersion() + " is disabled!");
    }

    void log(Object loggedObject) {
        System.out.println(loggedObject);
    }

    Boolean checkPermissions(Player player, String node) {
        return player.hasPermission(node) || player.isOp();
    }

    Boolean checkPermissions(Player player, String node, Boolean useOp) {
        if (useOp)
            return player.isOp();

        return player.hasPermission(node);

    }
}
