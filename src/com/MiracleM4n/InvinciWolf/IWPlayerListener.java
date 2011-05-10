package com.MiracleM4n.InvinciWolf;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerListener;

public class IWPlayerListener extends PlayerListener {
    
	InvinciWolf plugin = null;
	
    public IWPlayerListener(InvinciWolf callbackPlugin) {
        plugin = callbackPlugin;
    }
	
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		
	}
}

