package goodsport;

import java.util.ArrayList;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import goodsport.commands.CommandCheckScore;
import goodsport.commands.CommandLeaderboard;
import goodsport.commands.CommandSetScore;

public class GoodSportStartup extends JavaPlugin {

	/*
	 * VARIABLES
	 */
	public static String pluginVersion = "1.2";
	public static String pluginDate = "12/9/2021";
	public static String pluginAuthor = "Rusettsten";
	
	public long windowTime = 10000000000L; //Ten Seconds
	
	public long lastDeathTime;
	public String lastUuid;
	public long expirationTime;
	public boolean windowOpen;
	public Plugin plugin;
	public int currentWindowType; //1 = death, 2 = achievement
	public boolean firstAchieved;
	public ArrayList<String> playerList = new ArrayList<String>();
	
	
	/*
	 * METHODS - BASIC PLUGIN CONFIG
	 */
	
	@Override
	public void onEnable() {
		//Set variables
		this.windowOpen = false;
		this.plugin = this;
		
		this.getCommand("leaderboard").setExecutor(new CommandLeaderboard());
		this.getCommand("checkscore").setExecutor(new CommandCheckScore());
		this.getCommand("setscore").setExecutor(new CommandSetScore());
		
		//Event Listening
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EventCity(this), this);
		
		getLogger().info("GoodSport " + pluginVersion + " ENABLED!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("GoodSport " + pluginVersion + " DISABLED!");
	}
}
