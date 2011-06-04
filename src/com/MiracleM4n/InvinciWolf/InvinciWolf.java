package com.MiracleM4n.InvinciWolf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class InvinciWolf extends JavaPlugin {

	protected final static Logger logger = Logger.getLogger("Minecraft");
	public static final String name = "InvinciWolf";
	IWEntityListener entityListener = new IWEntityListener(this);
	IWPlayerListener playerListener = new IWPlayerListener(this);
	IWCommandExecutor commandexecutor = new IWCommandExecutor(this);
	
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
	    
	//Hash Maps
	public HashMap<Player, Double> wolves = new HashMap<Player, Double>();
	
	public static PermissionHandler Permissions;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY, playerListener, Event.Priority.Highest, this);
		getCommand("getwolves").setExecutor(commandexecutor);
	        
		setupPermissions();
		
		System.out.println("[" + (pdfFile.getName()) + "]" + " version " + 
			pdfFile.getVersion() + " is enabled!");
		
		moveFiles();
		readConfig();
	}
	
	public void moveFiles(){
		getDataFolder().mkdir();
		getDataFolder().setWritable(true);
		getDataFolder().setExecutable(true);
		extractFile("config.yml");
	}
	
	private void extractFile(String name) {
		PluginDescriptionFile pdfFile = getDescription();
		File actual = new File(getDataFolder(), name);
			if (!actual.exists()) {
				InputStream input = getClass().getResourceAsStream("/Config/" + name);
				if (input != null) {
					FileOutputStream output = null;
					try
					{
						output = new FileOutputStream(actual);
						byte[] buf = new byte[8192];
						int length = 0;

						while ((length = input.read(buf)) > 0) {
							output.write(buf, 0, length);
						}
						System.out.println("[" + (pdfFile.getName()) + "]" + "Default file written: " + name);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (input != null)
								input.close();
						} catch (Exception e) {
						} try {
							if (output != null)
								output.close();
						} catch (Exception e) {
						}
					}
				}
			}
		}
	
	public void readConfig() {
		Configuration config = new Configuration(new File(getDataFolder(), "config.yml"));
		config.load();
		
		//Strings
		notNumber  = config.getString("NotNumb", notNumber);
		negativeInterger  = config.getString("NegValue", negativeInterger);
		farAway  = config.getString("TooFar", farAway);
		cantFind  = config.getString("CantFind", cantFind);
		noPermissions  = config.getString("NoPerms", noPermissions);
		wolfTele  = config.getString("WolfTele", wolfTele);
		pOffline  = config.getString("OwnOffline", pOffline);
		
		//Intergers
		maxWolves = config.getInt("Max_Wolves", maxWolves);
		maxTeleportRadius = config.getInt("Max_Tele_Radius", maxTeleportRadius);
		maxAdminTeleportRadius = config.getInt("Max_Admin_Tele_Radius", maxAdminTeleportRadius);
		maxWolvesFix = (maxWolves-1);
	}
	    
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		System.out.println("[InvinciWolf]" + " version " + 
				pdfFile.getVersion() + " is disabled!");
	}
	   	
	private void setupPermissions() 
	{
		Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
			
		if (InvinciWolf.Permissions == null) {
			if (test != null) {
				InvinciWolf.Permissions = ((Permissions)test).getHandler();
				System.out.println("[InvinciWolf] Permissions found hooking in.");
			} else {
				System.out.println("[InvinciWolf] Permissions plugin not found, defaulting to ops.txt.");
			}
		}
	}
}
