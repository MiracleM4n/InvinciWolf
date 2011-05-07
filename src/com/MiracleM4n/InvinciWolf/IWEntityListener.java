package com.MiracleM4n.InvinciWolf;

import com.MiracleM4n.InvinciWolf.UpdatedWolf;

import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class IWEntityListener extends EntityListener {

    InvinciWolf plugin = null;
    
    public IWEntityListener(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }

    public void onEntityDamage(EntityDamageEvent event) {
        
        if (!(event instanceof EntityDamageByEntityEvent)) return;
       
        if (!(event.getEntity() instanceof Wolf)) return;
       
        UpdatedWolf wolf = new UpdatedWolf((Wolf)event.getEntity());
       
        if (!wolf.isTame()) return;
       
    	if(event instanceof EntityDamageByEntityEvent){
    		EntityDamageByEntityEvent subEvent = (EntityDamageByEntityEvent) event;
    		
            if ((subEvent.getDamager()) == (plugin.getServer().getPlayer(wolf.getOwner())))
            {
            	event.setCancelled(true);
            }
    	}
    	
        if (plugin.getServer().getPlayer(wolf.getOwner()) == null) {
        	event.setCancelled(true);
        }
    }
}
