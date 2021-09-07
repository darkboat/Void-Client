package me.rexysaur.void_.Client.mod;

import me.rexysaur.void_.Client.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class Mod {
	
	public Minecraft mc = Minecraft.getMinecraft();
	public String name;
	public boolean enabled;
	public int bind = -1323;
	
	public boolean toggle = false;
	
	public Mod(String name, int key)
	{
		this.name = name;
		
		if (key != -1323)
		{
			this.bind = key; 
		}
		
		this.enabled = true;
		
		GameSettings.addBind(this);
	}
	
	public void onEnable()
	{
		Client.INSTANCE.eventmanager.register(this);
	}
	
	public void onDisable()
	{
		Client.INSTANCE.eventmanager.unregister(this);
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
		
		if (enabled)
		{
			onEnable();
		}
		else
		{
			onDisable();
		}
	}
	
	public void toggle()
	{
		setEnabled(!this.enabled);
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void tick()
	{
		
	}
}
