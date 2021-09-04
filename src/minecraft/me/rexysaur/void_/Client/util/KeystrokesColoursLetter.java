package me.rexysaur.void_.Client.util;

import java.awt.Color;
import java.util.ArrayList;

import me.rexysaur.void_.Client.Client;

public enum KeystrokesColoursLetter {
	WHITE("WHITE", 255, 255, 255),
	BLACK("BLACK", 0, 0, 0),
	RED("RED", 255, 0, 0),
	GREEN("GREEN", 0, 255, 0),
	BLUE("BLUE", 0, 0, 255);

	private static final ArrayList<String> colours = new ArrayList<>();
	
	public String name;
	
	public int red;
	public int green;
	public int blue;
	public int colour;

	KeystrokesColoursLetter(String name, int... RGB) {
		this.name = name;
		
		this.red = RGB[0];
		this.green = RGB[1];
		this.blue = RGB[2];
		
		this.colour = new Color(this.red, this.green, this.blue).getRGB();
		
		Client.INSTANCE.KeystrokesLetColours.add(this.name + ":" + this.colour);
	}
	
	private void addToColours()
	{
		try
		{
			if(this.name != null)
			{
				KeystrokesColoursLetter.colours.add(this.name + ":" + this.colour);
			}
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}

	public static void setKeystrokesLetterPressed(KeystrokesColoursLetter col)
	{
		Client.INSTANCE.hudmanager.KeyStrokes.letColorPressed = new Color(col.red, col.green, col.blue).getRGB();
	}
	
	public static void setKeystrokesLetterStatic(KeystrokesColoursLetter col)
	{
		Client.INSTANCE.hudmanager.KeyStrokes.letColorStatic = new Color(col.red, col.green, col.blue).getRGB();
	}
	
	public static String getColourByInt(int Colour)
	{
		String name = null;
		
		for (String col : Client.INSTANCE.KeystrokesLetColours)
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
