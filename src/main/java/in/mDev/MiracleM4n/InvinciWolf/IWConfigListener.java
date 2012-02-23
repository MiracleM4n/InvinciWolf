package in.mDev.MiracleM4n.InvinciWolf;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

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

        editOption(config, "NotNumb", "message.notNumber");
        editOption(config, "NegValue", "message.negativeValue");
        editOption(config, "TooFar", "message.tooFar");
        editOption(config, "CantFind", "message.cantFind");
        editOption(config, "NoPerms", "message.noPerm");
        editOption(config, "WolfTele", "message.wolfTele");
        editOption(config, "OwnOffline", "message.ownerOffline");
        editOption(config, "Max_Wolves", "message.maxWolves");

        editOption(config, "Max_Tele_Radius", "int.maxTele");
        editOption(config, "Max_Admin_Tele_Radius", "int.maxAdminTele");

        checkOption(config, "message.notNumber", plugin.notNumber);
        checkOption(config, "message.negativeValue", plugin.negativeInterger);
        checkOption(config, "message.tooFar", plugin.farAway);
        checkOption(config, "message.cantFind", plugin.cantFind);
        checkOption(config, "message.noPerm", plugin.noPermissions);
        checkOption(config, "message.itemCleared", plugin.wolfTele);
        checkOption(config, "message.ownerOffline", plugin.pOffline);
        checkOption(config, "message.maxWolves", plugin.maxWolves);

        checkOption(config, "int.maxTele", plugin.maxTeleportRadius);
        checkOption(config, "int.maxAdminTele", plugin.maxAdminTeleportRadius);

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
        plugin.notNumber = config.getString("message.notNumber");
        plugin.negativeInterger = config.getString("message.negativeValue");
        plugin.farAway = config.getString("message.tooFar");
        plugin.cantFind = config.getString("message.cantFind");
        plugin.noPermissions = config.getString("message.noPerm");
        plugin.wolfTele = config.getString("message.itemCleared");
        plugin.pOffline = config.getString("message.ownerOffline");

        //Integers
        plugin.maxWolves = config.getInt("message.maxWolves");
        plugin.maxTeleportRadius = config.getInt("int.maxTele");
        plugin.maxAdminTeleportRadius = config.getInt("int.maxAdminTele");
    }

    void checkOption(YamlConfiguration config, String option, Object defValue) {
        if (!config.isSet(option)) {
            config.set(option, defValue);
            hasChanged = true;
        }
    }

    void editOption(YamlConfiguration config, String oldOption, String newOption) {
        if (config.isSet(oldOption)) {
            config.set(newOption, config.get(oldOption));
            config.set(oldOption, null);
            hasChanged = true;
        }
    }
}
