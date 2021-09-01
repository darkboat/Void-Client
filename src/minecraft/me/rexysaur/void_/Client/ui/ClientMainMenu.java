package me.rexysaur.void_.Client.ui;

import java.awt.Color;
import java.io.IOException;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.util.Background;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ClientMainMenu extends GuiScreen {
	public boolean enabled = true;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (this.enabled)
		{
			Background.Draw(this);
			
			Gui.drawRect(0,  0,  250, this.height, new Color(0, 0, 0, 170).getRGB());
			
			GlStateManager.pushMatrix();
			GlStateManager.translate(width/2f, height/2f, 0);
			GlStateManager.scale(3, 3, 1);
			GlStateManager.translate(-(width/2f), -(height/2f), 0);
			this.drawCenteredString(mc.fontRendererObj, Client.INSTANCE.NAME, 360, 230, -1);
			GlStateManager.popMatrix();
			
			mc.fontRendererObj.drawStringWithShadow("v" + Client.INSTANCE.VERSION, 210, 140, new Color(255, 255, 255, 255).getRGB());
			
			super.drawScreen(mouseX, mouseY, partialTicks);
		}
	}
	
	@Override
	public void initGui()
	{
		this.enabled = true;
		
		this.buttonList.add(new GuiButton(1, 10, height / 2 - 40, "SinglePlayer"));
		this.buttonList.add(new GuiButton(2, 10, height / 2 - 15, "Multiplayer"));
		this.buttonList.add(new GuiButton(3, 10, height / 2 + 10, "Options"));
		this.buttonList.add(new GuiButton(4, 10, height / 2 + 35, "Quit"));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.id == 1)
		{
			this.enabled = false;
			this.buttonList.clear();
			mc.displayGuiScreen(new GuiSelectWorld(this));
		}
		if (button.id == 2)
		{
			this.enabled = false;
			this.buttonList.clear();
			mc.displayGuiScreen(new GuiMultiplayer(this));
		}
		if (button.id == 3)
		{
			this.enabled = false;
			this.buttonList.clear();
			mc.displayGuiScreen(new ClientOptionsMenu(this, mc.gameSettings));
		}
		if (button.id == 4)
		{
			this.enabled = false;
			this.buttonList.clear();
			mc.shutdown();
		}
	}
	
}
