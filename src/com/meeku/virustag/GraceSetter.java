package com.meeku.virustag;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GraceSetter implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			
			if(!(arg3.length == 1)) {
				return false;
			}
			
			try {
				int grace = Integer.parseInt(arg3[0]);
				
				if(grace >= 0 && grace <= 60) {
					VirusTag.grace = grace;
					Bukkit.broadcastMessage("Grace period is now " + arg3[0] + " seconds");
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
