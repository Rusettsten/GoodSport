package goodsport.commands;

import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import goodsport.data.GoodSportDataInterface;
import goodsport.data.GoodSportPlayerData;

public class CommandCheckScore implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		
		
		if(args.length == 0) {
			String playerName = sender.getName();
		 	String playerUuid = Bukkit.getPlayerUniqueId(playerName).toString();
		 	
		 	GoodSportDataInterface.loadedPlayerData.sort(Comparator.comparing(GoodSportPlayerData::getScore).reversed());
		 
		 	for(int x = 0; x < GoodSportDataInterface.loadedPlayerData.size(); x++) {
			 	if(GoodSportDataInterface.loadedPlayerData.get(x).getUuid().equals(playerUuid)) {
				 	GoodSportPlayerData playerData = GoodSportDataInterface.loadedPlayerData.get(x);
				 	sender.sendMessage("§lYour GoodSport Report Card");
				 	int numberInLeaderboard = x + 1;
				 	int totalLeaderboard = GoodSportDataInterface.loadedPlayerData.size();
				 	sender.sendMessage("§aYou are §6#" + numberInLeaderboard + "§a/" + totalLeaderboard);
				 	sender.sendMessage("§aScore: §b" + playerData.getScore());
				 	sender.sendMessage("§aTotal f's: §b" + playerData.getF());
				 	sender.sendMessage("§aFirst f's: §6" + playerData.getFirstF());
				 	sender.sendMessage("§aTotal gg's: §b" + playerData.getGG());
				 	sender.sendMessage("§aFirst gg's: §6" + playerData.getFirstGG());
				 	return true;
			 	}
		 	}
		 	sender.sendMessage("§a[GoodSport] §cAn error has occured retrieving your score!");
		 	
		 	return true;
		} else if(args.length >= 1) {
			String playerName = args[0];
			String playerUuid = Bukkit.getPlayerUniqueId(playerName).toString();
		 	
		 	GoodSportDataInterface.loadedPlayerData.sort(Comparator.comparing(GoodSportPlayerData::getScore).reversed());
		 
		 	for(int x = 0; x < GoodSportDataInterface.loadedPlayerData.size(); x++) {
			 	if(GoodSportDataInterface.loadedPlayerData.get(x).getUuid().equals(playerUuid)) {
				 	GoodSportPlayerData playerData = GoodSportDataInterface.loadedPlayerData.get(x);
				 	sender.sendMessage("§lA GoodSport Report Card");
				 	int numberInLeaderboard = x + 1;
				 	int totalLeaderboard = GoodSportDataInterface.loadedPlayerData.size();
				 	sender.sendMessage("§aThey are §6#" + numberInLeaderboard + "§a/" + totalLeaderboard);
				 	sender.sendMessage("§aScore: §b" + playerData.getScore());
				 	sender.sendMessage("§aTotal f's: §b" + playerData.getF());
				 	sender.sendMessage("§aFirst f's: §6" + playerData.getFirstF());
				 	sender.sendMessage("§aTotal gg's: §b" + playerData.getGG());
				 	sender.sendMessage("§aFirst gg's: §6" + playerData.getFirstGG());
				 	return true;
			 	}
		 	}
		 	sender.sendMessage("§a[GoodSport] §cAn error has occured retrieving a score!");
		 	
		 	return true;
		}
		return false;
	}
	
	public CommandCheckScore() {
		
	}

}
