package me.rexysaur.void_.Client.Session;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.stream.JsonReader;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class MinecraftLogin {
	
	static String accessToken;
	static String clientToken;
	static String profileID;
	static String response;
	static String username;

	static Session session = Minecraft.getMinecraft().getSession();
	static String token = MinecraftLogin.session.getToken();
	
	public static Session login(String usernameIn, String password) throws Exception{
		
		String payload = String.format("{\"agent\": {\"name\": \"Minecraft\",\"version\": 1},\"username\": \"" + usernameIn + "\",\"password\": \"" + password + "\",\"clientToken\": \"" + MinecraftLogin.token + "\"}");
		
		URL authenticationURL = new URL("https://authserver.mojang.com/authenticate");
		
		HttpsURLConnection connection = (HttpsURLConnection)authenticationURL.openConnection();
		byte payloadAsBytes[] = payload.getBytes(Charset.forName("UTF-8"));
		
		connection.setConnectTimeout(15000);
		connection.setReadTimeout(15000);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("charset", "UTF-8");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Length", Integer.toString(payloadAsBytes.length));
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.write(payloadAsBytes);
		wr.flush();
		wr.close();
		
		InputStream inputStream = null;
		try {
			response = IOUtils.toString(connection.getInputStream());
		}
		catch(IOException e) {
			System.out.println("Test");
			System.out.println(inputStream);
			e.printStackTrace();
		}
		System.out.println(response);
		
		parseJSON(response);
		System.out.println("1. " + usernameIn);
		System.out.println("2. " + username);
		System.out.println("3. " + profileID);
		System.out.println("4. " + accessToken);

		return new Session(username,profileID,accessToken,"mojang");
	}
	
	public static void parseJSON(String toParse) throws IOException{
		JsonReader reader = new JsonReader(new StringReader(toParse));
		reader.beginObject();
		while(reader.hasNext()) {
			String name = reader.nextName();
			if(name.equals("accessToken")) {
				accessToken = reader.nextString();
			} 
			else if(name.equals("clientToken")){
				System.out.println(reader.nextString() + "V" + MinecraftLogin.token); 
			} 
			else if (name.equals("selectedProfile")) {
				reader.beginObject();
				while(reader.hasNext()){
					String next = reader.nextName();
					boolean hit = false;
					
					if(next.equals("id")) {
						hit = true;
						profileID = reader.nextString();
					}
					if(next.equals("name")) {
						hit = true;
						username = reader.nextString();
					}
					
					if (hit == false)
					{
						reader.skipValue();
					}
				}
				reader.endObject();
			}
			else if(name.equals("availableProfiles")) {
				reader.beginArray();
				while(reader.hasNext()) {
					reader.skipValue();
				}
				reader.endArray();
			} else
			{
				reader.skipValue();
			}
		}
		reader.endObject();
		reader.close();
		
	}
}