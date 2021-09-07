package me.rexysaur.void_.Client.hud.mod.impl;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import me.rexysaur.void_.Client.hud.mod.HudMod;

public class CPSMod extends HudMod {
	
	private ArrayList<Long> clicks = new ArrayList<Long>();
	private boolean wasPressed;
	private long lastPressed;
	
	public CPSMod()
	{
		super("CPS", 15, 125, 0, 0);

		super.enabled = true;
	}
	
	@Override
	public int getWidth()
	{
		return fr.getStringWidth(getCPS());
	}
	
	@Override
	public int getHeight()
	{
		return fr.FONT_HEIGHT;
	}

	@Override
	public void draw()
	{
		super.setWidth(getWidth());
		super.setHeight(getHeight());
		
		if (this.enabled)
		{
			final boolean pressed = Mouse.isButtonDown(0);
			
			if(pressed != this.wasPressed)
			{
				this.lastPressed = System.currentTimeMillis();
				this.wasPressed = pressed;

				if(pressed)
				{
					this.clicks.add(this.lastPressed);
				}
			}

			fr.drawStringWithShadow(getCPS(), getX(), getY(), -1);
			super.draw();
		}
	}

	@Override
	public void renderDummy(int mouseX, int mouseY)
	{
		if (this.enabled)
		{
			this.draw();
			
			super.renderDummy(mouseX, mouseY);
		}
	}

	public String getCPS()
	{
		final String bracket = "§0";
		final String name = "§e";
		final String count = "§f";

		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		
		return bracket + "[ " + name + "CPS" + count + " : " + this.clicks.size() + bracket + " ]";
	}
}
