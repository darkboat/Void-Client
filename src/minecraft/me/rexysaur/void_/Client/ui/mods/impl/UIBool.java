package me.rexysaur.void_.Client.ui.mods.impl;

import java.io.IOException;

import me.rexysaur.void_.Client.ui.mods.UIMod;
import me.rexysaur.void_.Client.util.SaveManager;

public class UIBool extends UIMod {
	public boolean enabled;

	public UIBool(String name, int x, int y, String MENU) {
		super(name, x, y, MENU);
		
		try {
			String enabled = SaveManager.getDataFromSave(name + "enabled");
			
			if(enabled != null)
			{
				this.enabled = Boolean.getBoolean(enabled);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void swapValue()
	{
		this.enabled = !this.enabled;
	}
	
	@Override
	public String getValue()
	{
		return translateEnabled();
	}
	
	public String translateEnabled()
	{
		return enabled ? "enabled" : "disabled";
	}
}
