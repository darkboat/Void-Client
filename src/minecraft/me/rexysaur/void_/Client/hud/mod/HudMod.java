package me.rexysaur.void_.Client.hud.mod;

import java.awt.Color;
import java.io.IOException;

import me.rexysaur.void_.Client.hud.DraggableComponent;
import me.rexysaur.void_.Client.util.SaveManager;
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
		
		try {
			String xFromSave = SaveManager.getDataFromSave(name + "x");
			String yFromSave = SaveManager.getDataFromSave(name + "y");
			
//			System.err.println(name + "x" + "  :  " + name + "y");
			
			if(xFromSave != "")
			{
				this.x = Integer.parseInt(xFromSave);
			}
			if(yFromSave != "")
			{
				this.y = Integer.parseInt(yFromSave);
			}
			
			System.err.println(this.name + "x = " + this.x + "    :    " + this.name + "y = " + this.y);

		} catch (IOException e) {
			e.printStackTrace();
		}

		drag = new DraggableComponent(this.x, this.y, this.x + w, this.y + h, new Color(0, 0, 0, 0).getRGB());
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
