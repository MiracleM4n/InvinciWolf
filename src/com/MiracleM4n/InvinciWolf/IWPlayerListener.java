package com.MiracleM4n.InvinciWolf;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
		boolean hastoomany = false;
		Entity e = event.getRightClicked();
		if (player.getItemInHand().getType().equals(Material.BONE)) {
			if (((InvinciWolf.Permissions == null) && player.isOp()) || (InvinciWolf.Permissions != null && InvinciWolf.Permissions.has(player, "invinciwolf.many"))) {
				event.setCancelled(false);
				hastoomany = false;
				return;
			} else {
				if (e instanceof Wolf) {
					for (Entity entity : player.getWorld().getEntities()) {
						if(entity instanceof Wolf) {
							Wolf wolf = (Wolf) entity;
							if (wolf.getOwner() == player) {
								if(wolves > plugin.maxWolvesFix) {
									wolf.setTamed(false);
									wolf.setSitting(false);
									event.setCancelled(true);
									hastoomany = true;
								}
							} else if(wolf.isTamed() == false) {
								if(wolves > plugin.maxWolvesFix) {
									wolf.setTamed(false);
									wolf.setSitting(false);
									event.setCancelled(true);
									hastoomany = true;
								}
							}
							wolves++;
						}
					}
					if(hastoomany) {
						player.sendMessage(ChatColor.RED + "[InvinciWolf] You can't own over " + (plugin.maxWolves) + " wolves.");
					}
				}
			}
		}
	}
}


