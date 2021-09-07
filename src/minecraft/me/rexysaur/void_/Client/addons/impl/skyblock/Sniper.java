package me.rexysaur.void_.Client.addons.impl.skyblock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import me.rexysaur.void_.Client.addons.Addon;
import me.rexysaur.void_.Client.event.impl.ClientTick;
import me.rexysaur.void_.Client.util.Auction.AC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import optifine.json.JSONArray;
import optifine.json.JSONObject;
import optifine.json.JSONValue;
import optifine.json.ParseException;

public class Sniper extends Addon {
	private int ticks = 0;
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public Sniper()
	{
		super("SkyblockSniper");
		
		super.isEnabled = true;
	}
	
	@Override
	public void onTick(ClientTick event) throws IOException
	{
		ticks += 1;

		if (ticks >= 1200)
		{
			ticks = 0;

			URL url = new URL("82.217.172.136/goodAuctions");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			
			in.close();
			
			con.disconnect();
			
			Gson gson = new GsonBuilder().create();
			JsonReader reader = new JsonReader(new StringReader(content.toString()));
			reader.setLenient(true);

			 AC[] auctions = gson.fromJson(reader, AC[].class);
			
			for(AC ls : auctions)
			{
				String name = "";
				try {
					String uuid = ls.auctioneer;
					String url1 = "https://api.mojang.com/user/profiles/"+uuid.replace("-", "")+"/names";
					String nameJson = IOUtils.toString(new URL(url1));
					JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
					String playerSlot = nameValue.get(nameValue.size()-1).toString();
					JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);

					name += nameObject.get("name").toString();
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (name == "")
				{
					System.err.println("UUID could not be converted");
				}
				else
				{
		            IChatComponent comp = IChatComponent.Serializer.jsonToComponent(String.format("{\n   \"text\":\"%s : %s : %s \",\n   \"color\":\"gold\",\n   \"extra\":[\n      {\n         \"text\":\"[OPEN]\",\n         \"color\":\"light purple\",\n         \"bold\":true,\n         \"italic\":true,\n         \"underlined\":false,\n         \"strikethrough\":false,\n         \"obfuscated\":false,\n         \"clickEvent\":{\n            \"action\":\"run_command\",\n            \"value\":\"/ah %s\"\n         }\n      }\n   ]\n}", ls.item_name, ls.starting_bid, ls.profit ,name));

					mc.thePlayer.addChatMessage(comp);
				}
			}
		}
	}
}
