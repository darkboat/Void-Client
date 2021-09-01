package me.rexysaur.void_.Client.hud;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.hud.mod.HudMod;
import net.minecraft.client.gui.GuiScreen;

public class HUDConfigScreen extends GuiScreen {
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		for(HudMod m : Client.INSTANCE.hudmanager.hudMods)
		{
			m.renderDummy(mouseX, mouseY);
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	};
	
}
