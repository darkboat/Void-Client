package me.rexysaur.void_.Client.hud.mod;

import java.awt.Color;

import me.rexysaur.void_.Client.hud.DraggableComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class HudMod {
	
	public Minecraft mc = Minecraft.getMinecraft();
	public FontRenderer fr = mc.fontRendererObj;
	
	public String name;
	public boolean enabled;
	public DraggableComponent drag;
	
	public boolean draggable = true;
	
	public int x, y;
	
	public HudMod(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		
		drag = new DraggableComponent(x, y, x + getWidth(), y + getHeight(), new Color(0, 0, 0, 0).getRGB());
	}
	
	public int getWidth() {
		return 50;
	}
	
	public int getHeight() {
		return 50;
	}
	
	public void draw()
	{
	}
	
	public void renderDummy(int mouseX, int mouseY)
	{
		if (this.enabled)
		{
			drag.draw(mouseX, mouseY);
		}
	}
	
	public int getX()
	{
		return drag.getxPosition();
	}
	
	public int getY()
	{
		return drag.getyPosition();
	}
	
	public void toggle()
	{
		this.enabled = !this.enabled;
	}
}
