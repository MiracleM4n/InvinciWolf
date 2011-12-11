package in.mDev.MiracleM4n.InvinciWolf;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class IWEntityListener extends EntityListener {
    InvinciWolf plugin;
    
    public IWEntityListener(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }
    
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Wolf))
            return;

        if (!(event instanceof EntityDamageByEntityEvent))
            return;

        EntityDamageByEntityEvent subEvent = (EntityDamageByEntityEvent) event;
        Entity attacker = subEvent.getDamager();
        Wolf wolf = (Wolf) event.getEntity();


        if (!wolf.isTamed())
                return;
        if (attacker instanceof Player) {
            Player player = (Player)attacker;

            if (plugin.checkPermissions(player, "invinciwolf.own"))
                if ((attacker) == wolf.getOwner())
                    event.setDamage(0);

        } else if ((attacker instanceof Player)) {
            Player player = (Player)attacker;

            if (!plugin.checkPermissions(player, "invinciwolf.offline"))
                if (wolf.getOwner() == null) {
                    event.setDamage(0);
                    player.sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.pOffline) + ".");
                }
        }
    }
}
