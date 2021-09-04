package me.rexysaur.void_.Client.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.hud.mod.HudMod;
import net.minecraft.client.settings.GameSettings;

public class SaveManager {
	public static class Option
	{
		public String key;
		public Object value;
		
		public Option(String key, Object value)
		{
			this.key = key;
			this.value = value;
		}
	}

	public static ArrayList<Option> options = new ArrayList<Option>();
	
	public static String saveFilePath = getAppdata() + File.separator + "Void" + File.separator + "conf" + File.separator + "options.txt";
	public static String credFilePath = getAppdata() + File.separator + "Void" + File.separator + "conf" + File.separator + "creds.txt";
	
	public static File saveFile = new File(saveFilePath);
	public static File credFile = new File(credFilePath);
	
	private static String name = "";
	private static String pass = "";

	private static void getOptions()
	{
		Option crosshairColour = new Option("CrosshairColour", GameSettings.Crosshair_Colours[Client.INSTANCE.hudmanager.crosshair.currentCol]);
		Option EnchantGlint = new Option("EnchantGlint", Client.INSTANCE.modmanager.enchantglint.getColor());

		// Keystrokes
		Option KeystrokesKeyPressed = new Option("KeystrokesKeyPressed", Client.INSTANCE.hudmanager.KeyStrokes.keyColorPressed);
		Option KeystrokesKeyStatic = new Option("KeystrokesKeyStatic", Client.INSTANCE.hudmanager.KeyStrokes.keyColorStatic);
		Option KeystrokesLetPressed = new Option("KeystrokesLetPressed", Client.INSTANCE.hudmanager.KeyStrokes.letColorPressed);
		Option KeystrokesLetStatic = new Option("KeystrokesLetStatic", Client.INSTANCE.hudmanager.KeyStrokes.letColorStatic);

		for (HudMod mod : Client.INSTANCE.hudmanager.hudMods)
		{
			if (mod.draggable)
			{
				if(mod.enabled)
				{
					Option x = new Option(mod.name + "x", mod.getX());
					Option y = new Option(mod.name + "y", mod.getY());
					
					options.add(x);
					options.add(y);
				}
			}
		}

		// Keystrokes
		options.add(KeystrokesKeyPressed);
		options.add(KeystrokesKeyStatic);
		options.add(KeystrokesLetPressed);
		options.add(KeystrokesLetStatic);

		// Crosshair
		options.add(crosshairColour);
	}
	
	public static void save() throws IOException
	{
		
		getOptions();

		String output = "";
		String n = "\n";
		
		FileWriter writer = new FileWriter(saveFilePath);
		
		for (Option option : options)
		{
			output += n + option.key + ":" + option.value;
		}
		
		writer.write(output);
		writer.close();
	}

	public static void saveCredentials(String username, String password)
	{
		String encryptedUsername = encrypt(Client.ENCRYPT_SALT, username);
		String encryptedPassword = encrypt(Client.ENCRYPT_SALT, password);
		
		FileWriter writer;
		
		try {
			writer = new FileWriter(credFilePath);
			writer.write("name:" + encryptedUsername + "\npass:" + encryptedPassword);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] getCredentials()
	{
		Scanner reader;

		try {
			reader = new Scanner(credFile);
			
			while(reader.hasNextLine())
			{
				Object data = reader.nextLine();

				if (data != null && !((String)data).isEmpty())
				{
					String[] split = ((String)data).split(":");
					
					if(split.length == 2)
					{
						if (split[0].equals("name"))
						{
							SaveManager.name = split[1];
						}
						
						if (split[0].equals("pass"))
						{
							SaveManager.pass = split[1];
						}
					}
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Client.isLoggedIn = true;
		
		return new String[] {decrypt(Client.ENCRYPT_SALT, SaveManager.name), decrypt(Client.ENCRYPT_SALT, SaveManager.pass)};
	}
	
	private static String encrypt(int salt, String item)
	{
		String letters = "1234567890-=!@#$%^&*()_+qwertyuiop[]\\QWERTYUIOP{}|asdfghjkl;'ASDFGHJKL:\"zxcvbnm,./ZXCVBNM<>?";
		
		char[] chars = item.toCharArray();
		char[] letterChars = letters.toCharArray();
		
		String output = "";
		
		for (char c : chars)
		{
			int index = letters.indexOf(c) + salt;
			
			if (index >= letters.length())
			{
				index = index - letters.length();
			}

			output += letterChars[index];
		}
		
		return output;
	}
	
	private static String decrypt(int salt, String item)
	{
		String letters = "1234567890-=!@#$%^&*()_+qwertyuiop[]\\QWERTYUIOP{}|asdfghjkl;'ASDFGHJKL:\"zxcvbnm,./ZXCVBNM<>?";
		
		char[] chars = item.toCharArray();
		char[] letterChars = letters.toCharArray();
		
		String output = "";
		
		for (char c : chars)
		{
			int index = letters.indexOf(c) - salt;
			
			if (index <= 0)
			{
				index = letters.length() + index;
			}
			output += letterChars[index];
		}
		
		return output;
	}

	public static String getDataFromSave(String key) throws IOException
	{
		String res = "";

		Scanner reader = new Scanner(saveFile);
		
		while (reader.hasNextLine())
		{
			Object data = reader.nextLine();
			
			if (data != null && !((String) data).isEmpty())
			{
				data = (String) data;
				String[] split = ((String) data).split(":");

				String k = split[0];
				String v = split[1];

				if (k.trim().equals(key.trim()))
				{
					res += v;
				}
			}
		}
		
		reader.close();
		return res;
	}
	
	private static String getAppdata()
	{
		return System.getenv("APPDATA").toString();
	}
}
