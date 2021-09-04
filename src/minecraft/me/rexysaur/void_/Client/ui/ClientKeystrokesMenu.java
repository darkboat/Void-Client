package me.rexysaur.void_.Client.ui;

import java.awt.Color;
import java.io.IOException;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.util.Background;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;


public class ClientKeystrokesMenu extends GuiScreen {

	    private GuiScreen parentGuiScreen;
	    protected String screenTitle = "Keystrokes Settings";
	    private GameSettings guiGameSettings;
	    private GuiListExtended optionsRowList;

	    /** An array of all of GameSettings.Options's video options. */
	    private static final GameSettings.Options[] videoOptions = new GameSettings.Options[] {GameSettings.Options.KEYSTROKES_KEY_STATIC_COLOUR, GameSettings.Options.KEYSTROKES_KEY_PRESSED_COLOUR, GameSettings.Options.KEYSTROKES_LETTER_STATIC_COLOUR, GameSettings.Options.KEYSTROKES_LETTER_PRESSED_COLOUR, GameSettings.Options.SHOW_KEYSTROKES};

	    public ClientKeystrokesMenu(GuiScreen parentScreenIn, GameSettings gameSettingsIn)
	    {
	        this.parentGuiScreen = parentScreenIn;
	        this.guiGameSettings = gameSettingsIn;
	    }

	    /**
	     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
	     * window resizes, the buttonList is cleared beforehand.
	     */
	    public void initGui()
	    {
	        this.screenTitle = "Keystrokes Settings";
	        this.buttonList.clear();
	        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height - 27, I18n.format("gui.done", new Object[0])));

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
	        }
	        else
	        {
	            this.optionsRowList = new GuiOptionsRowList(this.mc, this.width, this.height, 32, this.height - 32, 25, videoOptions);
	        }
	    }

	    /**
	     * Handles mouse input.
	     */
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

	    /**
	     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
	     */
	    public void drawScreen(int mouseX, int mouseY, float partialTicks)
	    {
	    	int displayX1 = 100;
	    	int displayY1 = 100;
	    	int displayX2 = 200;
	    	int displayY2 = 100;

	        Background.Draw(this);
	        this.optionsRowList.drawScreen(mouseX, mouseY, partialTicks);
	        this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 5, 16777215);
	        
	        Client.INSTANCE.hudmanager.KeyStrokes.drawShowcaseDefault(displayX1, displayY1);
	        Client.INSTANCE.hudmanager.KeyStrokes.drawShowcasePressed(displayX2, displayY2);
	        
	        super.drawScreen(mouseX, mouseY, partialTicks);
	    }

}
