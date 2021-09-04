package me.rexysaur.void_.Client;

import java.io.IOException;
import java.util.ArrayList;

import me.rexysaur.void_.Client.cosmetics.CapeManager;
import me.rexysaur.void_.Client.cosmetics.CosmeticManager;
import me.rexysaur.void_.Client.event.EventManager;
import me.rexysaur.void_.Client.event.EventTarget;
import me.rexysaur.void_.Client.event.impl.ClientTick;
import me.rexysaur.void_.Client.hud.HUDConfigScreen;
import me.rexysaur.void_.Client.hud.mod.HudManager;
import me.rexysaur.void_.Client.macro.impl.MacroManager;
import me.rexysaur.void_.Client.mod.ModManager;
import me.rexysaur.void_.Client.util.MessageManager;
import me.rexysaur.void_.Client.util.SaveManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Session;

public class Client {
	public Minecraft mc = Minecraft.getMinecraft();
	public static Client INSTANCE = new Client();

	public static final boolean isLauncher = true;

	// Managers
	public EventManager eventmanager;
	public ModManager modmanager;
	public HudManager hudmanager;
	public CosmeticManager cosmeticmanager;
	
	public ArrayList<String> KeystrokesKeyColours = new ArrayList<>();
	public ArrayList<String> KeystrokesLetColours = new ArrayList<>();

	public String NAME = "Void Client";
	public String VERSION = "1.0.0";

	public Session session;
	private Object sessionService;
	private String clientToken;
	
	public int seconds;
	public int tick;
	public long start;
	public float delta;

	public int combo = 0;
	
	public static int ENCRYPT_SALT = 5;

	public static boolean isLoggedIn = isLauncher ? true : false;

	public void startup()
	{
		eventmanager = new EventManager();
		modmanager = new ModManager();
		hudmanager = new HudManager();
		cosmeticmanager = new CosmeticManager();
		
		eventmanager.register(this);

		this.start = System.nanoTime();
		
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
	public void onTick(ClientTick event)
	{
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
