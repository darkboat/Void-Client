package me.rexysaur.void_.Client.mod.impl;

import java.awt.Color;

import me.rexysaur.void_.Client.mod.Category;
import me.rexysaur.void_.Client.mod.Mod;

public class EnchantGlint extends Mod {

	private static final Color DEFAULT_COLOR = new Color(128, 64, 204);
	private Color customColor = new Color(112, 205, 118 );

	public EnchantGlint() {
		super("EnchantGlint", "Add a custom enchant glint", Category.MISC);

		super.enabled = true;
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