package me.rexysaur.void_.Client.ui.mods;

import java.awt.Color;
import java.util.ArrayList;

import me.rexysaur.void_.Client.helpers.FontHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class UIMod {
	public String name;
	
	public int x, y;
	
	public int w = 126;
	public int h = 25;
	public int extraSettingsIconSize = h;
	
	public boolean extraEnabled = false;
	
	public Color blur = new Color(0, 0, 0, 225);
	
	public Color background = new Color(0, 0, 0, 200);
	public Color text = new Color(255, 255, 255);
	public Color extraIcon = new Color(0, 0, 0, 255);
	public Color extraBackground = new Color(0, 0, 0, 150);
	
	protected static Minecraft mc = Minecraft.getMinecraft();
	protected FontRenderer fr = mc.fontRendererObj;
	
	public String menu;
	public String type;
	
	public int extraSize = mc.displayWidth / 3;
	
	public boolean isExtra = false;
	
	public String value = "NOT CONFIGURED";
	
	// Extra Coords
	private static final int extraX = (mc.displayWidth / 3) / 2;
	private static final int extraY = (mc.displayHeight / 2) / 5;
	private static final int extraR = mc.displayWidth / 3;
	private static final int extraB = 400;

	public UIMod(String name, int x, int y, String MENU, String type)
	{
		this.name = name;
		
		this.x = x;
		this.y = y;
		
		this.menu = MENU;
		this.type = type;
	}

	public void draw(GuiScreen gui)
	{
		ArrayList<Object> mods = UIModManager.getModsOfMENU(this.menu);
		boolean isExtraPartOpen = false;
		
		for(Object mod : mods)
		{
			if(mod instanceof UIMod)
			{
				if(((UIMod)mod).extraEnabled)
				{
					isExtraPartOpen = true;
					break;
				}
			}
		}
		
		if(!isExtraPartOpen)
		{
			gui.drawRect(x, y, x + w, y + h, background.getRGB());
			gui.drawRect(x + w, y, x + w + h, y + h, extraIcon.getRGB());
			
			FontHelper.drawStringScaled(fr, "+", x + w + (h / 6), y + (h / 8) - 1, -1, 3);
			
//			GL11.glPushMatrix();
//			
//			GL11.glScaled(2, 2, 0);
//			
//			fr.drawString("+", x / 2, y / 2, -1);
//			GL11.glColor4d(1, 1, 1, 1);
//	        GL11.glEnable(GL11.GL_TEXTURE_2D);
//	        
//			GL11.glPopMatrix();
			
			drawValue(value);
			
			if(extraEnabled && isExtra)
			{
				drawExtra(gui);
			}
		}
		else
		{
			drawExtra(gui);
		}
	}
	
	public String translate()
	{
		return value;
	}
	
	public void drawExtra(GuiScreen gui)
	{
		// Draw blur
		Gui.drawRect(0, 0, mc.displayWidth, mc.displayHeight, blur.getRGB());

		// Draw rect
		Gui.drawRect(extraX,  extraY, extraR, extraB, extraBackground.getRGB());
	}
	
	protected void drawValue(String value)
	{
		fr.drawString(name + " : " + value, x + 9, y + (int)(h / 2) - 3, text.getRGB());
	}
	
	public void swapValue()
	{
	}
	
	public void onClick(int mouseX, int mouseY)
	{
		if(type == "CLICK") {
			if (mouseX > this.x && mouseX < this.x + this.w)
			{
				if(mouseY > this.y && mouseY < this.y + this.h)
				{
					if(!extraEnabled) swapValue();
				}
			}
		}
		
		if(extraEnabled && isExtra)
		{
			
			if(mouseX > extraX && mouseX < extraR && mouseY > extraY && mouseY < extraB)
			{
			}
			else
			{
				extraEnabled = false;
			}
		}
		
		if(isExtra)
		{
			int extraIconX = this.x + this.w;
			int extraIconY = this.y;
			int extraIconSize = this.h;
			
			if (mouseX > extraIconX && mouseX < ((int)extraIconX + (int)extraIconSize))
			{
				if(mouseY > extraIconY && mouseY < ((int)extraIconY + (int)extraIconSize))
				{
					extraEnabled = true;
				}
			}
		}
	}
}
