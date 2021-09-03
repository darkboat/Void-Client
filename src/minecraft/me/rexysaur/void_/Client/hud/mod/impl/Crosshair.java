package me.rexysaur.void_.Client.hud.mod.impl;

import java.awt.Dimension;
import java.awt.Toolkit;

import me.rexysaur.void_.Client.hud.mod.HudMod;

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
