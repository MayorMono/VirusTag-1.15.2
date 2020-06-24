package com.meeku.virustag;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RadiusSetter implements CommandExecutor {
	
	// Sets danger radius to argument if argument is in range [1,127]
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			
			if(!(arg3.length == 1)) {
				return false;
			}
			
			try {
				int radius = Integer.parseInt(arg3[0]);
				
				if(radius > 0 && radius <= 127) {
					VirusTag.radius = radius;
					Bukkit.broadcastMessage("Danger radius is now " + arg3[0]);
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
