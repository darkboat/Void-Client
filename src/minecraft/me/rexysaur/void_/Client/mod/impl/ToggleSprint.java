package me.rexysaur.void_.Client.mod.impl;

import org.lwjgl.input.Keyboard;

import me.rexysaur.void_.Client.mod.Mod;
import net.minecraft.client.Minecraft;

public class ToggleSprint extends Mod {
	public ToggleSprint()
	{
		super("ToggleSprint", Keyboard.KEY_K);
		
		super.toggle = true;
	}
	
	@Override
	public void tick() {
		if(Minecraft.getMinecraft().thePlayer != null)
		{
			Minecraft.getMinecraft().thePlayer.setSprinting(true);
		}
	}
	
	@Override
	public void onDisable() {
		Minecraft.getMinecraft().thePlayer.setSprinting(false);
	}
}
