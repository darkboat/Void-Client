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

	public HudMod(String name, int x, int y, int w, int h)
	{
		this.name = name;
		this.x = x;
		this.y = y;

		drag = new DraggableComponent(x, y, x + w, y + h, new Color(0, 0, 0, 0).getRGB());
	}
	
	public int getWidth() {
		return 0;
	}
	
	public int getHeight() {
		return 0;
	}
	
	public void setWidth(int w)
	{
		drag.setWidth(w);
	}
	
	public void setHeight(int h)
	{
		drag.setHeight(h);
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
