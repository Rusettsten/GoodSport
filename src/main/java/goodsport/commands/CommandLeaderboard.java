package goodsport.commands;

import java.util.Comparator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import goodsport.data.GoodSportDataInterface;
import goodsport.data.GoodSportPlayerData;

public class CommandLeaderboard implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(GoodSportDataInterface.loadedPlayerData.size() == 0) {
			sender.sendMessage("§c Not enough data to generate GoodSport leaderboard!");
			return true;
		}
		
		int limit = 10;
		if(GoodSportDataInterface.loadedPlayerData.size() < 10) {
			limit = GoodSportDataInterface.loadedPlayerData.size();
		}
		
		GoodSportDataInterface.loadedPlayerData.sort(Comparator.comparing(GoodSportPlayerData::getScore).reversed());
		
		sender.sendMessage("§a GoodSport Leaderboard");
		sender.sendMessage("§f 1  §6" + 
		GoodSportDataInterface.loadedPlayerData.get(0).getUsername() + 
		"  §bScore:" + GoodSportDataInterface.loadedPlayerData.get(0).getScore() +
		"  §ff:" + GoodSportDataInterface.loadedPlayerData.get(0).getF() +
		" §41st f:" + GoodSportDataInterface.loadedPlayerData.get(0).getFirstF() +
		" §fgg:" + GoodSportDataInterface.loadedPlayerData.get(0).getGG() +
		" §41st gg:" + GoodSportDataInterface.loadedPlayerData.get(0).getFirstGG());
		for(int x = 1; x < limit; x++) {
			String num = String.valueOf(x+1);
			sender.sendMessage("§f "+ num +"  §a" + GoodSportDataInterface.loadedPlayerData.get(x).getUsername() + 
			"  §bScore:" + GoodSportDataInterface.loadedPlayerData.get(x).getScore() +
			"  §ff:" + GoodSportDataInterface.loadedPlayerData.get(x).getF() +
			" §41st f:" + GoodSportDataInterface.loadedPlayerData.get(x).getFirstF() +
			" §fgg:" + GoodSportDataInterface.loadedPlayerData.get(x).getGG() +
			" §41st gg:" + GoodSportDataInterface.loadedPlayerData.get(x).getFirstGG());
		}
		
		return true;
	}
	
	public CommandLeaderboard() {
		
	}
}
