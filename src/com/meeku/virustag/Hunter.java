package com.meeku.virustag;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hunter implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			if(arg3.length == 0) {
				VirusTag.hunterRespawn = !VirusTag.hunterRespawn;
				if(VirusTag.hunterRespawn) {
					Bukkit.broadcastMessage("Hunters will now respawn");
					return true;
				} else {
					Bukkit.broadcastMessage("Hunters will no longer respawn");
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public static void trackNearestRunner() {
		for(Player h: VirusTag.hunters) {
			Location hL = h.getLocation();
			Player nearestRunner = null;
			double shortestDistance = -1;
			
			for(Player r: VirusTag.runners) {
				Location rL = r.getLocation();
				if(shortestDistance == -1 || rL.distanceSquared(hL) < shortestDistance) {
					shortestDistance = rL.distanceSquared(hL);
					nearestRunner = r;
				}
			}
		
			h.setCompassTarget(nearestRunner.getLocation());
			
			if(h.getInventory().getItemInMainHand() != null && h.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
				String runnerName = nearestRunner.getName();
				int yCord = nearestRunner.getLocation().getBlockY();
				h.sendMessage(ChatColor.AQUA + "Tracking " + runnerName + ": y = " + yCord);
			}
		}
	}
}
