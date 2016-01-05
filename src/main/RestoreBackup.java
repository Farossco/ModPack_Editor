package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RestoreBackup {
	
	public static void main(){
		try {
			Files.delete(new File(Locations.path + Locations.modpackFile).toPath());
			Files.copy(new File(Locations.backupPath + Locations.backupFile).toPath(), new File (Locations.path + Locations.modpackFile).toPath());
			Files.delete(new File(Locations.backupPath + Locations.backupFile).toPath());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
