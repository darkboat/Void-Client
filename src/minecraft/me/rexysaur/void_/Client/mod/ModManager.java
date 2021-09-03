package me.rexysaur.void_.Client.mod;

import java.util.ArrayList;

import me.rexysaur.void_.Client.mod.impl.cmd;
import net.java.games.input.Keyboard;

public class ModManager {
	
	public ArrayList<Mod> mods;
	
	public cmd CMD;

	public ModManager()
	{
		mods = new ArrayList<>();
		
		mods.add(CMD = new cmd());
	}

}
