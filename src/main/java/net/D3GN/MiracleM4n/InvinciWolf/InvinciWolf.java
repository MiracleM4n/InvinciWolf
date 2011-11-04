package net.D3GN.MiracleM4n.InvinciWolf;

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
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;


public class InvinciWolf extends JavaPlugin {
    PluginDescriptionFile pdfFile = getDescription();
	PluginManager pm = getServer().getPluginManager();

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
	public Integer maxWolvesFix = (maxWolves-1);

    // Permissions
    public PermissionHandler permissions;
    Boolean permissions3;
    Boolean permissionsB = false;

    // GroupManager
    public AnjoPermissionsHandler gmPermissions;
    Boolean gmPermissionsB = false;

    // PermissionsEX
    public PermissionManager pexPermissions;
    Boolean PEXB = false;
	
	public void onEnable() {
		pdfFile = getDescription();
		pm = getServer().getPluginManager();

        iConfigF = new File(getDataFolder(), "config.yml");
        iConfig = YamlConfiguration.loadConfiguration(iConfigF);
	        
		setupPEX();

        eListener = new IWEntityListener(this);
	    pListener = new IWPlayerListener(this);
	    cExecutor = new IWCommandExecutor(this);
	    cListener = new IWConfigListener(this);

        cListener.checkConfig();
        cListener.readConfig();

        pm.registerEvent(Event.Type.ENTITY_DAMAGE, eListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY, pListener, Event.Priority.Highest, this);

        getCommand("getwolves").setExecutor(cExecutor);
		
		System.out.println("[" + (pdfFile.getName()) + "]" + " version " + 
			pdfFile.getVersion() + " is enabled!");
	}
	    
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		System.out.println("[" + (pdfFile.getName()) + "]" + " version " +
			pdfFile.getVersion() + " is disabled!");
	}

    protected void setupPEX() {
        Plugin pexTest = pm.getPlugin("PermissionsEx");

        if (pexTest != null) {
            pexPermissions = PermissionsEx.getPermissionManager();
            PEXB = true;
            log("[" + pdfFile.getName() + "] PermissionsEx " + (pexTest.getDescription().getVersion()) + " found hooking in.");
        } else {
            PEXB = false;
            setupPermissions();
        }
    }

    protected void setupPermissions() {
        Plugin permTest = pm.getPlugin("Permissions");

        if(permTest != null) {
            permissions = ((Permissions) permTest).getHandler();
            permissionsB = true;
            permissions3 = permTest.getDescription().getVersion().startsWith("3");
            log("[" + pdfFile.getName() + "] Permissions " + (permTest.getDescription().getVersion()) + " found hooking in.");
        } else {
            permissionsB = false;
            permissions3 = false;
            setupGroupManager();
        }
    }

    protected void setupGroupManager() {
        Plugin permTest = pm.getPlugin("GroupManager");

        if (permTest != null) {
            gmPermissionsB = true;
            log("[" + pdfFile.getName() + "] GroupManager " + (permTest.getDescription().getVersion()) + " found hooking in.");
        } else {
            gmPermissionsB = false;
            log("[" + pdfFile.getName() + "] No Legacy Permissions plugins were found defaulting to SuperPerms.");
        }
    }

    public void log(String loggedString) {
        getServer().getConsoleSender().sendMessage(loggedString);
    }

    public Boolean checkPermissions(Player player, String node) {
        if (permissionsB)
            if (permissions.has(player, node))
                return true;

        if (gmPermissionsB)
            if (gmPermissions.has(player, node))
                return true;

        if (PEXB)
            if (pexPermissions.has(player, node))
                return true;

        return player.hasPermission(node) || player.isOp();

    }

    @SuppressWarnings("unused")
    public Boolean checkPermissions(Player player, String node, Boolean useOp) {
        if (permissionsB)
            if (permissions.has(player, node))
                return true;

        if (gmPermissionsB)
            if (gmPermissions.has(player, node))
                return true;

        if (PEXB)
            if (pexPermissions.has(player, node))
                return true;

        if (useOp)
            return player.isOp();

        return player.hasPermission(node);

    }
}
