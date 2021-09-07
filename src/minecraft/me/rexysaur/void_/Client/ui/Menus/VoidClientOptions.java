package me.rexysaur.void_.Client.ui.Menus;

import java.io.IOException;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.ui.mods.UIMod;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class VoidClientOptions extends GuiScreen {

	private final int increment = 40;
	private final int done_distance = 75;
	
	private GuiScreen parent;
	
	public VoidClientOptions(GuiScreen parentGUI)
	{
		this.parent = parentGUI;
	}
    
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(new ResourceLocation("Void/background.jpg"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);

		GlStateManager.pushMatrix();
		GlStateManager.translate(width/2f, height/2f, 0);
		GlStateManager.scale(3, 3, 1);
		GlStateManager.translate(-(width/2f), -(height/2f), 0);
		this.drawCenteredString(mc.fontRendererObj, "Void Configuration", 480, 200, -1);
		GlStateManager.popMatrix();
        
        for(UIMod mod : Client.INSTANCE.uimodmanager.getModsOfMENU("MAIN"))
        {
        	mod.draw(this);
        }

		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui()
	{
		this.buttonList.clear();
	}
	
	public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        for(UIMod mod : Client.INSTANCE.uimodmanager.getModsOfMENU("MAIN"))
        {
        	mod.onClick(mouseX, mouseY);
        }
    }

    /**
     * Called when a mouse button is released.  Args : mouseX, mouseY, releaseButton
     */
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {

    }
}
