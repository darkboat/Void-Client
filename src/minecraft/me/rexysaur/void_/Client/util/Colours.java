package me.rexysaur.void_.Client.util;

import java.awt.Color;

public enum Colours {
	WHITE(255, 255, 255),
	BLACK(0, 0, 0);
	
	public int red;
	public int green;
	public int blue;
	
	Colours(int... RGB)
	{
		this.red = RGB[0];
		this.green = RGB[1];
		this.blue = RGB[2];
	}
	
	public static int getColour(Colours col)
	{
		return new Color(col.red, col.green, col.blue).getRGB();
	}
}
