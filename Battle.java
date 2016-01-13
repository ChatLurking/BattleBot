import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
/* IMPORTANT NOTES
 * 
 * I reused the Player class so:
 * 		battler = winner
 * 		battlee = loser
 * 		wager = percent win
 * 
 * 
 * 
 */
public class Battle {
	public String battler;
	public String battlee;
	public int wager;
	public String dbPath;


	public Battle(String battler, String battlee, int wager, String dbPath){
		this.battler = battler;
		this.battlee = battlee;
		this.wager = wager;
		this.dbPath = dbPath;
	}

	/*public String compareSouls(String battler, String battlee, int wager, String dbPath){
		
		System.err.println("compareSouls start");
		//this.dbPath = dbPath;
		this.battler = battler;
		this.battlee = battlee;
		int battlerPoints = DataBaseConnect(battler);
		int battleePoints = DataBaseConnect(battlee);
		int battleeWin;
		float compare;
		Random r = new Random();
		int randInt = r.nextInt(100);
		compare = (float) battlerPoints / battleePoints * 100;
		
		// Code to check that battler and battlee have enough points to battle
		if (wager > battlerPoints || wager > battleePoints){
			if (wager > battlerPoints){
				return battler + " does not have enough souls to battle";
			}
			return battlee +" does not enough souls to battle";
		}

		System.err.println("r: " +randInt);
		System.err.println("Points: "+battlerPoints+" "+battleePoints);
		System.err.println("battlee == battler");
		if (battleePoints == battlerPoints){
			battleeWin = 49;
			if (battleeWin > randInt){
				UpdateDataBase(battlee, battleePoints + wager);
				UpdateDataBase(battler, battlerPoints - wager);
				return this.battlee +" is victorious!";
			}else{
				UpdateDataBase(battler, battlerPoints + wager);
				UpdateDataBase(battlee, battleePoints - wager);
				return this.battler+" is victorious!";
			}
		}System.err.println("battler > battlee");
		if (battlerPoints > battleePoints) {
			float c = battleePoints / battlerPoints;
			if (c < 7){
				c += 5;
			}
			compare = c;
			System.err.println("c: " +c);
			System.err.println("compare: " +compare);
			if (randInt > compare) {
				UpdateDataBase(battler, battlerPoints + wager);
				UpdateDataBase(battlee, battleePoints - wager);
				return battler + " is victorious!";
			}else {
				UpdateDataBase(battlee, battleePoints + wager);
				UpdateDataBase(battler, battlerPoints - wager);
				return battlee + " is victorious!";
			}
		}System.err.println("battlee > battler");
		if (battleePoints > battlerPoints) {
			float c = (battlerPoints / battleePoints);
			if (c < 7){
				c += 5;
			}
			compare = c;
			System.err.println("c: " +c);
			System.err.println("compare: " +compare);
			if (randInt > compare) {
				UpdateDataBase(battlee, battleePoints + wager);
				UpdateDataBase(battler, battlerPoints - wager);
				return battlee + " is victorious!";
			}else {
				UpdateDataBase(battler, battlerPoints + wager);
				UpdateDataBase(battlee, battleePoints - wager);
				return battler + " is victorious!";
			}
		}
		return null;
	}*/

	public Battle(Player p, String dbPath) {
		// TODO Auto-generated constructor stub
		this.battler = p.getBattler();
		this.battlee = p.getBattlee();
		this.wager = p.getWager();
		this.dbPath = dbPath;
	}

	public Player compareSouls(Player p, String dbPath) {
		Player pl;
		System.out.println("CompareSouls(Player, dbPath)");
		String battler = p.getBattler();
		String battlee = p.getBattlee();
		int battlerPoints = DataBaseConnect(battler);
		int battleePoints = DataBaseConnect(battlee);
		int wager = p.getWager();
		Random r = new Random();
		int randInt = r.nextInt(100);
		System.out.println("Random Num: " + randInt);
		
		
		
		if(battlerPoints > battleePoints){
			float compare = (float) battleePoints / battlerPoints;
			float i = (float) (compare * 100.0);
			System.out.println("compare: "+compare + " i: "+ i);
			if(randInt >= i){
				System.out.println("battler wins");
				managePoints(battler, battlee, battlerPoints, battleePoints, wager);
				System.err.println(battler + " is victorious with a "+ i +
						" chance of winning");
				pl = new Player(battler, battlee, (int) i);
				return pl;
			}else{
				System.out.println("battlee wins");
				managePoints(battlee, battler, battleePoints, battlerPoints, wager);
				System.err.print(battlee + " is victorious with a "+ i +" chance of winning");
				pl = new Player(battlee, battler, (int) i);
				return pl;
			}
		}else if (battleePoints > battlerPoints){
			float compare = (float) battlerPoints/battleePoints;
			float i = (float) (compare * 100.0);
			System.out.println("compare: "+compare + " i: "+ i);
			if (randInt >= i){
				System.out.println("Battlee wins");
				managePoints(battlee, battler, battleePoints, battlerPoints, wager);
				System.out.println(battlee + " is victorious with a "
				+ i +" chance of winning");
				pl = new Player (battlee, battler, (int)i);
				return pl;
			}else{
				System.out.println("battler wins");
				managePoints(battler, battlee, battlerPoints, battleePoints, wager);
				System.out.println(battler + " is victorious with a "+ i +" chance of winning");
				pl = new Player (battler, battlee, (int) i);
				return pl;
			}
		}else if (battleePoints == battlerPoints){
			int battleeWin = 49;
			if (battleeWin >= randInt){
				managePoints(battlee, battler, battleePoints, battlerPoints, wager);
				pl = new Player (battlee, battler, 50);
				return pl;
			}else{
				managePoints(battler, battlee, battlerPoints, battleePoints, wager);
				pl = new Player (battler, battlee, 50);
				return pl;
			}
		}
		return null;
	}
	
	public Player enoughPoints(Player p) {
		// TODO Auto-generated method stub
		Player pl;
		String battler = p.getBattler();
		String battlee = p.getBattlee();
		int battlerPoints = DataBaseConnect(battler);
		int battleePoints = DataBaseConnect(battlee);
		int wager = p.getWager();
		
		if (wager > battlerPoints || wager > battleePoints){
			if (wager > battlerPoints){
				pl = new Player(battler, battlee, wager);
				return pl;
			}
			pl = new Player (battlee, battler, wager);
			return pl;
		}
		return null;
		
	}

	private void managePoints(String winner, String loser, int winnerPoints, 
			int loserPoints, int wager){
		UpdateDataBase(winner, winnerPoints + wager);
		UpdateDataBase(loser, loserPoints - wager);
	}
	
	private void UpdateDataBase(String name, int points){
		
		StringBuilder sb = new StringBuilder();
		sb = sb.append("'").append(name).append("'");
		String user = sb.toString();
		
		sb = new StringBuilder();
		sb = sb.append("'").append(points).append("'");
		String p = sb.toString();
		
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
			Statement stmt = c.createStatement();
			int rs = stmt.executeUpdate( "UPDATE CurrencyUser SET Points = "+p+""
					+ "WHERE Name ="+user+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int DataBaseConnect(String name) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb = sb.append("'").append(name).append("'");
		String user = sb.toString();
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT Points FROM CurrencyUser "
					+ "WHERE Name ="+user+"");
			while ( rs.next() ) {
				int points = rs.getInt("Points");
				System.err.println("Points = " +points);
				c.close();
				return points;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
}