package net.D3GN.MiracleM4n.InvinciWolf;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class IWEntityListener extends EntityListener {

	private final InvinciWolf plugin;
    
    public IWEntityListener(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }
    
    public void onEntityDamage(EntityDamageEvent event) {
    	Entity attacker = null;
    	if (event instanceof EntityDamageByEntityEvent) {
    			EntityDamageByEntityEvent subEvent = (EntityDamageByEntityEvent) event;
    			attacker = subEvent.getDamager();
    	}
        if (!(event.getEntity() instanceof Wolf)) return;
        Wolf wolf = (Wolf) event.getEntity();
        if (!wolf.isTamed()) return;
        if ((attacker) == wolf.getOwner()) {
            if (attacker instanceof Player) {
                Player player = (Player)attacker;
            	if (plugin.checkPermissions(player, "invinciwolf.own")) {
            		event.setDamage(0);
            	}
            }
        }
        if (wolf.getOwner() == null) {
        	if ((attacker instanceof Player)) {
                Player player = (Player)attacker;
            	if (!plugin.checkPermissions(player, "invinciwolf.offline")) {
            		event.setDamage(0);
            		((Player) attacker).sendMessage(ChatColor.RED + "[InvinciWolf] " + (plugin.pOffline) + ".");
            	}
        	}
        }
    }
}
