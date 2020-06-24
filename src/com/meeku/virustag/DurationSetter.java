package com.meeku.virustag;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DurationSetter implements CommandExecutor {
	
	// Sets potion effect duration to command argument (in seconds) if argument is in range [1,1200]
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			
			if(!(arg3.length == 1)) {
				return false;
			}
			
			try {
				int duration = Integer.parseInt(arg3[0]);
				
				if(duration > 0 && duration <= 1200) {
					VirusTag.duration = duration;
					Bukkit.broadcastMessage("Infection duration is now " + arg3[0] + " seconds");
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
