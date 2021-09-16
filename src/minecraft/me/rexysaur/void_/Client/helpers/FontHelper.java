package me.rexysaur.void_.Client.helpers;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;

public class FontHelper {
	public static void drawStringScaled(FontRenderer fr, String s, int x, int y, int col, float scale)
	{
		GL11.glPushMatrix();
		
		GL11.glScaled(scale, scale, 0);
		
		fr.drawString(s, x / scale, y / scale, col, false);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glPopMatrix();
	}
}
