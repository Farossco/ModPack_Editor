package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import utils.*;

public class Backup {
	
	//Detect if the backup file is present
	public static boolean isPresent(String backupFile){
		java.io.File monFichier = new java.io.File(Constants.backupPath + backupFile);
		if (monFichier.exists()) {
			return true;
		}else{
			return false;
		}
	}
	
	public static void manage() {
		
		boolean modpackIsPresent = false;
		boolean thirdpartyIsPresent = false;
		
		Resources.clear();
		if (isPresent(Constants.modpackBackupFile)){
			System.out.println("-- Modpack.xml backup detected --");
			modpackIsPresent = true;
		}
		
		if (isPresent(Constants.thirdpartyBackupFile)){
			System.out.println("-- Thirdparty.xml backup detected --");
			thirdpartyIsPresent = true;
		}
		
		if (!modpackIsPresent && !thirdpartyIsPresent){
			System.out.println("-- No backup file detected --");
		}
			
		System.out.println("\nWhat do you want to do ?");
		
		System.out.println("\n-- modpacks.xml --");
		if (modpackIsPresent){
			System.out.println("1. Restore backup file");
			System.out.println("2. Remove backup file");
			System.out.println("3. Create backup file");
		}else{
			System.out.println("1. Create backup file");
		}
		
		System.out.println("\n-- thirdparty.xml --");
		if (modpackIsPresent){
			if (thirdpartyIsPresent){
				System.out.println("4. Restore backup file");
				System.out.println("5. Remove backup file");
				System.out.println("6. Create backup file");
				System.out.println("\n7. Back");
			}else{
				System.out.println("4. Create backup file");
				System.out.println("\n5. Back");
			}
		}else{
			if (thirdpartyIsPresent){
				System.out.println("2. Restore backup file");
				System.out.println("3. Remove backup file");
				System.out.println("4. Create backup file");
				System.out.println("\n5. Back");
			}else{
				System.out.println("2. Create backup file");
				System.out.println("\n3. Back");
			}
		}
		
		String choice = Menu.scanner.nextLine();
		
		switch(choice){
		
		case "1":
			
			if (modpackIsPresent){
				restore(Constants.modpackBackupFile, Constants.modpackFile);
			}else{
				create(Constants.modpackFile);
			}
			break;
			
		case "2":
			if (modpackIsPresent){
				remove(Constants.modpackBackupFile, Constants.modpackFile);
			}else{
				if (thirdpartyIsPresent){
					restore(Constants.thirdpartyBackupFile, Constants.thirdpartyFile);
				}else{
					create(Constants.thirdpartyFile);
				}
			}
			break;
		
		case "3":
			if (modpackIsPresent){
				create(Constants.modpackFile);
			}else{
				if (thirdpartyIsPresent){
					remove(Constants.thirdpartyBackupFile, Constants.thirdpartyFile);
				}else{
					return;
				}
			}
			break;
			
		case "4":
			if (modpackIsPresent){
				if (thirdpartyIsPresent){
					restore(Constants.thirdpartyBackupFile, Constants.thirdpartyFile);
				}else{
					create(Constants.thirdpartyFile);
				}
			}else{
				if (thirdpartyIsPresent){
					create(Constants.thirdpartyFile);
				}
			}
			break;
		
		case "5":
			if (modpackIsPresent){
				if (thirdpartyIsPresent){
					remove(Constants.thirdpartyBackupFile, Constants.thirdpartyFile);
				}else{
					return;
				}
			}else{
				if (thirdpartyIsPresent)
					return;
				
			}
			break;
			
		case "6":
			if (modpackIsPresent)
				if (thirdpartyIsPresent)
					create(Constants.thirdpartyFile);
			break;
				
		case "7":
			if (modpackIsPresent)
				if (thirdpartyIsPresent)
					return;
		}
		manage();
	}
	
	public static void create(String file) {
		
		String backupFile = "";
		
		if (file.equals(Constants.modpackFile)){
			backupFile = Constants.modpackBackupFile;
		}else if (file.equals(Constants.thirdpartyFile)){
			backupFile = Constants.thirdpartyBackupFile;
		}else{
			System.out.println("Impossible to create backup !\n");
			return;
		}
		
		try{
			if ( new File(Constants.backupPath + backupFile).exists() )
				Files.delete(new File(Constants.backupPath + backupFile).toPath());
			Files.copy(new File(Constants.path + file).toPath(), new File(Constants.backupPath + backupFile).toPath());
			System.out.println("A backup of the " + file + " file has been made\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void restore(String backupFile, String file){
		try {
			Files.delete(new File(Constants.path + file).toPath());
			Files.copy(new File(Constants.backupPath + backupFile).toPath(), new File (Constants.path + file).toPath());
			Files.delete(new File(Constants.backupPath + backupFile).toPath());
			System.out.println("\nBackup restored successfully !");
			Menu.scanner.nextLine();
		}catch (IOException e) {
			System.out.println("\nAn error occured while trying to restore backup !");
			Menu.scanner.nextLine();
		}
	}
	
	public static void remove(String backupFile, String file) {
		try {
			System.out.println("Remove Backup (" + file + ") ? (Y/N)");
			boolean stay = true;
			String entry = "";
			while(stay){
				entry = Menu.scanner.nextLine();
				if ( entry.equals("Y") || entry.equals("y") ){
					stay = false;
				}else if ( entry.equals("N") || entry.equals("n") ){
					return;
				}else{
					System.out.print("Please choose Y or N: ");
				}
			}
			Files.delete(new File(Constants.backupPath + backupFile).toPath());
		}catch (IOException e) {
			System.out.println("\nAn orror occured while trying to restore backup !");
			Menu.scanner.nextLine();
		}
	}
}
