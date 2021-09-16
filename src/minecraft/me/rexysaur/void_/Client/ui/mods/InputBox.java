package me.rexysaur.void_.Client.ui.mods;

import org.lwjgl.input.Keyboard;

public class InputBox {
	public String value = "";
	
	public String id;
	public String menu;
	
	public int x;
	public int y; 
	public int w; 
	public int h;
	
	public boolean isSelected = true;

	public InputBox(String id, int x, int y, int w, int h, String MENU)
	{
		this.id = id;
		this.menu = MENU;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void onClick(int mouseX, int mouseY)
	{
		int W = this.x + this.w;
		int H = this.y + this.h;
		
		if(mouseX > x && mouseX < W && mouseY > y && mouseY < H)
		{
			isSelected = true;
		}
	}
	
	public void keyPressed(int key)
	{
		if(key == Keyboard.KEY_0 || 
				key == Keyboard.KEY_1 || 
				key == Keyboard.KEY_2 || 
				key == Keyboard.KEY_3 || 
				key == Keyboard.KEY_4 || 
				key == Keyboard.KEY_5 || 
				key == Keyboard.KEY_6 || 
				key == Keyboard.KEY_7 || 
				key == Keyboard.KEY_8 || 
				key == Keyboard.KEY_9)
		{
			System.err.println(Keyboard.getEventCharacter());
		}
	}
}
