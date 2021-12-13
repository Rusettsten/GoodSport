package goodsport.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import goodsport.data.GoodSportDataInterface;
import goodsport.data.GoodSportPlayerData;

public class CommandSetScore implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		
		if(args.length == 2) {
			String playerName = args[0];
			int newScore = Integer.parseInt(args[1]);
			String playerUuid = Bukkit.getPlayerUniqueId(playerName).toString();
			
			for(int x = 0; x < GoodSportDataInterface.loadedPlayerData.size(); x++) {
				 if(GoodSportDataInterface.loadedPlayerData.get(x).getUuid().equals(playerUuid)) {
					 GoodSportPlayerData playerData = GoodSportDataInterface.loadedPlayerData.get(x);
					 int oldScore = playerData.getScore();
					 GoodSportDataInterface.loadedPlayerData.get(x).setScore(newScore);
					 sender.sendMessage("§aPlayer §6" + playerName + "'s §ascore has been set from §8" + oldScore + " to §f" + newScore);
				 }
			}
		}else {
			sender.sendMessage("Wrong number of arguments! §cUsage /setscore <playerName> <newScore>");
			return true;
		}
		
		return false;
	}
	
	public CommandSetScore() {
		
	}

}
