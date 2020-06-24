package com.meeku.virustag;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Starter implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg3.length >= 1 && arg0 instanceof Player) {

			if(!VirusTag.inGame && arg0.isOp()) {
				VirusTag.border = ((Player)arg0).getWorld().getWorldBorder();
				
				for(String s: arg3) {
					Player p = Bukkit.getPlayer(s);
					if(p != null && p.getGameMode() != GameMode.SPECTATOR) {
						VirusTag.hunters.add(p);
					}
				}
				
				for(Player p: Bukkit.getOnlinePlayers()) {
					if(!VirusTag.hunters.contains(p) && p.getGameMode() != GameMode.SPECTATOR) {
						VirusTag.runners.add(p);
					}
				}
				
			
				if(VirusTag.hunters.size() > 0 && VirusTag.runners.size() > 0) {
					// teleport all players to command sender, set up world border
	                // host player should go to a suitable location first, then start the game
					
					Player host = (Player)arg0;
					
					host.getWorld().setTime(0);
					
					// TODO: display who is on which team/change player name colour
					
					
					for(Player p: Bukkit.getOnlinePlayers()) {
						p.teleport(host);
						if(p.getGameMode() != GameMode.SPECTATOR) {
							p.setGameMode(GameMode.SURVIVAL);
							p.getInventory().clear();
							p.getActivePotionEffects().clear();
							p.setHealth(20);
							p.setFoodLevel(20);
							p.setSaturation(5);
						}
						
						PotionEffect graceSprint = new PotionEffect(PotionEffectType.SLOW, VirusTag.grace * 20, 6);
						PotionEffect graceJump = new PotionEffect(PotionEffectType.JUMP, VirusTag.grace * 20, 128);
						
						if(VirusTag.hunters.contains(p)) {
							p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
							p.addPotionEffect(graceSprint);
							p.addPotionEffect(graceJump);
						}
						
						p.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE, 1));
						p.getInventory().addItem(new ItemStack(Material.DIAMOND_SHOVEL, 1));
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 16));
					}
					
					VirusTag.border.setCenter(host.getLocation());
					VirusTag.border.setSize(VirusTag.borderSize);
					host.getWorld().setSpawnLocation(host.getLocation());
					
					
					Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "The game has started!");
					
					VirusTag.trackTask = VirusTag.scheduler.scheduleSyncRepeatingTask(VirusTag.getPlugin(VirusTag.class), Hunter::trackNearestRunner, 0L, VirusTag.compassDelay * 20);
					VirusTag.graceTask = VirusTag.scheduler.scheduleSyncDelayedTask(VirusTag.getPlugin(VirusTag.class),this::endGrace,VirusTag.grace * 20);
					
					
					if(VirusTag.grace > 0) Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "A grace period of " + VirusTag.grace + " has begun");
					VirusTag.inGame = true;
					
					return true;
				
				} else {
					arg0.sendMessage("Both teams need at least 1 player");
					VirusTag.hunters.clear();
					VirusTag.runners.clear();
				}

				
			} 
			
			return true;
		}
		return false;
	}

	private void endGrace() {
		Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Grace period has ended");
		VirusTag.infectionEnabled = true;
		VirusTag.infectTask = VirusTag.scheduler.scheduleSyncRepeatingTask(VirusTag.getPlugin(VirusTag.class), VirusTag::infect, 0L, 1L);
		
		if(VirusTag.timeLimit > 0) {
			VirusTag.time = VirusTag.timeLimit;
			VirusTag.timer = VirusTag.scheduler.scheduleSyncRepeatingTask(VirusTag.getPlugin(VirusTag.class), VirusTag::checkTime, 0L, 20L);
		}
	}
}
