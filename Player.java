
public class Player {
	public String battler;
	public String battlee;
	public int wager;
	
	public Player(String battler, String battlee, int wager){
		this.battler = battler;
		this.battlee = battlee;
		this.wager = wager;
	}
	
	
	public String getBattler() {
		return battler;
	}
	
	public String getBattlee() {
		return battlee;
	}
	
	public int getWager() {
		return wager;
	}
}
