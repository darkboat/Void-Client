package me.rexysaur.void_.Launcher;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;

import me.rexysaur.void_.Launcher.util.OSHelper;
import me.rexysaur.void_.Launcher.util.UnzipUtility;

public class Main {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Chad");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1000, 600));
		frame.setResizable(false);
		
		JPanel jpanel = new JPanel();
		
		frame.setVisible(true);
		
		frame.getContentPane().add(jpanel);
	}
	
	public static void launch()
	{
		File minecraftDirectory = new File(OSHelper.getOS().getMc());
		File minecraftAssets = new File(minecraftDirectory.toString() + "assets");
		
		File natives = new File(System.getProperty("user.dir") + File.separator + "natives.zip");
		File libraries = new File(System.getProperty("user.dir") + File.separator + "libraries.zip");
		File jar = new File(System.getProperty("user.dir") + File.separator + "Void.jar");

		try {
			FileUtils.copyURLToFile(new URL("https://github.com/darkboat/Void-Client/raw/master/misc/libraries.zip"), libraries);
			FileUtils.copyURLToFile(new URL("https://github.com/darkboat/Void-Client/raw/master/misc/natives.zip"), natives);
			FileUtils.copyURLToFile(new URL("https://github.com/darkboat/Void-Client/raw/master/Export/Void%20Client/Void%20Client.jar"), jar);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		UnzipUtility unzipper = new UnzipUtility();
		
		try {
			unzipper.unzip(natives.toString(), System.getProperty("user.dir") + File.separator + "natives.zip");
			natives.delete();
			
			unzipper.unzip(libraries.toString(), System.getProperty("user.dir") + File.separator + "libraries.zip");
			libraries.delete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try {
			Process process = Runtime.getRuntime().exec("java -"
					+ "Xms1024M "
					+ "Xmx4096M "
					+ "-Djava.library.path=\"" + System.getProperty("user.dir") + File.separator + "natives" + "\" "
					+ "-cp \"" + System.getProperty("user.dir") + File.separator + "libraries" + File.separator + "*" + ";" + jar.toString() + "\" "
					+ "net.minecraft.client.main.Main "
					+ "--width 854 "
					+ "--height 480 "
					+ "--version 1.8.8 "
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
