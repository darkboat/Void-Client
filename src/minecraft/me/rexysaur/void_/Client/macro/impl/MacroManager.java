package me.rexysaur.void_.Client.macro.impl;

import java.util.ArrayList;

import me.rexysaur.void_.Client.mod.Mod;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class MacroManager {
	public static ArrayList<Macros> macros = new ArrayList<Macros>();
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static enum Macros {
		LOBBY("lobby", Keyboard.KEY_COMMA, "/lobby");
		
		public KeyBinding key;
		public String cmd;
		
		Macros(String name, int key, String cmd)
		{
			this.key = new KeyBinding(name, key, "Client");
			this.cmd = cmd;
		}
	}
	
	public static void init()
	{
		
		MacroManager.macros.add(Macros.LOBBY);
	}
	
	public static void update()
	{
		for (Macros macro : macros)
		{
			if (macro.key.isPressed())
			{
				mc.thePlayer.sendChatMessage(macro.cmd);
			}
		}
	}
}
