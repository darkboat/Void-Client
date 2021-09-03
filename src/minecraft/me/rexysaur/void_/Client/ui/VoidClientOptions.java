package me.rexysaur.void_.Client.ui;

import java.io.IOException;

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
	
    private GuiScreen parentGuiScreen;
    private GameSettings guiGameSettings;
    private GuiListExtended optionsRowList;
    
    private final int gui_distance_from_top = 75;
	private final int increment = 40;
	private final int done_distance = 75;
    
    private static final GameSettings.Options[] videoOptions = new GameSettings.Options[] {GameSettings.Options.SHOW_FPS};

    public VoidClientOptions(GuiScreen parentScreenIn, GameSettings gameSettingsIn)
    {
        this.parentGuiScreen = parentScreenIn;
        this.guiGameSettings = gameSettingsIn;
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
		
        this.optionsRowList.drawScreen(mouseX, mouseY, partialTicks);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui()
	{
		this.buttonList.clear();

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + (48 + increment + done_distance), I18n.format("gui.done", new Object[0])));
        this.buttonList.add(new GuiButton(157, 115, 140, "Keystrokes Settings"));
        this.buttonList.add(new GuiButton(156, 115, 111, "Crosshair Settings"));

        if (!OpenGlHelper.vboSupported)
        {
            GameSettings.Options[] agamesettings$options = new GameSettings.Options[videoOptions.length - 1];
            int i = 0;

            for (GameSettings.Options gamesettings$options : videoOptions)
            {
                if (gamesettings$options == GameSettings.Options.USE_VBO)
                {
                    break;
                }

                agamesettings$options[i] = gamesettings$options;
                ++i;
            }

            this.optionsRowList = new GuiOptionsRowList(this.mc, this.width, this.height, 32, this.height - 32, 25, agamesettings$options);
            this.optionsRowList.top += gui_distance_from_top;
        }
        else
        {
            this.optionsRowList = new GuiOptionsRowList(this.mc, this.width, this.height, 32, this.height - 32, 25, videoOptions);
            this.optionsRowList.top += gui_distance_from_top;
        }
	}
	
	public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.optionsRowList.handleMouseInput();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
            
            if (button.id == 156)
            {
            	this.mc.gameSettings.saveOptions();
            	this.mc.displayGuiScreen(new ClientCrosshairMenu(this, guiGameSettings));
            }
            
            if (button.id == 157)
            {
            	this.mc.gameSettings.saveOptions();
            	this.mc.displayGuiScreen(new ClientKeystrokesMenu(this, guiGameSettings));
            }
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        int i = this.guiGameSettings.guiScale;
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.optionsRowList.mouseClicked(mouseX, mouseY, mouseButton);

        if (this.guiGameSettings.guiScale != i)
        {
            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            int j = scaledresolution.getScaledWidth();
            int k = scaledresolution.getScaledHeight();
            this.setWorldAndResolution(this.mc, j, k);
        }
    }

    /**
     * Called when a mouse button is released.  Args : mouseX, mouseY, releaseButton
     */
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        int i = this.guiGameSettings.guiScale;
        super.mouseReleased(mouseX, mouseY, state);
        this.optionsRowList.mouseReleased(mouseX, mouseY, state);

        if (this.guiGameSettings.guiScale != i)
        {
            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            int j = scaledresolution.getScaledWidth();
            int k = scaledresolution.getScaledHeight();
            this.setWorldAndResolution(this.mc, j, k);
        }
    }
}