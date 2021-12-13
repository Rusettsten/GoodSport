package goodsport.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class GoodSportDataInterface {
	
	private static String homeFolder = "plugins/goodsport/data/";
	
	/*
	 * VARIABLES
	 */
	public static ArrayList<GoodSportPlayerData> loadedPlayerData = new ArrayList<GoodSportPlayerData>();
	
	/*
	 * METHODS
	 */
	
	public static void loadPlayerData(Player player) {
		loadedPlayerData.add(directLoadPlayerData(player));
	}
	
	public static GoodSportPlayerData directLoadPlayerData(Player player) {
		String playerUuid = player.getUniqueId().toString();
		String username = player.getName();
		File f = new File(homeFolder + playerUuid + ".yml");
		if(f.exists()) {
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
			String uuid = yaml.getString("uuid");
			int fAmount = yaml.getInt("f.amount");
			int ggAmount = yaml.getInt("gg.amount");
			int firstF = yaml.getInt("first.f");
			int firstGG = yaml.getInt("first.gg");
			int score = yaml.getInt("score");
			GoodSportPlayerData gspd = new GoodSportPlayerData(uuid, username, fAmount, ggAmount, firstF, firstGG, score);
			return gspd;
		}else {
			File path = new File(homeFolder);
			path.mkdirs();
			GoodSportPlayerData gspd = new GoodSportPlayerData(playerUuid, username, 0, 0, 0, 0, 0);
			writePlayerData(gspd);
			return gspd;
		}
	}
	
	public static void writePlayerData(GoodSportPlayerData gspd) {
		YamlConfiguration yaml = new YamlConfiguration();
		yaml.createSection("uuid");
		yaml.createSection("f.amount");
		yaml.createSection("gg.amount");
		yaml.createSection("first.f");
		yaml.createSection("first.gg");
		yaml.createSection("score");
		yaml.set("uuid", gspd.getUuid());
		yaml.set("f.amount", gspd.getF());
		yaml.set("gg.amount", gspd.getGG());
		yaml.set("first.f", gspd.getFirstF());
		yaml.set("first.gg", gspd.getFirstGG());
		yaml.set("score", gspd.getScore());
		try {
			yaml.save(homeFolder + gspd.getUuid() + ".yml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
