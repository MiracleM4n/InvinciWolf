package in.mDev.MiracleM4n.InvinciWolf;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class IWPlayerListener implements Listener {
    InvinciWolf plugin;

    public IWPlayerListener(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (plugin.checkPermissions(player, "invinciwolf.many"))
            return;

        if (!(event.getRightClicked() instanceof Wolf))
            return;

        Integer wolves = 0;
        Boolean hasTooMany = false;

        for (Entity entity : player.getWorld().getEntities()) {
            if(!(entity instanceof Wolf))
                continue;

            Wolf wolf = (Wolf) entity;
            Wolf cWolf = (Wolf) event.getRightClicked();

            if (!wolf.isTamed()) {
                wolf.setSitting(false);
                continue;
            }

            if (wolf.getOwner() != player)
                continue;

            wolves++;

            if(wolves >= plugin.maxWolves) {
                cWolf.setTamed(false);
                cWolf.setSitting(false);
                event.setCancelled(true);
                hasTooMany = true;
            }
        }

        if (hasTooMany)
            player.sendMessage(ChatColor.RED + "[InvinciWolf] You can't own over " + (plugin.maxWolves) + " wolves.");
    }
}


