package me.rexysaur.void_.Client.hud.mod.impl;

import java.awt.Color;

import me.rexysaur.void_.Client.hud.mod.HudMod;
import net.minecraft.client.gui.Gui;

public class FPSMod extends HudMod {
	public FPSMod()
	{
		super("FPS", 15, 90, 0, 0);
		
		super.enabled = true;
	}
	
	@Override
	public int getWidth()
	{
		return fr.getStringWidth(getFPS());
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
			fr.drawStringWithShadow(getFPS(), getX(), getY(), -1);
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
	
	public String getFPS()
	{
		return "§0[§6FPS§f:" + mc.getDebugFPS() + "§0]";
	}
}
