package com.meeku.virustag;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reporter implements CommandExecutor {
	
	// Reports status of pandemic
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg3.length == 0) {
			
			arg0.sendMessage("--------------------------------");
			
			if(VirusTag.inGame) {
				arg0.sendMessage("A game is in progress");
			} else {
				arg0.sendMessage("No game is in progress");
			}
			
			arg0.sendMessage("Hunters remaining: " + VirusTag.hunters.size());
			arg0.sendMessage("Runners remaining: " + VirusTag.runners.size());
			arg0.sendMessage("Hunters can respawn: " + VirusTag.hunterRespawn);
			arg0.sendMessage("Grace period: " + VirusTag.grace + " second(s)");
			arg0.sendMessage("Time limit: " + VirusTag.timeLimit + " second(s)");
			arg0.sendMessage("World border length: " + VirusTag.borderSize);
			arg0.sendMessage("Compass track delay: " + VirusTag.compassDelay);
			arg0.sendMessage("Danger distance: " + VirusTag.radius);
			arg0.sendMessage("Infection period: " + VirusTag.duration + " second(s)");
			arg0.sendMessage("Amplifier: " + VirusTag.amplifier);
			arg0.sendMessage("Mobs infected: " + VirusTag.mobsInfected);
			arg0.sendMessage("--------------------------------");
				
			return true;
		}
		
		return false;
	}

}
