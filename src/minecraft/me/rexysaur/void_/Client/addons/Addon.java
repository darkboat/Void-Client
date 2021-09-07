package me.rexysaur.void_.Client.addons;

import java.io.IOException;

import me.rexysaur.void_.Client.event.impl.ClientTick;
import me.rexysaur.void_.Client.util.SaveManager;

public class Addon {
	public boolean isEnabled = false;
	
	private String name;
	
	public Addon(String name)
	{
		this.name = name;
		
		try {
			String enabled = SaveManager.getDataFromSave(name);
			
			if (!enabled.isEmpty() && enabled != null)
			{
				isEnabled = Boolean.getBoolean(enabled);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getname()
	{
		return name;
	}
	
	public void toggle()
	{
		this.isEnabled = !this.isEnabled;
	}
	
	public void onTick(ClientTick event) throws IOException
	{

	}
}
