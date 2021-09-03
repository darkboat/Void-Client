package me.rexysaur.void_.Client.mod.impl;

import me.rexysaur.void_.Client.mod.Category;
import me.rexysaur.void_.Client.mod.Mod;
import net.minecraft.client.entity.EntityPlayerSP;

public class cmd extends Mod {

	public cmd() {
		super("Lobby", "auto lobby", Category.MISC);
	}
	
	@Override
	public void toggle() {
		super.toggle();
		mc.thePlayer.sendChatMessage("/lobby");
	}

}
