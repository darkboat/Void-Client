package me.rexysaur.void_.Client.cosmetics;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class CosmeticHatBase extends ModelBase {

	protected final ModelBiped playerModel;
	
	public CosmeticHatBase(RenderPlayer player) {
		this.playerModel = player.getMainModel();
	}
	
}
