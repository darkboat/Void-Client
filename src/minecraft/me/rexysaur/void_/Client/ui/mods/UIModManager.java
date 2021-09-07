package me.rexysaur.void_.Client.ui.mods;

import java.util.ArrayList;

import me.rexysaur.void_.Client.ui.options.Keystrokes;

public class UIModManager {
	public static ArrayList<UIMod> mods = new ArrayList<UIMod>();
	
	public UIModManager()
	{
		mods.add(new Keystrokes());
	}
	
	public static UIMod getMod(String name)
	{
		UIMod mud = null;
		
		for(UIMod mod : mods)
		{
			if(mod.name == name)
			{
				mud = mod;
			}
		}
		
		return mud;
	}
	
	public static ArrayList<UIMod> getModsOfMENU(String MENU)
	{
		ArrayList<UIMod> found = new ArrayList<UIMod>();
		
		for(UIMod mod : mods)
		{
			if(mod.menu == MENU)
			{
				found.add(mod);
			}
		}
		
		return found;
	}
}
