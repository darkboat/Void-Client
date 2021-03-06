package me.rexysaur.void_.Client.cosmetics;

import java.util.ArrayList;

import me.rexysaur.void_.Client.Client;
import net.minecraft.util.ResourceLocation;

public class CapeManager {
	public static boolean hasEquippedCape = false;
	public static CosmeticCape equippedCape = null;
	
	public static void equipCape(CosmeticCape cape)
	{
		CapeManager.hasEquippedCape = true;
		CapeManager.equippedCape = cape;
	}
	
	public static void dequipCape()
	{
		CapeManager.hasEquippedCape = false;
		CapeManager.equippedCape = null;
	}
	
	public static boolean hasEquippedCape(CosmeticCape cape)
	{
		return CapeManager.equippedCape.equals(cape);
	}
	
	public static CosmeticCape getCape(String name)
	{
		CosmeticCape cape = null;
		
		for (CosmeticCape c : (ArrayList<CosmeticCape>) Client.INSTANCE.cosmeticmanager.capes)
		{
			if(c.name.equals(name))
			{
				cape = c;
			}
		}

		return cape;
	}
	
	public static ResourceLocation loadCape()
	{
		return CapeManager.equippedCape.getResource();
	}
}
