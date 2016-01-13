import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.tools.JavaFileObject;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetUserList {
	public static void main(String [] args){

		String sURL = "http://tmi.twitch.tv/group/user/brainsgames/chatters"; //just a string
		try {
			
			 JSONObject json = new JSONObject(readUrl(sURL));

			    String title = (String) json.get("chatters");

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
}
