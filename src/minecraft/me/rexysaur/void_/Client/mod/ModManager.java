package me.rexysaur.void_.Client.mod;

import java.util.ArrayList;

import me.rexysaur.void_.Client.mod.impl.EnchantGlint;
import me.rexysaur.void_.Client.mod.impl.ToggleSprint;

public class ModManager {
	
	public static EnchantGlint enchantglint;
	
	public ArrayList<Mod> mods;

	public ModManager()
	{
		mods = new ArrayList<>();
		
		mods.add(enchantglint = new EnchantGlint());
		mods.add(new ToggleSprint());
	}
	
	public Mod getMod(String name)
	{
		Mod res = null;
		
		for(Mod mod : mods)
		{
			if(mod.name == name)
			{
				res = mod;
			}
		}
		
		return res;
	}
}
