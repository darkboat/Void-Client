package me.rexysaur.void_.Client.ui.options;

import me.rexysaur.void_.Client.ui.mods.impl.UIBool;
import me.rexysaur.void_.Client.ui.mods.impl.UIColorPicker;

public class Keystrokes extends UIBool {
	public Keystrokes()
	{
		super("Keystrokes", 400, 200, "MAIN");
		
		super.isExtra = true;
	}
}