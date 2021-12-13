package goodsport;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import goodsport.data.GoodSportDataInterface;

@SuppressWarnings("deprecation")
public class EventCity implements Listener {

	/*
	 * VARIABLES
	 */
	private GoodSportStartup gss;
	
	/*
	 * METHODS
	 */
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		long currentTime = System.nanoTime();
		gss.lastUuid = e.getPlayer().getUniqueId().toString();
		gss.playerList.clear();
		gss.firstAchieved = false;
		gss.currentWindowType = 1;
		gss.lastDeathTime = currentTime;
		gss.expirationTime = currentTime + gss.windowTime;
		gss.windowOpen = true;
	}
	
	@EventHandler
	public void onPlayerAdvancement(PlayerAdvancementDoneEvent e) {
		long currentTime = System.nanoTime();
		gss.lastUuid = e.getPlayer().getUniqueId().toString();
		gss.playerList.clear();
		gss.firstAchieved = false;
		gss.currentWindowType = 2;
		gss.lastDeathTime = currentTime;
		gss.expirationTime = currentTime + gss.windowTime;
		gss.windowOpen = true;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if(e.isAsynchronous() && gss.windowOpen && Bukkit.getServer().getOnlinePlayers().size() > 1 && !gss.lastUuid.equals(e.getPlayer().getUniqueId().toString())) { //See if the message is valid and if the window is open
			long currentTime = System.nanoTime();
			if(e.getMessage().equalsIgnoreCase("f") || e.getMessage().equalsIgnoreCase("gg")) {
				if(currentTime > gss.expirationTime) { //If window is expired.
					gss.windowOpen = false;
				}else {
				
				if(e.getMessage().equalsIgnoreCase("f") && gss.currentWindowType == 1 && !gss.playerList.contains(e.getPlayer().getName())){ //F processing
					String senderUuid = e.getPlayer().getUniqueId().toString();
					for(int x = 0; x < GoodSportDataInterface.loadedPlayerData.size(); x++) {
						if(senderUuid.equals(GoodSportDataInterface.loadedPlayerData.get(x).getUuid())) {
							int currentScore = GoodSportDataInterface.loadedPlayerData.get(x).getScore();
							int scoreAddition =(int) ((gss.expirationTime - currentTime) / 100000000L);
							int newScore = currentScore + scoreAddition;
							GoodSportDataInterface.loadedPlayerData.get(x).setScore(newScore);
							GoodSportDataInterface.loadedPlayerData.get(x).incrementF();
							if(!gss.firstAchieved) {
								GoodSportDataInterface.loadedPlayerData.get(x).incrementFirstF();
								//FIRST F BONUS GOES HERE
								e.getPlayer().sendMessage("§6 You got first!");
								gss.firstAchieved = true;
							}
							gss.playerList.add(e.getPlayer().getName());
							e.getPlayer().sendMessage("§a You have been awarded " + scoreAddition + " in GoodSport points!");
						}
					}
					
				} else if(e.getMessage().equalsIgnoreCase("gg") && gss.currentWindowType == 2 && !gss.playerList.contains(e.getPlayer().getName())){ //GG processing
					String senderUuid = e.getPlayer().getUniqueId().toString();
					for(int x = 0; x < GoodSportDataInterface.loadedPlayerData.size(); x++) {
						if(senderUuid.equals(GoodSportDataInterface.loadedPlayerData.get(x).getUuid())) {
							int currentScore = GoodSportDataInterface.loadedPlayerData.get(x).getScore();
							int scoreAddition =(int) ((gss.expirationTime - currentTime) / 100000000L);
							int newScore = currentScore + scoreAddition;
							GoodSportDataInterface.loadedPlayerData.get(x).setScore(newScore);
							GoodSportDataInterface.loadedPlayerData.get(x).incrementGG();
							if(!gss.firstAchieved) {
								GoodSportDataInterface.loadedPlayerData.get(x).incrementFirstGG();
								//FIRST GG BONUS GOES HERE
								e.getPlayer().sendMessage("§6 You got first!");
								gss.firstAchieved = true;
							}
							gss.playerList.add(e.getPlayer().getName());
							e.getPlayer().sendMessage("§a You have been awarded " + scoreAddition + " in GoodSport points!");
						}
					}
					
				}
				
				
				
				}//window expiry bracket
			}
		}
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerLoginEvent e) {
		boolean alreadyLoaded = false;
		if(GoodSportDataInterface.loadedPlayerData.size() > 0) {
			Player player = e.getPlayer();
			for(int x = 0; x < GoodSportDataInterface.loadedPlayerData.size(); x++) {
				if(player.getUniqueId().toString().equals(GoodSportDataInterface.loadedPlayerData.get(x).getUuid())) {
					alreadyLoaded = true;
				}
			}
		}
		if(!alreadyLoaded) {
			GoodSportDataInterface.loadPlayerData(e.getPlayer());
		}
	}
	/*
	 * CONSTRUCTOR
	 */
	
	public EventCity(GoodSportStartup gssIn) {
		this.gss = gssIn;
	}
}
