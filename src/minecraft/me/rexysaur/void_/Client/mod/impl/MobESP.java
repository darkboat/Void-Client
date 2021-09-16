package me.rexysaur.void_.Client.mod.impl;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.rexysaur.void_.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class MobESP extends Mod {
	
	public MobESP()
	{
		super("Mob ESP", Keyboard.KEY_J);
		
		super.enabled = true;
	}
	
	@Override
	public void tick()
	{

	}
}
