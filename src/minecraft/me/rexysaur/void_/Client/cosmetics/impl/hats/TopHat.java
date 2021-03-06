package me.rexysaur.void_.Client.cosmetics.impl.hats;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import me.rexysaur.void_.Client.cosmetics.CosmeticHat;
import me.rexysaur.void_.Client.cosmetics.CosmeticHatBase;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;

public class TopHat extends CosmeticHat {
	
	private final ModelTopHat modelTopHat;

	public TopHat(RenderPlayer player) {
		super("th", 100, new Color(255, 0, 0), player);
		
		modelTopHat = new ModelTopHat(player);
	}

	@Override
	public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float HeadYaw, float headPitch, float scale)
	{
		GlStateManager.pushMatrix();
		playerRenderer.bindTexture(super.getResource());
		
		if(player.isSneaking())
		{
			GL11.glTranslated(0, 0.225D, 0);
		}
		
		Color color = super.getColor();
		GL11.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
		modelTopHat.render(player, limbSwing, limbSwingAmount, ageInTicks, HeadYaw, headPitch, scale);
		GL11.glColor3f(1, 1, 1);
		
		GL11.glPopMatrix();
	}
	
	private class ModelTopHat extends CosmeticHatBase {
		
		private ModelRenderer rim;
		private ModelRenderer pointy;

		public ModelTopHat(RenderPlayer player) {
			super(player);
			
			rim = new ModelRenderer(playerModel, 0, 0);
			rim.addBox(-5.5F, -9F, -5.5F, 11, 2, 11);
			
			pointy = new ModelRenderer(playerModel, 0, 13);
			pointy.addBox(-3.5F, -17F, -3.5F, 7, 8, 7);
		}
		
		@Override
		public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float HeadYaw, float headPitch, float scale)
		{
			rim.rotateAngleX = playerModel.bipedHead.rotateAngleX;
			rim.rotateAngleY = playerModel.bipedHead.rotateAngleY;
			rim.rotationPointX = 0.0F;
			rim.rotateAngleY = 0.0F;
			rim.render(scale);
			
			pointy.rotateAngleX = playerModel.bipedHead.rotateAngleX;
			pointy.rotateAngleY = playerModel.bipedHead.rotateAngleY;
			pointy.rotationPointX = 0.0F;
			pointy.rotateAngleY = 0.0F;
			pointy.render(scale);
		}
	}
}
