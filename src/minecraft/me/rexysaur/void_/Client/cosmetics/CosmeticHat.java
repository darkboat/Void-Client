package me.rexysaur.void_.Client.cosmetics;

import java.awt.Color;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public abstract class CosmeticHat implements LayerRenderer<AbstractClientPlayer>{
	private String name;
	private int price;
	private Color col;
	private ResourceLocation resource;
	
	protected final RenderPlayer playerRenderer;
	
	public CosmeticHat(String name, int price, Color col, RenderPlayer playerRenderer)
	{
		this.name = name;
		this.price = price;
		this.col = col;
		
		this.playerRenderer = playerRenderer;
		
		this.resource = new ResourceLocation("Void/hats/" + name + ".png");
	}

	public String getname()
	{
		return this.name;
	}
	
	public int getPrice()
	{
		return this.price;
	}
	
	public Color getColor()
	{
		return this.col;
	}
	
	public ResourceLocation getResource()
	{
		return this.resource;
	}

	@Override
	public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float HeadYaw, float headPitch, float scale) {
		
		if (player.hasPlayerInfo() && !player.isInvisible())
		{
			render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, HeadYaw, headPitch, scale);
		}
		
	}
	
	public abstract void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float HeadYaw, float headPitch, float scale);
	
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
