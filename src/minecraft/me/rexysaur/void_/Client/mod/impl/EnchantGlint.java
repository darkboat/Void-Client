package me.rexysaur.void_.Client.mod.impl;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import me.rexysaur.void_.Client.mod.Category;
import me.rexysaur.void_.Client.mod.Mod;

public class EnchantGlint extends Mod {

	private static final Color DEFAULT_COLOR = new Color(128, 64, 204);
	private Color customColor = new Color(243, 0, 255);

	public EnchantGlint() {
		super("EnchantGlint", Keyboard.KEY_0);

		super.enabled = true;
		super.toggle = true;
	}

	public Color getColor()
	{
		if (isEnabled())
		{
			return customColor;	
		}
		else
		{
			return DEFAULT_COLOR;
		}
	}
}