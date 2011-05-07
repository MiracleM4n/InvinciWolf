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
       
        //System.out.println("An entity has been hit by an entity !");
       
        if (!(event.getEntity() instanceof Wolf)) return;
       
        //System.out.println("A wolf has been hit !");
       
        UpdatedWolf wolf = new UpdatedWolf((Wolf)event.getEntity());
       
        if (!wolf.isTame()) return;
       
        if (plugin.getServer().getPlayer(wolf.getOwner()) == null) {
        //System.out.println("The wolf's owner is offline, cancelling the event !");
        event.setCancelled(true);
        }
       }
}
