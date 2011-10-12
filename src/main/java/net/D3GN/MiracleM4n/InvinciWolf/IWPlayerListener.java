package net.D3GN.MiracleM4n.InvinciWolf;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerListener;

public class IWPlayerListener extends PlayerListener {
    
	private final InvinciWolf plugin;
	
    public IWPlayerListener(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }
	
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		int wolves = 0;
		boolean hasTooMany = false;
		Entity e = event.getRightClicked();
		if (!plugin.checkPermissions(player, "invinciwolf.many")) {
			if (e instanceof Wolf) {
				for (Entity entity : player.getWorld().getEntities()) {
					if(entity instanceof Wolf) {
						Wolf wolf = (Wolf) entity;
						if (wolf.getOwner() == player) {
							if(wolves > plugin.maxWolvesFix) {
								wolf.setTamed(false);
								wolf.setSitting(false);
								event.setCancelled(true);
								hasTooMany = true;
							}
						} else if(!wolf.isTamed()) {
							wolf.setSitting(false);
						}
						wolves++;
					}
				}
				if (hasTooMany) {
					player.sendMessage(ChatColor.RED + "[InvinciWolf] You can't own over " + (plugin.maxWolves) + " wolves.");
				}
			}
		}
	}
}


