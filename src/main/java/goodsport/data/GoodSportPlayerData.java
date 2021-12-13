package goodsport.data;

public class GoodSportPlayerData {

	/*
	 * VARIABLES
	 */
	private String playerUuid;
	private String username;
	private int fAmount;
	private int ggAmount;
	private int firstF;
	private int firstGG;
	private int score;
	
	/*
	 * METHODS - GETTERS
	 */
	public String getUuid() {
		return playerUuid;
	}
	public String getUsername() {
		return username;
	}
	public int getF() {
		return fAmount;
	}
	public int getGG() {
		return ggAmount;
	}
	public int getFirstF() {
		return firstF;
	}
	public int getFirstGG() {
		return firstGG;
	}
	public int getScore() {
		return score;
	}
	
	/*
	 * METHODS - SETTERS
	 */
	public void incrementF() {
		fAmount++;
		GoodSportDataInterface.writePlayerData(this);
	}
	public void incrementGG() {
		ggAmount++;
		GoodSportDataInterface.writePlayerData(this);
	}
	public void incrementFirstF() {
		firstF++;
		GoodSportDataInterface.writePlayerData(this);
	}
	public void incrementFirstGG() {
		firstGG++;
		GoodSportDataInterface.writePlayerData(this);
	}
	public void setScore(int scoreIn) {
		this.score = scoreIn;
		GoodSportDataInterface.writePlayerData(this);
	}
	
	/*
	 * CONSTRUCTOR
	 */
	public GoodSportPlayerData(String uuidIn, String usernameIn, int fIn, int ggIn, int firstFin, int firstGGin, int scoreIn) {
		this.playerUuid = uuidIn;
		this.username = usernameIn;
		this.fAmount = fIn;
		this.ggAmount = ggIn;
		this.firstF = firstFin;
		this.firstGG = firstGGin;
		this.score = scoreIn;
	}
}
