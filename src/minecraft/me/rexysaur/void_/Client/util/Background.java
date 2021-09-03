package me.rexysaur.void_.Client.util;

import me.rexysaur.void_.Client.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class Background {
	private static final Minecraft mc = Minecraft.getMinecraft();

	public static void Draw(GuiScreen screen)
	{
		mc.getTextureManager().bindTexture(new ResourceLocation("Void/background.jpg"));
		screen.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, screen.width, screen.height, screen.width, screen.height);
	}
}
