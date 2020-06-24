package com.meeku.virustag;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimeLimitSetter implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			
			if(!(arg3.length == 1)) {
				return false;
			}
			
			try {
				int timeLimit = Integer.parseInt(arg3[0]);
				
				if(timeLimit >= 0) {
					VirusTag.timeLimit = timeLimit;
					Bukkit.broadcastMessage("Time limit is now " + arg3[0] + " seconds");
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
