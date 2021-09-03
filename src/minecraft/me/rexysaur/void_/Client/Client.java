package me.rexysaur.void_.Client;

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
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Session;

public class Client {
	public Minecraft mc = Minecraft.getMinecraft();
	public static Client INSTANCE = new Client();
	
	public static final boolean DEBUG = true;

	// Managers
	public EventManager eventmanager;
	public ModManager modmanager;
	public HudManager hudmanager;
	public CosmeticManager cosmeticmanager;
	
	public String NAME = "Void Client";
	public String VERSION = "1.0.0";
	
	public Session session;
	private Object sessionService;
	private String clientToken;
	
	public int seconds;
	public int tick;
	public long start;
	public float delta;
	
	public int clicks;
	
	public void startup()
	{
		eventmanager = new EventManager();
		modmanager = new ModManager();
		hudmanager = new HudManager();
		cosmeticmanager = new CosmeticManager();
		
		eventmanager.register(this);

		this.start = System.nanoTime();
		Client.INSTANCE.clicks = 0;
		
		MacroManager.init();

		CapeManager.equipCape(CapeManager.getCape("classic"));
	}

	public void shutdown()
	{
		eventmanager.unregister(this);
	}
	
	@EventTarget
	public void onTick(ClientTick event)
	{
		
	    long time = System.nanoTime();
	    int delta_time = (int) ((time - Client.INSTANCE.start) / 1000000);
	    Client.INSTANCE.start = time;
		
		Client.INSTANCE.tick += delta;
		
		if (Client.INSTANCE.tick >= 1000)
		{
			Client.INSTANCE.tick = 0;
			
			Client.INSTANCE.seconds += 1;
			
			if (Client.INSTANCE.seconds == 20)
			{
				Client.INSTANCE.seconds = 1;
				Client.INSTANCE.clicks = Client.INSTANCE.clicks / Client.INSTANCE.seconds;
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
