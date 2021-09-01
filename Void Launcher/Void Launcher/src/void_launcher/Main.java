package void_launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import void_launcher.ui.LauncherFrame;
import void_launcher.util.OSHelper;
import void_launcher.util.UnzipUtility;

public class Main {
	public static void main(String [] args)
	{
		new LauncherFrame();
	}
	
	public static void launch()
	{
		String natives_path = System.getProperty("user.dir") + File.separator + "natives";
		String libraries_path = System.getProperty("user.dir") + File.separator + "libraries";
		String jar_path = System.getProperty("user.dir") + File.separator + "Void.jar";
		
		File minecraftDirectory = new File(OSHelper.getOS().getMc());
		File minecraftAssets = new File(minecraftDirectory.toString() + "assets");
		
		File natives = new File(natives_path + ".zip");
		File libraries = new File(libraries_path + ".zip");
		File jar = new File(jar_path);
		
		try {
			FileUtils.copyURLToFile(new URL("https://github.com/darkboat/launcher/raw/main/natives.zip"), natives);
			FileUtils.copyURLToFile(new URL("https://github.com/darkboat/launcher/releases/download/mcp/libraries.zip"), libraries);
			FileUtils.copyURLToFile(new URL("https://github.com/darkboat/launcher/raw/mcp/Void.jar"), jar);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UnzipUtility unzipper = new UnzipUtility();
		
		try {
			unzipper.unzip(natives.toString(), natives_path);
			natives.delete();
			
			unzipper.unzip(libraries.toString(), libraries_path);
			libraries.delete();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("java "
					+ "-Xms1024M "
					+ "-Xmx4096M "
					+ "-Djava.library.path=\"" + System.getProperty("user.dir") + File.separator + "natives" + "\" "
					+ "-cp \"" + System.getProperty("user.dir") + File.separator + "libraries" + File.separator + "*" + ";" + jar.toString() + "\" "
					+ "net.minecraft.client.main.Main "
					+ "--width 854 "
					+ "--height 480 "
					+ "--username VoidUser "
					+ "--version 1.12 "
					+ "--gemeDir " + minecraftDirectory.toString() + " "
					+ "--assetsDir " + minecraftAssets.toString() + " "
					+ "assetIndex 1.12 "
					+ "uuid N/A "
					+ "--accessToken aeef7bc935f9420eb6314dea7ad7e1e5 "
					+ "--userType mojang");
			
			Process process = Runtime.getRuntime().exec("java "
					
					+ "-Xms1024M "
					+ "-Xmx4096M "
					+ "-Djava.library.path=\"" + System.getProperty("user.dir") + File.separator + "natives" + "\" "
					+ "-cp \"" + System.getProperty("user.dir") + File.separator + "libraries" + File.separator + "*" + ";" + jar.toString() + "\" "
					+ "net.minecraft.client.main.Main "
					+ "--width 854 "
					+ "--height 480 "
					+ "--username VoidUser "
					+ "--version 1.12 "
					+ "--gameDir " + minecraftDirectory.toString() + " "
					+ "--assetsDir " + minecraftAssets.toString() + " "
					+ "assetIndex 1.12 "
					+ "uuid N/A "
					+ "--accessToken aeef7bc935f9420eb6314dea7ad7e1e5 "
					+ "--userType mojang");
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			String s = null;
			
			while((s = stdInput.readLine()) != null)
			{
				System.out.println(s);
			}
			while((s = stdError.readLine()) != null)
			{
				System.out.println(s);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
