package me.rexysaur.void_.Client;

import java.io.IOException;
import java.util.ArrayList;

import me.rexysaur.void_.Client.addons.Addon;
import me.rexysaur.void_.Client.addons.AddonManager;
import me.rexysaur.void_.Client.cosmetics.CapeManager;
import me.rexysaur.void_.Client.cosmetics.CosmeticManager;
import me.rexysaur.void_.Client.event.EventManager;
import me.rexysaur.void_.Client.event.EventTarget;
import me.rexysaur.void_.Client.event.impl.ClientTick;
import me.rexysaur.void_.Client.hud.HUDConfigScreen;
import me.rexysaur.void_.Client.hud.mod.HudManager;
import me.rexysaur.void_.Client.macro.impl.MacroManager;
import me.rexysaur.void_.Client.mod.Category;
import me.rexysaur.void_.Client.mod.Mod;
import me.rexysaur.void_.Client.mod.ModManager;
import me.rexysaur.void_.Client.ui.mods.UIModManager;
import me.rexysaur.void_.Client.util.MessageManager;
import me.rexysaur.void_.Client.util.SaveManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.IChatComponent;

public class Client {
	public Minecraft mc = Minecraft.getMinecraft();
	public static Client INSTANCE = new Client();

	public static final boolean isLauncher = false;

	// Managers
	public EventManager eventmanager;
	public ModManager modmanager;
	public HudManager hudmanager;
	public CosmeticManager cosmeticmanager;
	public AddonManager addonmanager;
	public UIModManager uimodmanager;

	public ArrayList<String> KeystrokesKeyColours = new ArrayList<>();
	public ArrayList<String> KeystrokesLetColours = new ArrayList<>();

	public String NAME = "Void Client";
	public String VERSION = "1.0.0";

	public int tick;
	
	public static int ENCRYPT_SALT = 5;

	public static boolean isLoggedIn = isLauncher ? true : false;

	public ArrayList<RenderBat> bats = new ArrayList<RenderBat>();

	public void startup()
	{
		System.err.println("WIDTH = " + mc.displayWidth);
		System.err.println("HEIGHT = " + mc.displayHeight);
		
		eventmanager = new EventManager();
		modmanager = new ModManager();
		hudmanager = new HudManager();
		cosmeticmanager = new CosmeticManager();
		addonmanager = new AddonManager();
		uimodmanager = new UIModManager();
		
		eventmanager.register(this);

		MacroManager.init();

		CapeManager.equipCape(CapeManager.getCape("classic"));
		
		if (!isLauncher)
		{
			SaveManager.getCredentials();
		}
	}

	public void shutdown()
	{
		try {
			SaveManager.save();
		} catch (IOException e) {
			e.printStackTrace();
		}

		eventmanager.unregister(this);
	}
	
	@EventTarget
	public void onTick(ClientTick event) throws IOException
	{
		for(Addon addon : Client.INSTANCE.addonmanager.addons)
		{
			if(addon.isEnabled)
			{
				addon.onTick(event);
			}
		}
		
		for(Mod mod : Client.INSTANCE.modmanager.mods)
		{
			if(mod.isEnabled())
			{
				mod.tick();
			}
		}
		
		for(KeyBinding bind : GameSettings.binds)
		{
			if (bind.isPressed())
			{
				Mod mod = Client.INSTANCE.modmanager.getMod(bind.getKeyDescription());
				
				if(mod.toggle)
				{
					mod.toggle();
				}
				else
				{
					mod.onEnable();
				}
			}
		}

		MacroManager.update();
		
		if (mc.gameSettings.HUD_Config.isPressed())
		{
			mc.displayGuiScreen(new HUDConfigScreen());
		}
	}
	
	@EventTarget
	public void onMessage()
	{
		IChatComponent message = MessageManager.getMostRecentMessage();

		System.err.println(message.getUnformattedText());
	}
}
