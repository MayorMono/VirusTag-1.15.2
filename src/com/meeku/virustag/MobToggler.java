package com.meeku.virustag;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MobToggler implements CommandExecutor {

	// Toggles infection of non-player mobs
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			if(arg3.length == 0) {
				VirusTag.mobsInfected = !VirusTag.mobsInfected;
				if(VirusTag.mobsInfected) {
					if(VirusTag.infectionEnabled) {
						Bukkit.broadcastMessage("Mobs are now infected");
					} else {
						Bukkit.broadcastMessage("Mobs will be infected");
					}
					return true;
				} else {
					if(VirusTag.infectionEnabled) {
						Bukkit.broadcastMessage("Mobs are no longer infected");
					} else {
						Bukkit.broadcastMessage("Mobs will not be infected");
					}
					return true;
				}
			}
			return false;
		}
		return true;
	}

}
