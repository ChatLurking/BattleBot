

import java.util.LinkedList;
import java.util.*;
import org.jibble.pircbot.PircBot;



public class Main extends PircBot {
	//Vars
	String owner;
	String password;
	String dbPath;
	LinkedList <Player> battles;
	String channel;
	LinkedList <String> u;
	//Constructor 
	public Main(String channel, String owner, String nickname, String password, String dbPath){
		this.channel = channel;
		this.setName(nickname);
		this.owner = owner;
		this.password = password;
		this.dbPath = dbPath;
	}
	

	//Start of methods
	public void onMessage(String channel, String sender,
			String login, String hostname, String message){
		//active(channel, sender);
		message = message.toLowerCase();
		String[] messageArray = message.split(" ");
		String command = messageArray[0];

		switch (command){

		case "!battle":
			
			System.err.println("Enter !battle");;
			if (messageArray [1].equals(sender)){
				System.err.println("battle yourself");
				sendMessage(channel, "You cannot battle yourself.");
				System.out.println();
			}
			
			if (messageArray[1].equals("brainsgames")){
				sendMessage(channel, "You are not able to battle them at this time.");
				return;
			}
			
			if (messageArray[1].equals("chatlurking") || sender.equals("chatlurking")){
				sendMessage(channel, "Anne won because she is a badass.");
				return;
			}
		
			if (messageArray[1].matches("^[a-zA-Z0-9_]*$") == true){
				//if(checkBattlee(messageArray[1]) == true){
					System.err.println("Main: first if");
					
					int wager = Integer.parseInt(messageArray[2]);
					System.out.println("Wager: " + wager);
					Player p = new Player(sender, messageArray[1], wager);
					Battle b = new Battle(p, dbPath);
					
					if(b.enoughPoints(p) != null){
						sendMessage(channel, p.getBattler()+" does not have enough souls to battle.");
						return;
					}
					
					//sendMessage(channel ,b.compareSouls(sender, messageArray[1], wager, dbPath));
					p = b.compareSouls(p, dbPath);
					
					sendMessage(channel, p.getBattler() + " is victorious! They had a percent chance of winning!");
					sendMessage(channel, "/me "+p.getBattlee()+" has died BibleThump");
					
					System.out.println();
					return;
				//}else{
				//	sendMessage(channel, "You must battle someone who is in the channel.");
				//}
			}else{
				sendMessage(channel, "Do !battle username wager");
				System.out.println();
				return;
			}
			
		case "!bookmark":
			Bookmark bm = new Bookmark(channel);
		

		case "!disconnect":
			if(sender.equals(owner)|| (sender.equals("brainsgames"))){
				disconnect();       
				System.exit(0);
			}

		default:break;
		}
	}
	
	
	public Boolean checkBattlee(String name) {
		
		int size =  u.toArray().length;
		String[] users = new String[size];
		users = (String[]) u.toArray();
		
		for (int i = 0; i < users.length-1; i++){
			
			System.err.println(users[i]);
			if (users[i].equals(name)){
				return true;
			}
		}
		return false;
	}
	
	 
	public void active(String channel, String sender){
		if (u.contains(sender)){
			
		}else{
		u.add(sender);
		}
	}
	
	public void addToBattle(String battler, String battlee, int wager){
		if(searchBattles(battler, battlee, wager) == null){
			battles.add(new Player(battler, battlee, wager));
		}
		System.err.println("Battle already exists between these foes.");
	}
	
	public Player searchBattles(String battler, String battlee, int wager){
		Player p = new Player(battler, battlee, wager);
		if (battles.remove(p) == true){
			
			return p;
		}
		return null;
	}
}


