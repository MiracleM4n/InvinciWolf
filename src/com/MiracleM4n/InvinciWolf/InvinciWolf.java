package com.MiracleM4n.InvinciWolf;

	import java.util.logging.Logger;

	import org.bukkit.event.Event;
	import org.bukkit.plugin.Plugin;
	import org.bukkit.plugin.PluginDescriptionFile;
	import org.bukkit.plugin.PluginManager;
	import org.bukkit.plugin.java.JavaPlugin;

	import com.nijiko.permissions.PermissionHandler;
	import com.nijikokun.bukkit.Permissions.Permissions;

	public class InvinciWolf extends JavaPlugin {

	    protected final static Logger logger = Logger.getLogger("Minecraft");
	    public static final String name = "InvinciWolf";
	    IWEntityListener entityListener = new IWEntityListener(this);
	    IWPlayerListener playerListener = new IWPlayerListener(this);
	    IWCommandExecutor commandexecutor = new IWCommandExecutor(this);
	    
	    
	    public static PermissionHandler Permissions;

	    public void onEnable() {
	        PluginManager pm = getServer().getPluginManager();
	        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Highest, this);
	        pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY, playerListener, Event.Priority.Highest, this);
	        
	        getCommand("getwolves").setExecutor(commandexecutor);
	        
	        setupPermissions();
	        
			
			PluginDescriptionFile pdfFile = getDescription();
			System.out.println("[InvinciWolf]" + " version " + 
					pdfFile.getVersion() + " is enabled!");
		}

	    public void onDisable() {
			PluginDescriptionFile pdfFile = getDescription();
			System.out.println("[InvinciWolf]" + " version " + 
					pdfFile.getVersion() + " is disabled!");
	    }
	    
		private void setupPermissions() 
		{
			Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

			if (InvinciWolf.Permissions == null) 
			{
				if (test != null) 
				{
					InvinciWolf.Permissions = ((Permissions)test).getHandler();
					System.out.println("[InvinciWolf] Permissions found hooking in.");
				} 
				
				else 
				{
					System.out.println("[InvinciWolf] Permissions plugin not found, defaulting to ops.txt.");
				}
			}
		}
	}
