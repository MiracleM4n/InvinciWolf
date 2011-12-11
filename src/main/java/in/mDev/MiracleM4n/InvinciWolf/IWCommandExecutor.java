package in.mDev.MiracleM4n.InvinciWolf;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class IWCommandExecutor implements CommandExecutor {
    InvinciWolf plugin;

    public IWCommandExecutor(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }

    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = ((Player) sender);

        if (label.equalsIgnoreCase("getwolves")) {
            if(args.length == 0)
                return false;

            if(args.length > 0) {
                Integer radius;
                Integer maxDist;

                try {
                    radius = new Integer(args[0]);
                } catch (NumberFormatException ignored) {
                    player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.notNumber) + ".");
                    return true;
                }

                if (plugin.checkPermissions(player, "invinciwolf.teleadmin"))
                    maxDist =  plugin.maxAdminTeleportRadius;

                else if (plugin.checkPermissions(player, "invinciwolf.teleadmin"))
                    maxDist =  plugin.maxTeleportRadius;

                else {
                    player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.noPermissions) + ".");
                    return true;
                }

                if (radius < 0) {
                    player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.negativeInterger) + ".");
                    return true;
                } else if (radius <= maxDist) {
                    for (Entity entity : (player.getNearbyEntities(radius,radius,radius)))
                        if (entity instanceof Wolf)
                            if((((Wolf) entity).getOwner().equals(player)))
                                if (((Wolf) entity).isSitting()) {
                                    ((Wolf) entity).setSitting(false);
                                    entity.teleport(player);
                                } else
                                    entity.teleport(player);

                        player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.wolfTele) + ".");
                        return true;
                }else if(radius > maxDist) {
                        player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.farAway) + ".");
                        return true;
                } else {
                    player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.farAway) + ".");
                    return true;
                }
            }
        }

        return true;
    }
}
