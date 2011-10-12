package net.D3GN.MiracleM4n.InvinciWolf;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.IOException;

public class IWConfigListener {
    InvinciWolf plugin;

    public IWConfigListener(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }

    Boolean hasChanged = false;

    public void checkConfig() {
		PluginDescriptionFile pdfFile = plugin.pdfFile;
        YamlConfiguration config = plugin.iConfig;
        YamlConfigurationOptions configO = config.options();

		//Intergers
		plugin.maxWolves = config.getInt("Max_Wolves", plugin.maxWolves);


		if (config.get("NotNumb") == null) {
			config.set("NotNumb", plugin.notNumber);
			hasChanged = true;
		}
		if (config.get("NegValue") == null) {
			config.set("NegValue", plugin.negativeInterger);
			hasChanged = true;
		}
		if (config.get("TooFar") == null) {
			config.set("TooFar", plugin.farAway);
			hasChanged = true;
		}
		if (config.get("CantFind") == null) {
			config.set("CantFind", plugin.cantFind);
			hasChanged = true;
		}
		if (config.get("NoPerms") == null) {
			config.set("NoPerms", plugin.noPermissions);
			hasChanged = true;
		}
		if (config.get("WolfTele") == null) {
			config.set("WolfTele", plugin.wolfTele);
			hasChanged = true;
		}
		if (config.get("OwnOffline") == null) {
			config.set("OwnOffline", plugin.pOffline);
			hasChanged = true;
		}
		if (config.get("Max_Tele_Radius") == null) {
			config.set("Max_Tele_Radius", plugin.maxTeleportRadius);
			hasChanged = true;
		}
		if (config.get("Max_Admin_Tele_Radius") == null) {
			config.set("Max_Admin_Tele_Radius", plugin.maxAdminTeleportRadius);
			hasChanged = true;
		}
		if (config.get("Max_Wolves") == null) {
			config.set("Max_Wolves", plugin.maxWolves);
			hasChanged = true;
		}
		if (hasChanged) {
			configO.header("InvinciWolf Configuration File, Enjoy!!");
			System.out.println("[" + pdfFile.getName() + "]" + " config.yml has been updated.");
            try {
                config.save(plugin.iConfigF);
            } catch (IOException ignored) {}
        }
	}

	public void readConfig() {
        YamlConfiguration config = plugin.iConfig;

		//Strings
		plugin.notNumber  = config.getString("NotNumb", plugin.notNumber);
		plugin.negativeInterger  = config.getString("NegValue", plugin.negativeInterger);
		plugin.farAway  = config.getString("TooFar", plugin.farAway);
		plugin.cantFind  = config.getString("CantFind", plugin.cantFind);
		plugin.noPermissions  = config.getString("NoPerms", plugin.noPermissions);
		plugin.wolfTele  = config.getString("WolfTele", plugin.wolfTele);
		plugin.pOffline  = config.getString("OwnOffline", plugin.pOffline);

		//Intergers
		plugin.maxWolves = config.getInt("Max_Wolves", plugin.maxWolves);
		plugin.maxTeleportRadius = config.getInt("Max_Tele_Radius", plugin.maxTeleportRadius);
		plugin.maxAdminTeleportRadius = config.getInt("Max_Admin_Tele_Radius", plugin.maxAdminTeleportRadius);
		plugin.maxWolvesFix = (plugin.maxWolves-1);
	}
}
