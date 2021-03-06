package me.rexysaur.void_.Client.util;

import java.awt.Color;

import me.rexysaur.void_.Client.Client;

public enum CrosshairColours {
	WHITE(255, 255, 255),
	BLACK(0, 0, 0),
	RED(255, 0, 0),
	GREEN(0, 255, 0),
	BLUE(0, 0, 255);
	
	public int red;
	public int green;
	public int blue;

	CrosshairColours(int... RGB) {
		this.red = RGB[0];
		this.green = RGB[1];
		this.blue = RGB[2];
	}
	
	public static void setCrosshairColour(CrosshairColours col)
	{
		Client.INSTANCE.hudmanager.crosshair.color = new Color(col.red, col.green, col.blue).getRGB();
	}
}