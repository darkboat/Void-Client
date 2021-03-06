package me.rexysaur.void_.Client.cosmetics;

import net.minecraft.util.ResourceLocation;

public class CosmeticCape {
	public String name;
	private int price;
	private ResourceLocation resource;
	
	public CosmeticCape(String name, int price)
	{
		this.name = name;
		this.price = price;
		this.resource = new ResourceLocation("Void/capes/" + name + ".png");
	}
	
	public ResourceLocation getResource()
	{
		return this.resource;
	}
	
	public int getPrice()
	{
		return this.price;
	}
	
	public void setName(String name)
	{
		this.name = name;
		this.resource = new ResourceLocation("Void/capes/" + name + ".png");
	}
	
	public void setPrice(int price)
	{
		this.price = price;
	}
}
