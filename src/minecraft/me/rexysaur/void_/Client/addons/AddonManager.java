package me.rexysaur.void_.Client.addons;

import java.util.ArrayList;

import me.rexysaur.void_.Client.addons.impl.skyblock.Sniper;

public class AddonManager {
	public Sniper skyblockSniper;
	
	public ArrayList<Addon> addons = new ArrayList<Addon>();
	
	public AddonManager()
	{
		addons.add(skyblockSniper = new Sniper());
	}
}