package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import utils.*;

public class Backup {
	
	public static void create() {
		try{
			if ( new File(Locations.backupPath + Locations.backupFile).exists() )
				Files.delete(new File(Locations.backupPath + Locations.backupFile).toPath());
			Files.copy(new File(Locations.path + Locations.modpackFile).toPath(), new File(Locations.backupPath + Locations.backupFile).toPath());
			System.out.println("A backup of the modpacks.xml file has been made");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void restore(){
		try {
			Files.delete(new File(Locations.path + Locations.modpackFile).toPath());
			Files.copy(new File(Locations.backupPath + Locations.backupFile).toPath(), new File (Locations.path + Locations.modpackFile).toPath());
			Files.delete(new File(Locations.backupPath + Locations.backupFile).toPath());
			System.out.println("\nBackup restored successfully !");
			Main.scanner.nextLine();
		}catch (IOException e) {
			System.out.println("\nAn error occured while trying to restore backup !");
			Main.scanner.nextLine();
		}
	}
	
	public static void remove() {
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
			Files.delete(new File(Locations.backupPath + Locations.backupFile).toPath());
			System.out.println("\nBackup removed successfully !");
			Main.scanner.nextLine();
		}catch (IOException e) {
			System.out.println("\nAn orror occured while trying to restore backup !");
			Main.scanner.nextLine();
		}
	}
}
