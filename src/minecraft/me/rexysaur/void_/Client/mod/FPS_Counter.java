package me.rexysaur.void_.Client.mod;

public class FPS_Counter extends Mod {
	public FPS_Counter()
	{
		super("FPSCounter", "Fps Counter", Category.MISC);
	}
	
	@Override
	public void onEnable()
	{
		super.onEnable();

		System.out.println("Mod Enabled");
	}
}