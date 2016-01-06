package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import utils.*;

public class Backup {
	
	//Detect if the backup file is present
	public static boolean isPresent(String backupFile){
		java.io.File monFichier = new java.io.File(Locations.backupPath + backupFile);
		if (monFichier.exists()) {
			return true;
		}else{
			return false;
		}
	}
		
	public static void create(String backupFile, String file) {
		
		try{
			if ( new File(Locations.backupPath + backupFile).exists() )
				Files.delete(new File(Locations.backupPath + backupFile).toPath());
			Files.copy(new File(Locations.path + Locations.modpackFile).toPath(), new File(Locations.backupPath + backupFile).toPath());
			System.out.println("A backup of the " + file + " file has been made");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void restore(String backupFile, String file){
		try {
			Files.delete(new File(Locations.path + file).toPath());
			Files.copy(new File(Locations.backupPath + backupFile).toPath(), new File (Locations.path + file).toPath());
			Files.delete(new File(Locations.backupPath + backupFile).toPath());
			System.out.println("\nBackup restored successfully !");
			Main.scanner.nextLine();
		}catch (IOException e) {
			System.out.println("\nAn error occured while trying to restore backup !");
			Main.scanner.nextLine();
		}
	}
	
	public static void remove(String backupFile) {
		try {
			System.out.println("Remove Backup ? (Y/N)");
			boolean stay = true;
			String entry = "";
			while(stay){
				entry = Main.scanner.nextLine();
				if ( entry.equals("Y") || entry.equals("y") ){
					stay = false;
				}else if ( entry.equals("N") || entry.equals("n") ){
					return;
				}else{
					System.out.print("Please choose Y or N: ");
				}
			}
			Files.delete(new File(Locations.backupPath + backupFile).toPath());
		}catch (IOException e) {
			System.out.println("\nAn orror occured while trying to restore backup !");
			Main.scanner.nextLine();
		}
	}
}
