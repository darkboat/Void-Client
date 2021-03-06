package me.rexysaur.void_.Client.hud.mod.impl;

import java.awt.Color;
import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.opengl.GL11;

import me.rexysaur.void_.Client.hud.mod.HudMod;
import me.rexysaur.void_.Client.util.Colours;
import me.rexysaur.void_.Client.util.KeystrokesColoursKey;
import me.rexysaur.void_.Client.util.KeystrokesColoursLetter;
import me.rexysaur.void_.Client.util.SaveManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class Keystrokes extends HudMod {

	// Key
	public int currentKeyColStatic = 1;
	public int currentKeyColPressed = 0;
	
	public int keyColorStatic;
	public int keyColorPressed;

	// Letter
	public int currentLetColStatic = 0;
	public int currentLetColPressed = 1;
	
	public int letColorStatic;
	public int letColorPressed;

	public Keystrokes() {
		super("Keystrokes", 5, 5, 58, 75);

		this.keyColorStatic = Colours.getColour(Colours.BLACK);
		this.letColorStatic = Colours.getColour(Colours.WHITE);

		this.keyColorPressed = Colours.getColour(Colours.WHITE);
		this.letColorPressed = Colours.getColour(Colours.BLACK);
		
		try {
			String KKP = SaveManager.getDataFromSave("KeystrokesKeyPressed");
			String KKS = SaveManager.getDataFromSave("KeystrokesKeyStatic");
			String KLP = SaveManager.getDataFromSave("KeystrokesLetPressed");
			String KLS = SaveManager.getDataFromSave("KeystrokesLetStatic");
			
			if (KKP != null && KKS != null && KLP != null && KLS != null)
			{
				int kkp = Integer.parseInt(KKP);
				int kks = Integer.parseInt(KKS);
				int klp = Integer.parseInt(KLP);
				int kls = Integer.parseInt(KLS);
				
				keyColorPressed = kkp;
				keyColorStatic = kks;
				letColorPressed = klp;
				letColorStatic = kls;
				
				currentKeyColPressed = ArrayUtils.indexOf(GameSettings.KeystrokesKeyColours, KeystrokesColoursKey.getColourByInt(kkp));
				currentKeyColStatic = ArrayUtils.indexOf(GameSettings.KeystrokesKeyColours, KeystrokesColoursKey.getColourByInt(kks));
				
				currentLetColPressed = ArrayUtils.indexOf(GameSettings.KeystrokesLetColours, KeystrokesColoursLetter.getColourByInt(klp));
				currentLetColStatic = ArrayUtils.indexOf(GameSettings.KeystrokesLetColours, KeystrokesColoursLetter.getColourByInt(kls));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		super.enabled = true;
	}

	public static enum KeystrokesMode {
		
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
		WASD_JUMP(Key.W, Key.A, Key.S, Key.D, Key.Jump1),
		WASD_JUMP_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, Key.Jump2);
		
		private final Key[] keys;
		private int width,height;
		
		private KeystrokesMode(Key... keysIn)
		{
			this.keys = keysIn;
			
			for (Key key : keys)
			{
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
		}
		
		public int getWidth()
		{
			return width;
		}
		
		public int getHeight()
		{
			return height;
		}
		
		public Key[] getKeys()
		{
			return keys;
		}
	}
	
	public static class Key {
		public static Minecraft mc = Minecraft.getMinecraft();

		private static final Key W = new Key("W", mc.gameSettings.keyBindForward, 21, 1, 18, 18);
		private static final Key A = new Key("A", mc.gameSettings.keyBindLeft, 1, 21, 18, 18);
		private static final Key S = new Key("S", mc.gameSettings.keyBindBack, 21, 21, 18, 18);
		private static final Key D = new Key("D", mc.gameSettings.keyBindRight, 41, 21, 18, 18);
		
		private static final Key LMB = new Key("LMB", mc.gameSettings.keyBindAttack, 1, 41, 28, 18);
		private static final Key RMB = new Key("RMB", mc.gameSettings.keyBindUseItem, 31, 41, 28, 18);
		
		private static final Key Jump1 = new Key("----", mc.gameSettings.keyBindJump, 1, 41, 58, 18);
		private static final Key Jump2 = new Key("----", mc.gameSettings.keyBindJump, 1, 61, 58, 18);
		
		private final String name;
		private final KeyBinding keyBind;
		private final int x,y,w,h;
		
		public Key(String name, KeyBinding keyBind, int x, int y, int w, int h)
		{
			this.name = name;
			this.keyBind = keyBind;
			
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
		
		public boolean isDown() {
			return keyBind.isKeyDown();
		}
		
		public int getWidth()
		{
			return w;
		}
		
		public int getHeight()
		{
			return h;
		}
		
		public String getName()
		{
			return name;
		}
		
		public int getX()
		{
			return x;
		}
		
		public int getY()
		{
			return y;
		}
	}
	
	private KeystrokesMode mode = KeystrokesMode.WASD_JUMP_MOUSE;
	
	@Override
	public int getWidth()
	{
		return 58;
	}
	
	@Override
	public int getHeight()
	{
		return 75;
	}
	
	@Override
	public void draw()
	{
		if (this.enabled)
		{
			GL11.glPushMatrix();
			
			for (Key key : mode.getKeys())
			{
				int textWidth = fr.getStringWidth(key.getName());
				
				Gui.drawRect(getX() + key.getX(), getY() + key.getY(), getX() + key.getX() + key.getWidth(), getY() + key.getY() + key.getHeight(), key.isDown() ? this.keyColorPressed : this.keyColorStatic);
				
				fr.drawStringWithShadow(key.getName(), getX() + key.getX() + key.getWidth() / 2 - textWidth / 2, getY() + key.getY() + key.getHeight() / 2 - 4, key.isDown() ? this.letColorPressed : this.letColorStatic);
			}
			
			GL11.glPopMatrix();
		}
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY)
	{
		if (this.enabled)
		{
			this.draw();
			
			super.renderDummy(mouseX, mouseY);
		}
	}
	
	public void drawShowcaseDefault(int x, int y)
	{
		GL11.glPushMatrix();
		
		for (Key key : mode.getKeys())
		{
			int textWidth = fr.getStringWidth(key.getName());
			
			Gui.drawRect(x + key.getX(), y + key.getY(), x + key.getX() + key.getWidth(), y + key.getY() + key.getHeight(), this.keyColorStatic);
			
			fr.drawStringWithShadow(key.getName(), x + key.getX() + key.getWidth() / 2 - textWidth / 2, y + key.getY() + key.getHeight() / 2 - 4, this.letColorStatic);
		}
		
		GL11.glPopMatrix();
	}
	
	public void drawShowcasePressed(int x, int y)
	{
		GL11.glPushMatrix();
		
		for (Key key : mode.getKeys())
		{
			int textWidth = fr.getStringWidth(key.getName());

			Gui.drawRect(x + key.getX(), y + key.getY(), x + key.getX() + key.getWidth(), y + key.getY() + key.getHeight(), this.keyColorPressed);

			fr.drawStringWithShadow(key.getName(), x + key.getX() + key.getWidth() / 2 - textWidth / 2, y + key.getY() + key.getHeight() / 2 - 4, this.letColorPressed);
		}
		
		GL11.glPopMatrix();
	}
}
