package com.MiracleM4n.InvinciWolf;

import com.MiracleM4n.InvinciWolf.UpdatedWolf;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class IWEntityListener extends EntityListener {

    InvinciWolf plugin = null;
    
    public IWEntityListener(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }
    
    public void onEntityDamage(EntityDamageEvent event) {
    	Entity attacker = null;
    	if (event instanceof EntityDamageByEntityEvent) {
    			EntityDamageByEntityEvent subEvent = (EntityDamageByEntityEvent) event;
    			attacker = subEvent.getDamager();
    	} else if (event instanceof EntityDamageByProjectileEvent) {
    		EntityDamageByProjectileEvent subEvent = (EntityDamageByProjectileEvent) event;
    		attacker = subEvent.getDamager();
    	} 
        if (!(event.getEntity() instanceof Wolf)) return;
        UpdatedWolf wolf = new UpdatedWolf((Wolf)event.getEntity());
        if (!wolf.isTame()) return;
        if ((attacker) == (plugin.getServer().getPlayer(wolf.getOwner()))) {
            if (attacker instanceof Player) {
            	if ((InvinciWolf.Permissions == null && (((Player)attacker).isOp())) || 
            			(InvinciWolf.Permissions != null && InvinciWolf.Permissions.has(((Player)attacker), "invinciwolf.own"))) {
            		event.setCancelled(true);
            	} else {
            		event.setCancelled(false);
            	}
            }
        }
        if (plugin.getServer().getPlayer(wolf.getOwner()) == null) {
        	if ((attacker instanceof Player)) {
            	if ((InvinciWolf.Permissions == null && (((Player)attacker).isOp())) || 
            			(InvinciWolf.Permissions != null && InvinciWolf.Permissions.has(((Player)attacker), "invinciwolf.offline"))) {
            		event.setCancelled(false);
        		} else {
            		event.setCancelled(true);
            	}
        	}
        }
    }
}
