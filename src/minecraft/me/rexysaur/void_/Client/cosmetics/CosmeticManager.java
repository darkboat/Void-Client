package me.rexysaur.void_.Client.cosmetics;

import java.util.ArrayList;

import me.rexysaur.void_.Client.cosmetics.impl.capes.classic;
import me.rexysaur.void_.Client.cosmetics.impl.hats.TopHat;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class CosmeticManager {
	public static ArrayList capes = new ArrayList<>();
	public static ArrayList hats = new ArrayList<>();
	
	public CosmeticManager()
	{
		capes.add(new classic());
		hats.add(TopHat.class);
	}
}
