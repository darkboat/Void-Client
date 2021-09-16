package me.rexysaur.void_.Client.ui.mods.impl;

import me.rexysaur.void_.Client.ui.mods.InputBox;
import me.rexysaur.void_.Client.ui.mods.UIMod;

public class UIColorPicker extends UIMod {
	InputBox R;
	InputBox G;
	InputBox B;
	
	public UIColorPicker(String name, int x, int y, String MENU) {
		super(name, x, y, MENU, "INPUT");
		
		R = new InputBox("R", x, y, 50, 20, "MAIN");
		G = new InputBox("G", x + 75, y, 50, 20, "MAIN");
		B = new InputBox("B", x + 150, y, 50, 20, "MAIN");
	}
}
