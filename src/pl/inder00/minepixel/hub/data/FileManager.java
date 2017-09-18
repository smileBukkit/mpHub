package pl.inder00.minepixel.hub.data;

import java.io.File;

import pl.inder00.minepixel.hub.mpHub;

public class FileManager {
	
	private static File mainDir = mpHub.getInst().getDataFolder();
	private static File cfgFile = new File(mainDir, "config.yml");
	
	public static void check(){
		if(!mainDir.exists()) mainDir.mkdir();
		if(!cfgFile.exists()) mpHub.getInst().saveDefaultConfig();
	}
	public static File getConfigFile(){
		return cfgFile;
	}

}
