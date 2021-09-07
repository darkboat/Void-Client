package me.rexysaur.void_.Client.ui.mods;

import java.awt.Color;
import java.io.IOException;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.util.SaveManager;
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
	
	public Color background = new Color(0, 0, 0, 200);
	public Color text = new Color(255, 255, 255);
	public Color extra = new Color(124, 124, 124, 200);
	public Color extraBackground = new Color(177, 177, 177, 200);
	
	private Minecraft mc = Minecraft.getMinecraft();
	private FontRenderer fr = mc.fontRendererObj;
	
	public String menu;
	
	public int extraSize = 150;
	
	public boolean isExtra = false;
	
	public UIMod(String name, int x, int y, String MENU)
	{
		this.name = name;
		
		this.x = x;
		this.y = y;
		
		this.menu = MENU;
	}

	public void draw(GuiScreen gui)
	{
		gui.drawRect(x, y, x + w, y + h, background.getRGB());
		gui.drawRect(x + w, y, x + w + h, y + h, extra.getRGB());

		fr.drawString(name + " : " + getValue(), x + 9, y + (int)(h / 2) - 3, text.getRGB());
		
		if(extraEnabled && isExtra)
		{
			drawExtra(gui);
		}
	}
	
	public void drawExtra(GuiScreen gui)
	{
		int x = this.x + this.w + this.h;
		int y = this.y + this.h;
		
		gui.drawRect(x, y, x + extraSize, y + extraSize, extraBackground.getRGB());
	}
	
	public void swapValue()
	{
	}
	
	public String getValue()
	{
		return null;
	}
	
	public void onClick(int mouseX, int mouseY)
	{
		if (mouseX > this.x && mouseX < this.x + this.w)
		{
			if(mouseY > this.y && mouseY < this.y + this.h)
			{
				swapValue();
			}
		}
		
		if(extraEnabled && isExtra)
		{
			int x = this.x + this.w + this.h;
			int y = this.y + this.h;
			
			if(mouseX > x && mouseX < (x + extraSize) && mouseY > y && mouseY < (y + extraSize))
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
					extraEnabled = !extraEnabled;
				}
			}
		}
	}
}
