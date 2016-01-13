import java.sql.*;

import javax.swing.JFileChooser;

public class Init {
	public static void main (String[] args) throws Exception{

		
		
		
		//Start up bot
		String owner = "chatlurking"; //must be in lowercase
		String joinChannel = "#brainsgames";
		String nickname = "channelurking";
		String password = "";

		//Opens Database
		String file = connect();
		
		Main bot = new Main(joinChannel, owner, nickname, password, file);
	
		//Enable debugging
		bot.setVerbose(false);

		//connect to irc server
		bot.connect("irc.twitch.tv", 6667, password); // http://twitchapps.com/tmi/

		//joins channel
		bot.joinChannel(joinChannel);

		bot.setMessageDelay(3500); //in milliseconds 1000ms = 1sec

	}

	private static String connect() {
		// TODO Auto-generated method stub
		Connection c = null;
		try{
		//	Class.forName("org.sqlite.JBDC");
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String file = chooser.getSelectedFile().getPath();
				c = DriverManager.getConnection("jdbc:sqlite:"+file);

				System.err.println("opened database successfully");
				c.close();
				return file;

			}
		}catch (Exception e){
			System.err.println(e.getClass().getName() + " : "+ e.getMessage() );
		}
		return null;
	}
}