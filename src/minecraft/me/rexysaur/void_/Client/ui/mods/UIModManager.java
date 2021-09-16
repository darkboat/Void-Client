package me.rexysaur.void_.Client.ui.mods;

import java.util.ArrayList;
import java.util.List;

import me.rexysaur.void_.Client.ui.mods.impl.UIBool;
import me.rexysaur.void_.Client.ui.options.Keystrokes;

public class UIModManager {
	public static ArrayList<UIMod> mods = new ArrayList<UIMod>();
	public static ArrayList<InputBox> inputMods = new ArrayList<InputBox>();
	
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
	
	public static ArrayList<Object> getModsOfMENU(String MENU)
	{
		ArrayList<Object> found = new ArrayList<Object>();
		
		for(UIMod mod : mods)
		{
			if(mod.menu == MENU)
			{
				found.add(mod);
			}
		}
		
		for(InputBox mod : inputMods)
		{
			if(mod.menu == MENU)
			{
				found.add(mod);
			}
		}
		
		return found;
	}
}
