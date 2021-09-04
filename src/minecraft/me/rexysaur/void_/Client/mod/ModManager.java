package me.rexysaur.void_.Client.mod;

import java.util.ArrayList;

import me.rexysaur.void_.Client.mod.impl.EnchantGlint;

public class ModManager {
	
	public static EnchantGlint enchantglint;
	
	public ArrayList<Mod> mods;

	public ModManager()
	{
		mods = new ArrayList<>();
		
		mods.add(enchantglint = new EnchantGlint());
	}
}
