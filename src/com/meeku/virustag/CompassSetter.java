package com.meeku.virustag;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CompassSetter implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			
			if(!(arg3.length == 1)) {
				return false;
			}
			
			try {
				int compassDelay = Integer.parseInt(arg3[0]);
				
				if(compassDelay >= 1 && compassDelay <= 10) {
					VirusTag.compassDelay = compassDelay;
					Bukkit.broadcastMessage("Compass track delay is now " + arg3[0] + " seconds");
				}
			
			} catch(NumberFormatException e) {
				return false;
			} catch(IndexOutOfBoundsException e) {
				return false;
			}
		}
		return true;
	}
}
