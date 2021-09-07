package me.rexysaur.void_.Client.hud.mod.impl;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.hud.mod.HudMod;

public class ToggleSprint extends HudMod {
	public ToggleSprint()
	{
		super("ToggleSprint", 15, 90, 0, 0);
		
		super.enabled = true;
	}
	
	@Override
	public int getWidth()
	{
		return fr.getStringWidth(getToggledSprint());
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
			fr.drawStringWithShadow(getToggledSprint(), getX(), getY(), -1);
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
	
	public String getToggledSprint()
	{
		final String bracket = "§0";
		final String name = "§3";
		final String count = "§f";
		
		boolean ToggledSprint = Client.INSTANCE.modmanager.getMod("ToggleSprint").isEnabled();
		
		return bracket + "[ " + name + "ToggledSprint" + count + " : " + (ToggledSprint ? "YES" : "NO") + bracket + " ]";
	}
}
