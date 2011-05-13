package com.MiracleM4n.InvinciWolf;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class IWCommandExecutor implements CommandExecutor {
	
	private final InvinciWolf plugin;
	
    public IWCommandExecutor(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }
    public Integer noTeleportRadius = 0;
    
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
    	if (!(sender instanceof Player)) {
			return true;
		}
    	Player player = ((Player) sender);
    	if (label.equalsIgnoreCase("getwolves")) {
			if(args.length == 0) {
				return false;
			}
			if(args.length > 0) {
				int radius;
				try {
				    radius = new Integer(args[0]);
				} catch (NumberFormatException e) {
					player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.notNumber) + ".");
				    return true;
				}
				if ((InvinciWolf.Permissions == null && player.isOp()) || 
						(InvinciWolf.Permissions != null && InvinciWolf.Permissions.has(player, "invinciwolf.teleadmin"))) {
					if (radius < noTeleportRadius) {
						player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if(radius <= plugin.maxAdminTeleportRadius) {
						for(Entity entity : (player.getNearbyEntities(radius,radius,radius))) {
							if (entity instanceof Wolf) {
								if((((Wolf) entity).getOwner().equals(player))) {
									if (((Wolf) entity).isSitting()) {
										((Wolf) entity).setSitting(false);
										entity.teleport(player);
									} else {
										entity.teleport(player);
									}
								}
							}
						}
						player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.wolfTele) + ".");
						return true;
					}
					if(radius > plugin.maxAdminTeleportRadius) {
						player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.farAway) + ".");
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.farAway) + ".");
						return true;
					}
				} else if ((InvinciWolf.Permissions == null && (player.isOp() == false)) || 
						(InvinciWolf.Permissions != null && InvinciWolf.Permissions.has(player, "invinciwolf.tele"))) {
					if (radius < noTeleportRadius) {
						player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.negativeInterger) + ".");
						return true; 
					}
					if(radius <= plugin.maxTeleportRadius) {
						for(Entity entity : (player.getNearbyEntities(radius,radius,radius))) {
							if (entity instanceof Wolf) {
								if((((Wolf) entity).getOwner().equals(player))) {
									if (((Wolf) entity).isSitting()) {
										((Wolf) entity).setSitting(false);
										entity.teleport(player);
									} else {
										entity.teleport(player);
									}
								}
							}
						}
						player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.wolfTele) + ".");
						return true;
					}
					if(radius > plugin.maxTeleportRadius) {
						player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.farAway) + ".");
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.cantFind) + ".");
						return true;
					}
				} else {
					player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.noPermissions) + ".");
					return true;
				}
			} else {
				return true;
			}
		}
    	return true; 
	}
}
