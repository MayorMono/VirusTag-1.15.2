package com.meeku.virustag;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public class VirusTag extends JavaPlugin {
	public static int infectTask;
	public static boolean inGame = false;
	public static boolean infectionEnabled = false;
	public static int duration = 1;
	public static int amplifier = 5;
	public static int radius = 4;
	public static boolean mobsInfected = false;
	public static List<Player> runners = new ArrayList<>();
	public static List<Player> hunters = new ArrayList<>();
	public static int grace = 10;
	public static int graceTask;
	public static int borderSize = 100;
	public static WorldBorder border;
	public static int timeLimit = 300;
	public static int time = 300;
	public static int timer;
	public static boolean hunterRespawn = false;
	public static int trackTask;
	public static int compassDelay = 3;

	public static BukkitScheduler scheduler;
	
	@Override
	public void onEnable() {
		System.out.println("Virus Tag by MayorMono");

		this.getCommand("vtstart").setExecutor(new Starter());
		this.getCommand("vtend").setExecutor(new Disabler());
		this.getCommand("vtstatus").setExecutor(new Reporter());
		this.getCommand("vtsetradius").setExecutor(new RadiusSetter());
		this.getCommand("vtsetduration").setExecutor(new DurationSetter());
		this.getCommand("vtsetamplifier").setExecutor(new AmplifierSetter());
		this.getCommand("vttogglemobs").setExecutor(new MobToggler());
		this.getCommand("vtsetgrace").setExecutor(new GraceSetter());
		this.getCommand("vtsetborder").setExecutor(new BorderSetter());
		this.getCommand("vtsettimelimit").setExecutor(new TimeLimitSetter());
		this.getCommand("vttogglehunters").setExecutor(new Hunter());
		this.getCommand("vtsetcompassdelay").setExecutor(new CompassSetter());
		
        getServer().getPluginManager().registerEvents(new VirusTagListener(), this);

		scheduler = getServer().getScheduler();
	}

	// Iterates through list of online players in search of social distancing violations
	public static void infect() {

		// Generate potion effect for current tick
		PotionEffect virus = new PotionEffect(PotionEffectType.WITHER, duration * 20, amplifier);

		for(Player p1 : Bukkit.getOnlinePlayers()) {

			if(!(p1.getGameMode() == GameMode.SPECTATOR) && hunters.contains(p1)) {

				List<Entity> near = p1.getNearbyEntities(radius,radius,radius);
				for(Entity e : near) {

					if(e instanceof Player) {
						Player p2 = (Player) e;

						Location p1L = p1.getLocation();
						Location p2L = p2.getLocation();

						if(runners.contains(p2) && (p1L.distanceSquared(p2L) < (radius * radius))) {
							p2.addPotionEffect(virus);
						}

					} else if(e instanceof Mob && mobsInfected) {
						Location p1L = p1.getLocation();
						Location eL = e.getLocation();

						if(runners.contains(p1) && p1L.distanceSquared(eL) < (radius * radius)) {
							p1.addPotionEffect(virus);
						}
					}

				}
			}

		}
		
	}

	 public static void endGame() {
	    if(hunters.size() == 0 || runners.size() > 0) {
	    	Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "The runners win!");
	    } else if(runners.size() == 0) {
	    	Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "The hunters win!");
	    }
	    	
	    if(timeLimit > 0) scheduler.cancelTask(timer);
	    
	    if(!infectionEnabled) {
	    	VirusTag.scheduler.cancelTask(graceTask);
	    } else {
	    	VirusTag.scheduler.cancelTask(infectTask);
	    }
	    
	    scheduler.cancelTask(trackTask);
	    inGame = false;
	    infectionEnabled = false;
	    border.reset();
	    hunters.clear();
	    runners.clear();
	 }
	 
	 public static void checkTime() {
		 if(time == 0) {
			 endGame();
			 return;
		 } else if(time % 60 == 0) {
			 Bukkit.broadcastMessage(ChatColor.YELLOW + "" + time / 60 + " minute(s) remaining");
		 } else if(time == 30 || time <= 10) {
			 Bukkit.broadcastMessage(ChatColor.YELLOW + "" + time + " seconds remaining");
		 }
		 
		 time = time - 1;
	 }
}
