import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class Main {
	public static void main(String[] args) throws MalformedURLException, IOException
	{
		String path = Main.getAppdataFolder() + File.separator + ".minecraft" + File.separator + "versions" + File.separator + "Void" + File.separator;
		
		File jar = new File(path + "Void.jar");
		File json = new File(path + "Void.json");
		
		FileUtils.copyURLToFile(new URL("https://github.com/darkboat/Void-Client/raw/master/Export/Void/Void.jar"), jar);
		FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/darkboat/Void-Client/master/Export/Void/Void.json"), json);
		
		System.exit(1);
	}

	public static String getAppdataFolder()
	{
		return System.getenv("APPDATA").toString();
	}
}