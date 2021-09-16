package me.rexysaur.void_.Client.ui.mods.impl;

import java.io.IOException;

import me.rexysaur.void_.Client.ui.mods.UIMod;
import me.rexysaur.void_.Client.util.SaveManager;
import net.minecraft.client.gui.GuiScreen;

public class UIBool extends UIMod {
	public boolean enabled = false;
	public String value = "";

	public UIBool(String name, int x, int y, String MENU) {
		super(name, x, y, MENU, "CLICK");
		
		try {
			String en = SaveManager.getDataFromSave(name + "enabled");
			if(en != null)
			{
				this.enabled = Boolean.getBoolean(en);
				super.value = translate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(GuiScreen gui)
	{
		super.draw(gui);
	}
	
	@Override
	public void swapValue()
	{
		this.enabled = !this.enabled;
		super.value = translate();
	}
	
	@Override
	public String translate()
	{
		return enabled ? "enabled" : "disabled";
	}
}