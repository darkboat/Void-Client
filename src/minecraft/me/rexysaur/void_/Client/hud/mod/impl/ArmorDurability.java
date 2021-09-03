package me.rexysaur.void_.Client.hud.mod.impl;

import java.awt.Color;

import me.rexysaur.void_.Client.hud.mod.HudMod;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ArmorDurability extends HudMod {
	public int width = 75;
	public int height = 70;
	
	public ArmorDurability()
	{
		super("Armor Durability", 523, 267, 0, 0);
		super.enabled = true;

		super.draggable = true;
	}
	
	public interface anony {
		void drawIcon(String path, int x, int y);
	}
	
	@Override
	public void draw()
	{
		super.setWidth(getWidth());
		super.setHeight(getHeight());
		
		int HelmDurability = 0;
		int ChestDurability = 0;
		int LegDurability = 0;
		int BootsDurability = 0;

		ItemStack helm = mc.thePlayer.inventory.armorInventory[3];
		ItemStack chest = mc.thePlayer.inventory.armorInventory[2];
		ItemStack leg = mc.thePlayer.inventory.armorInventory[1];
		ItemStack boots = mc.thePlayer.inventory.armorInventory[0];

		if (helm != null)
		{
			HelmDurability = helm.getMaxDamage() - helm.getItemDamage();
		}
		if (chest != null)
		{
			ChestDurability = chest.getMaxDamage() - chest.getItemDamage();
		}
		if (leg != null)
		{
			LegDurability = leg.getMaxDamage() - leg.getItemDamage();
		}
		if (boots != null)
		{
			BootsDurability = boots.getMaxDamage() - boots.getItemDamage();
		}
		
		int distance = 15;
		
		int iconSize = 15;
		
		int shifty = 3;
		
		anony Anony = new anony() {
			@Override
			public void drawIcon(String iconName, int x, int y) {
				mc.getTextureManager().bindTexture(new ResourceLocation("Void/icons/" + iconName + ".png"));
				Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, iconSize, iconSize, iconSize, iconSize);
			}
		};
		
		int color = new Color(85, 221, 204).getRGB();
		
		if (HelmDurability > 0)
		{
			fr.drawStringWithShadow(Integer.toString(HelmDurability), this.getX() + 5, this.getY() + shifty, color);
			Anony.drawIcon("helmet", getX() - iconSize, getY());
		}
		if (ChestDurability > 0)
		{
			fr.drawStringWithShadow(Integer.toString(ChestDurability), this.getX() + 5, this.getY() + shifty + (distance * 1), color);
			Anony.drawIcon("chestplate", getX() - iconSize, getY() + (distance * 1));			
		}
		if (LegDurability > 0)
		{
			fr.drawStringWithShadow(Integer.toString(LegDurability), this.getX() + 5, this.getY() + shifty + (distance * 2), color);
			Anony.drawIcon("leggings", getX() - iconSize, getY() + (distance * 2));
		}
		if (BootsDurability > 0)
		{
			fr.drawStringWithShadow(Integer.toString(BootsDurability), this.getX() + 5, this.getY() + shifty + (distance * 3), color);
			Anony.drawIcon("boots", getX() - iconSize, getY() + (distance * 3));
		}
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY)
	{
		if (this.enabled && this.draggable)
		{
			this.draw();
			
			super.renderDummy(mouseX, mouseY);
		}
	}

	public void drawShowcase(int x, int y)
	{

	}
	
	@Override
	public int getWidth()
	{
		return this.width;
	}
	
	@Override
	public int getHeight()
	{
		return this.height;
	}
}
