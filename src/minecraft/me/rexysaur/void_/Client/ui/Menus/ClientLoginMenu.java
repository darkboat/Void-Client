package me.rexysaur.void_.Client.ui.Menus;

import java.awt.Color;
import java.io.IOException;
import java.util.UUID;

import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.Session.MinecraftLogin;
import me.rexysaur.void_.Client.util.Background;
import me.rexysaur.void_.Client.util.ClientLogin;
import me.rexysaur.void_.Client.util.Colours;
import me.rexysaur.void_.Client.util.SaveManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;

public class ClientLoginMenu extends GuiScreen {
	public boolean enabled = true;
	
	public GuiTextField username;
	public GuiTextField password;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (this.enabled)
		{
			Background.Draw(this);

			Gui.drawRect(0,  0,  this.width, this.height, new Color(0, 0, 0, 170).getRGB());

			GlStateManager.pushMatrix();
			GlStateManager.translate(width/2f, height/2f, 0);
			GlStateManager.scale(3, 3, 1);
			GlStateManager.translate(-(width/2f), -(height/2f), 0);
			this.drawCenteredString(mc.fontRendererObj, Client.INSTANCE.NAME, 475, 215, -1);
			GlStateManager.popMatrix();
			
			this.drawString(fontRendererObj, "Username", 365, height / 2 - 105, Colours.getColour(Colours.WHITE));
			this.drawString(fontRendererObj, "Password", 365, height / 2 - 45, Colours.getColour(Colours.WHITE));
			
			this.username.drawTextBox();
			this.password.drawTextBox();

			super.drawScreen(mouseX, mouseY, partialTicks);
		}
	}
	
	@Override
	public void initGui()
	{
		this.enabled = true;
		
		this.username = new GuiTextField(1, fontRendererObj, 365, height / 2 - 90, 200, fontRendererObj.FONT_HEIGHT + 15);
		this.password = new GuiTextField(2, fontRendererObj, 365, height / 2 - 30, 200, fontRendererObj.FONT_HEIGHT + 15);
		
		if (Client.isLoggedIn)
		{
		}

		this.buttonList.add(new GuiButton(4, 365, height / 2 + 35, "Save"));
	}

	private static String toAsterisk(String str)
	{
		String output = "";
		
		char[] chars = str.toCharArray();

		for (char c : chars)
		{
			output += "*";
		}
		
		return output;
	}
	
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.username.mouseClicked(mouseX, mouseY, mouseButton);
        this.password.mouseClicked(mouseX, mouseY, mouseButton);
    }
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.id == 4)
		{
			this.enabled = false;
			this.buttonList.clear();
			
			String name = this.username.getText();
			String pass = this.password.getText();
			
			ClientLogin.login(name, pass);
			
			SaveManager.saveCredentials(name, pass);
			
			this.mc.displayGuiScreen(new ClientMainMenu());
		}
	}
	
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    	this.username.textboxKeyTyped(typedChar, keyCode);
    	this.password.textboxKeyTyped(typedChar, keyCode);

        if (keyCode == 28 || keyCode == 156)
        {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }
}
