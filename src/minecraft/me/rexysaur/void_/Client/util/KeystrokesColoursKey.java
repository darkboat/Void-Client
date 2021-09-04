package me.rexysaur.void_.Client.util;

import java.awt.Color;
import java.util.ArrayList;

import me.rexysaur.void_.Client.Client;

public enum KeystrokesColoursKey {

	WHITE("WHITE", 255, 255, 255),
	BLACK("BLACK", 0, 0, 0),
	RED("RED", 255, 0, 0),
	GREEN("GREEN", 0, 255, 0),
	BLUE("BLUE", 0, 0, 255);

	private static ArrayList<String> colours = new ArrayList<>();
	
	public String name;
	
	public int red;
	public int green;
	public int blue;
	
	public int colour;

	KeystrokesColoursKey(String name, int... RGB) {
		this.name = name;
		
		this.red = RGB[0];
		this.green = RGB[1];
		this.blue = RGB[2];

		this.colour = new Color(this.red, this.green, this.blue).getRGB();

		Client.INSTANCE.KeystrokesKeyColours.add(this.name + ":" + this.colour);
	}
	
	public static void setKeystrokesKeyPressed(KeystrokesColoursKey col)
	{
		Client.INSTANCE.hudmanager.KeyStrokes.keyColorPressed = new Color(col.red, col.green, col.blue).getRGB();
	}

	public static void setKeystrokesKeyStatic(KeystrokesColoursKey col)
	{
		Client.INSTANCE.hudmanager.KeyStrokes.keyColorStatic = new Color(col.red, col.green, col.blue).getRGB();
	}
	
	public static String getColourByInt(int Colour)
	{
		String name = null;
		
		for (String col : Client.INSTANCE.KeystrokesKeyColours)
		{
			String[] data = col.split(":");
			
			String colName = data[0];
			int colour = Integer.parseInt(data[1]);
			
			if (colour == Colour)
			{
				name = colName;
			}
		}
		
		return name;
	}
}
