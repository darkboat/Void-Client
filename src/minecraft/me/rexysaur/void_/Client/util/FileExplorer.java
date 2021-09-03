package me.rexysaur.void_.Client.util;

import java.io.File;
import java.io.IOException;

import net.minecraft.client.Minecraft;

public class FileExplorer {
	public static void open(String path)
	{
		try {
			System.out.println("explorer.exe " + path);
			Runtime.getRuntime().exec("explorer.exe " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getAppdataFolder()
	{
		return System.getenv("APPDATA").toString();
	}

	public static String getAssetsFolder()
	{
		return FileExplorer.getAppdataFolder() + File.separator + "Void" + File.separator + "assets";
	}

	public static String getMcFolder()
	{
		return FileExplorer.getAppdataFolder() + File.separator + ".minecraft" + File.separator;
	}
}