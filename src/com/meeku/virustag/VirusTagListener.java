package com.meeku.virustag;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VirusTagListener implements Listener {
    
	@EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if(VirusTag.inGame) {
            
        	Player deadPlayer = e.getEntity();
        	
        	if(VirusTag.runners.contains(deadPlayer)) {
        		VirusTag.runners.remove(deadPlayer);
        		
        		if(VirusTag.runners.size() == 0) {
        			VirusTag.endGame();
        		} else {
        			int n = VirusTag.runners.size();
        			Bukkit.broadcastMessage("There are " + n + " runners remaining");
        			deadPlayer.setGameMode(GameMode.SPECTATOR);
        		}
        	
        	} else if(!VirusTag.hunterRespawn && VirusTag.hunters.contains(deadPlayer)) {
        	
        		VirusTag.hunters.remove(deadPlayer);
        		
        		if(VirusTag.hunters.size() == 0) {
        			VirusTag.endGame();
        		} else {
        			int n = VirusTag.hunters.size();
        			Bukkit.broadcastMessage("There are " + n + " hunters remaining");
        			deadPlayer.setGameMode(GameMode.SPECTATOR);
        		}
        	
        	}
        	
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
    	if(VirusTag.inGame && e.getEntity() instanceof Player) {
    		
    		if(e.getDamager() instanceof Player) {
    			if(VirusTag.infectionEnabled) {
        			e.setDamage(0);
        		} else {
        			e.setCancelled(true);
        		}
    		} else if(e.getDamager() instanceof Arrow) {
    			if(((Arrow) e.getDamager()).getShooter() instanceof Player) {
    				e.setDamage(0);
    			}
    		}
    		
    		
    	}
    }
  
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
    	Player quitter = e.getPlayer();
    	if(VirusTag.hunters.contains(quitter)) {
    		VirusTag.hunters.remove(quitter);
    		if(VirusTag.hunters.size() == 0) VirusTag.endGame();
    	
    	} else if(VirusTag.runners.contains(quitter)) {
    		VirusTag.runners.remove(quitter);
    		if(VirusTag.runners.size() == 0) VirusTag.endGame();
    	}
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    	if(VirusTag.inGame) {
    		e.getPlayer().setGameMode(GameMode.SPECTATOR);
    	}
    }
}
