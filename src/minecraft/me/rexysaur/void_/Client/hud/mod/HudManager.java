package me.rexysaur.void_.Client.hud.mod;

import java.util.ArrayList;

import me.rexysaur.void_.Client.hud.mod.impl.ArmorDurability;
import me.rexysaur.void_.Client.hud.mod.impl.Crosshair;
import me.rexysaur.void_.Client.hud.mod.impl.FPSMod;
import me.rexysaur.void_.Client.hud.mod.impl.Keystrokes;

public class HudManager {
	
	public FPSMod FPS;
	public Keystrokes KeyStrokes;
	public Crosshair crosshair;
	ArmorDurability armourDurability;
	
	
	public ArrayList<HudMod> hudMods = new ArrayList<>();
	
	public HudManager()
	{
		
		hudMods.add(FPS = new FPSMod());
		hudMods.add(KeyStrokes = new Keystrokes());
		hudMods.add(crosshair = new Crosshair());
		hudMods.add(armourDurability = new ArmorDurability());
	}
	
	public void renderMods()
	{
		for(HudMod m : hudMods)
		{
			m.draw();
		}
	}
}
