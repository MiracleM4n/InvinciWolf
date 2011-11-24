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

    void checkConfig() {
        YamlConfiguration config = plugin.iConfig;
        YamlConfigurationOptions configO = config.options();

        checkOption(config, "NotNumb", plugin.notNumber);
        checkOption(config, "NegValue", plugin.negativeInterger);
        checkOption(config, "TooFar", plugin.farAway);
        checkOption(config, "CantFind", plugin.cantFind);
        checkOption(config, "NoPerms", plugin.noPermissions);
        checkOption(config, "WolfTele", plugin.wolfTele);
        checkOption(config, "OwnOffline", plugin.pOffline);
        checkOption(config, "Max_Tele_Radius", plugin.maxTeleportRadius);
        checkOption(config, "Max_Admin_Tele_Radius", plugin.maxAdminTeleportRadius);
        checkOption(config, "Max_Wolves", plugin.maxWolves);

        if (hasChanged) {
            configO.header("InvinciWolf Configuration File, Enjoy!!");

            try {
                config.save(plugin.iConfigF);
            } catch (IOException ignored) {}
        }
    }

    void readConfig() {
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
    }

    void checkOption(YamlConfiguration config, String option, Object defValue) {
        if (!config.isSet(option)) {
            config.set(option, defValue);
            hasChanged = true;
        }
    }
}
