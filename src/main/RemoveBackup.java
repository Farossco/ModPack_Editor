package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class RemoveBackup {

	public static void main() {
		
		try {
			Files.delete(new File(Locations.backupPath + Locations.backupFile).toPath());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}