package com.meeku.virustag;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Disabler implements CommandExecutor {
	
	// Disables pandemic
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg3.length == 0 && arg0 instanceof Player) {
			if(VirusTag.inGame && arg0.isOp()) {
//				VirusTag.hunters.clear();
//				VirusTag.runners.clear();
//				VirusTag.inGame = false;
//				VirusTag.infectionEnabled = false;
//				if(VirusTag.timeLimit > 0) VirusTag.scheduler.cancelTask(VirusTag.timer);
//				((Player)arg0).getWorld().getWorldBorder().reset();
//				Bukkit.broadcastMessage("The game has ended");
				
				VirusTag.endGame();
				return true;
			}
			return true;
		}
		return false;
	}
}
