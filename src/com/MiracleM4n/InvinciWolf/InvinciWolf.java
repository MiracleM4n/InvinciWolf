package com.MiracleM4n.InvinciWolf;

	import java.util.logging.Level;
	import java.util.logging.Logger;

	import org.bukkit.event.Event;
	import org.bukkit.plugin.PluginManager;
	import org.bukkit.plugin.java.JavaPlugin;

	public class InvinciWolf extends JavaPlugin {

	    protected final static Logger logger = Logger.getLogger("Minecraft");
	    public static final String name = "InvinciWolf";
	    IWEntityListener entityListener = new IWEntityListener(this);

	    public void onEnable() {
	        PluginManager pm = getServer().getPluginManager();
	        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Highest, this);

	        Log("Version " + getDescription().getVersion() + " Enabled");
	    }

	    public void onDisable() {
	        Log("Disabled");
	    }

	    public static void Log(String txt) {
	        logger.log(Level.INFO, String.format("[%s] %s", name, txt));
	    }

	    public static void Log(Level loglevel, String txt) {
	        Log(loglevel, txt, true);
	    }

	    public static void Log(Level loglevel, String txt, boolean sendReport) {
	        logger.log(loglevel, String.format("[%s] %s", name, txt == null ? "" : txt));
	    }

	    public static void Log(Level loglevel, String txt, Exception params) {
	        if (txt == null) {
	            Log(loglevel, params);
	        } else {
	            logger.log(loglevel, String.format("[%s] %s", name, txt == null ? "" : txt), (Exception) params);
	        }
	    }

	    public static void Log(Level loglevel, Exception err) {
	        logger.log(loglevel, String.format("[%s] %s", name, err == null ? "? unknown exception ?" : err.getMessage()), err);
	    }
	}
