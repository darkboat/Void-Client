package me.rexysaur.void_.Client.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;

import me.rexysaur.void_.Client.hud.mod.HudMod;
import me.rexysaur.void_.Client.util.CrosshairColours;
import me.rexysaur.void_.Client.util.SaveManager;
import net.minecraft.client.settings.GameSettings;

public class Crosshair extends HudMod {
	public int width = 100;
	public int height = 100;
	public boolean isShadow = false;
	
	// Colour
	public int currentCol = 0;
	public int color = -1;
	
	public Crosshair()
	{
		super("Crosshair", 478, 267, 0, 0);
		super.enabled = true;

		super.draggable = false;

		try {
			String data = SaveManager.getDataFromSave("CrosshairColour");

			if (data == null)
			{
				this.currentCol = 0;
				this.color = -1;
			}
			else
			{
				this.currentCol = ArrayUtils.indexOf(GameSettings.Crosshair_Colours, data);

				CrosshairColours cc = CrosshairColours.valueOf(GameSettings.Crosshair_Colours[currentCol]);

				this.color = new Color(cc.red, cc.green, cc.blue).getRGB();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw()
	{
		super.setWidth(getWidth());
		super.setHeight(getHeight());
		
		if (this.enabled)
		{
			if (this.isShadow)
			{
				fr.drawStringWithShadow("+", getX(), getY(), this.color);
			}
			else
			{
				fr.drawString("+", getX(), getY(), this.color);
			}
			super.draw();
		}
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY)
	{
		if (this.enabled && this.draggable)
		{
			this.draw();
			
			super.renderDummy(mouseX, mouseY);
		}
	}

	public void drawShowcase(int x, int y)
	{
		if (this.isShadow)
		{
			fr.drawStringWithShadow("+", x, y, this.color);
		}
		else
		{
			fr.drawString("+", x, y, this.color);
		}
		super.draw();
	}
	
	@Override
	public int getWidth()
	{
		return this.width;
	}
	
	@Override
	public int getHeight()
	{
		return this.height;
	}
}
