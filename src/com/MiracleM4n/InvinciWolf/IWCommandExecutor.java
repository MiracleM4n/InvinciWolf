package com.MiracleM4n.InvinciWolf;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Wolf;

public class IWCommandExecutor implements CommandExecutor {
	
	InvinciWolf plugin = null;
	
    public IWCommandExecutor(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }
    
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
				    player.sendMessage(ChatColor.RED + "[InvinciWolf] That is not a number.");
				    return true;
				}
				if ((InvinciWolf.Permissions == null && player.isOp()) || 
						(InvinciWolf.Permissions != null && InvinciWolf.Permissions.has(player, "invinciwolf.tele")) || 
						(InvinciWolf.Permissions != null && InvinciWolf.Permissions.has(player, "invinciwolf.teleadmin"))) {
					if (radius < 0) {
						player.sendMessage(ChatColor.RED + "[InvinciWolf] Cant Use Negative Values.");
						return true; 
					}
					
					if(radius <= 64) {
						for(Entity entity : (player.getNearbyEntities(radius,radius,radius))) {
							if (entity instanceof Wolf) {
								if((((Tameable) entity).getOwner().equals(player)) || (((Wolf) entity).isSitting())) {
									((Wolf) entity).setSitting(false);
									entity.teleport(player);
									player.sendMessage(ChatColor.RED + "[InvinciWolf] Wolves Teleported.");
									return true;
								}
							}
						}
					}
					
					if(radius > 64) {
						if ((InvinciWolf.Permissions == null && player.isOp()) || 
								(InvinciWolf.Permissions != null && InvinciWolf.Permissions.has(player, "invinciwolf.teleadmin"))) {
							for(Entity entitya : (player.getNearbyEntities(radius,radius,radius))) {
								if (entitya instanceof Wolf) {
									if((((Tameable) entitya).getOwner().equals(player)) || (((Wolf) entitya).isSitting())) {
										((Wolf) entitya).setSitting(false);
										entitya.teleport(player);
										player.sendMessage(ChatColor.RED + "[InvinciWolf] Wolves Teleported.");
										return true;
									}
								}
							}
						}
						else{
							player.sendMessage(ChatColor.RED + "[InvinciWolf] Too far away.");
							return true; 
						}
					}
					else {
						return true;
					}
				}
				else {
					player.sendMessage(ChatColor.RED + "[InvinciWolf] You don't have permissions to use this.");
				}
			}
    	}
    	return true; 
	}
}
