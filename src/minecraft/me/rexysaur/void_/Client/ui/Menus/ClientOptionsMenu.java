package me.rexysaur.void_.Client.ui.Menus;

import java.awt.Color;
import java.io.IOException;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.util.Background;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiLockIconButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.IStream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;

public class ClientOptionsMenu extends GuiScreen {
    private final GuiScreen field_146441_g;

    /** Reference to the GameSettings object. */
    private final GameSettings game_settings_1;
    private GuiButton field_175357_i;
    private GuiLockIconButton field_175356_r;
    protected String field_146442_a = "Options";
    
	public ClientOptionsMenu(GuiScreen p_i1046_1_, GameSettings p_i1046_2_)
	{
		super();
		
        this.field_146441_g = p_i1046_1_;
        this.game_settings_1 = p_i1046_2_;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		Background.Draw(this);

		GlStateManager.pushMatrix();
		GlStateManager.translate(width/2f, height/2f, 0);
		GlStateManager.scale(3, 3, 1);
		GlStateManager.translate(-(width/2f), -(height/2f), 0);
		this.drawCenteredString(mc.fontRendererObj, "Options", 480, 200, -1);
		GlStateManager.popMatrix();

		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui()
	{
		int increment = 40;
		int done_distance = 50;
		int current = 48;
		
		int left_column = width / 2 - 150;
		int right_column = this.width / 2 + 10;
		
		this.buttonList.add(new GuiButton(156, width / 2 - 100, this.height / 6 + 48 - 6, "Void Client Options"));
        this.buttonList.add(new GuiButton(106, left_column, this.height / 6 + (current + increment) - 6, 150, 20, I18n.format("options.sounds", new Object[0])));
        this.buttonList.add(new GuiButton(107, right_column, this.height / 6 + (current + increment) - 6, 150, 20, I18n.format("options.stream", new Object[0])));
        
        current += increment;
        
        this.buttonList.add(new GuiButton(101, left_column, this.height / 6 + (current + increment) - 6, 150, 20, I18n.format("options.video", new Object[0])));
        this.buttonList.add(new GuiButton(100, right_column, this.height / 6 + (current + increment) - 6, 150, 20, I18n.format("options.controls", new Object[0])));
        
        current += increment;
        
        this.buttonList.add(new GuiButton(102, left_column, this.height / 6 + (current + increment) - 6, 150, 20, I18n.format("options.language", new Object[0])));
        this.buttonList.add(new GuiButton(103, right_column, this.height / 6 + (current + increment) - 6, 150, 20, I18n.format("options.chat.title", new Object[0])));
        
        current += increment;
        
        this.buttonList.add(new GuiButton(105, left_column, this.height / 6 + (current + increment) - 6, 150, 20, I18n.format("options.resourcepack", new Object[0])));
        this.buttonList.add(new GuiButton(104, right_column, this.height / 6 + (current + increment) - 6, 150, 20, I18n.format("options.snooper.view", new Object[0])));
        
        current += done_distance;
        
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + (current + increment), I18n.format("gui.done", new Object[0])));
	}
	
	public String func_175355_a(EnumDifficulty p_175355_1_)
    {
        IChatComponent ichatcomponent = new ChatComponentText("");
        ichatcomponent.appendSibling(new ChatComponentTranslation("options.difficulty", new Object[0]));
        ichatcomponent.appendText(": ");
        ichatcomponent.appendSibling(new ChatComponentTranslation(p_175355_1_.getDifficultyResourceKey(), new Object[0]));
        return ichatcomponent.getFormattedText();
    }

    public void confirmClicked(boolean result, int id)
    {
        this.mc.displayGuiScreen(this);

        if (id == 109 && result && this.mc.theWorld != null)
        {
            this.mc.theWorld.getWorldInfo().setDifficultyLocked(true);
            this.field_175356_r.func_175229_b(true);
            this.field_175356_r.enabled = false;
            this.field_175357_i.enabled = false;
        }
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id < 100 && button instanceof GuiOptionButton)
            {
                GameSettings.Options gamesettings$options = ((GuiOptionButton)button).returnEnumOptions();
                this.game_settings_1.setOptionValue(gamesettings$options, 1);
                button.displayString = this.game_settings_1.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }

            if (button.id == 108)
            {
                this.mc.theWorld.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum(this.mc.theWorld.getDifficulty().getDifficultyId() + 1));
                this.field_175357_i.displayString = this.func_175355_a(this.mc.theWorld.getDifficulty());
            }

            if (button.id == 109)
            {
                this.mc.displayGuiScreen(new GuiYesNo(this, (new ChatComponentTranslation("difficulty.lock.title", new Object[0])).getFormattedText(), (new ChatComponentTranslation("difficulty.lock.question", new Object[] {new ChatComponentTranslation(this.mc.theWorld.getWorldInfo().getDifficulty().getDifficultyResourceKey(), new Object[0])})).getFormattedText(), 109));
            }

            if (button.id == 110)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiCustomizeSkin(this));
            }

            if (button.id == 8675309)
            {
                this.mc.entityRenderer.activateNextShader();
            }

            if (button.id == 101)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiVideoSettings(this, this.game_settings_1));
            }

            if (button.id == 100)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiControls(this, this.game_settings_1));
            }

            if (button.id == 102)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiLanguage(this, this.game_settings_1, this.mc.getLanguageManager()));
            }

            if (button.id == 103)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new ScreenChatOptions(this, this.game_settings_1));
            }

            if (button.id == 104)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiSnooper(this, this.game_settings_1));
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.field_146441_g);
            }

            if (button.id == 105)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
            }

            if (button.id == 106)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.game_settings_1));
            }

            if (button.id == 107)
            {
                this.mc.gameSettings.saveOptions();
                IStream istream = this.mc.getTwitchStream();

                if (istream.func_152936_l() && istream.func_152928_D())
                {
                    this.mc.displayGuiScreen(new GuiStreamOptions(this, this.game_settings_1));
                }
                else
                {
                    GuiStreamUnavailable.func_152321_a(this);
                }
            }
            if (button.id == 156)
            {
            	this.mc.gameSettings.saveOptions();
            	this.mc.displayGuiScreen(new VoidClientOptions(this));
            }
        }
    }
}
