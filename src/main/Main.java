package main;

import java.util.Scanner;

import utils.*;

public class Main {

	public static Scanner scanner;
	
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		
		Resources.newFile();
		Resources.clear();
		System.out.println("Welcome to the Modpack edition program\nWhat do you want to do ?");
		System.out.println("1. Display a modpack");
		System.out.println("2. Edit a modpack (Raw)");
		System.out.println("3. Update a modpack");
		System.out.println("4. Add a new modpack");
		System.out.println("5. Remove a modpack");
		if (Backup.isPresent(Locations.thirdpartyBackupFile) && Backup.isPresent(Locations.modpackBackupFile)){
			System.out.println("6. Restore backup file (modpack.xml)");
			System.out.println("7. Restore backup file (thirdparty.xml)");
			System.out.println("8. Remove backup file (modpack.xml)");
			System.out.println("9. Remove backup file (thirdparty.xml)");
			System.out.println("10. Quit");
		}else if (Backup.isPresent(Locations.modpackBackupFile)){
			System.out.println("6. Restore backup file (modpack.xml)");
			System.out.println("7. Remove backup file (modpack.xml)");
			System.out.println("8. Quit");
		}else if (Backup.isPresent(Locations.thirdpartyBackupFile)){
			System.out.println("6. Restore backup file (thirdparty.xml)");
			System.out.println("7. Remove backup file (thirdparty.xml)");
			System.out.println("8. Quit");
		}else{
			System.out.println("6. Quit");
		}
		
		
		String choixMenu = scanner.nextLine();
		
		switch(choixMenu){
		
		case "1":
			Modpack.list("display");
			break;
			
		case "2":
			Modpack.list("edit");
			break;
		
		case "3":
			Modpack.list("update");
			break;

		case "4":
			Modpack.addNew();
			break;
			
		case "5":
			Modpack.list("remove");
			break;
			
		case "6":
			if (Backup.isPresent(Locations.thirdpartyBackupFile) && Backup.isPresent(Locations.modpackBackupFile)){
				Backup.restore(Locations.modpackBackupFile, Locations.modpackFile);
			}else if (Backup.isPresent(Locations.modpackBackupFile)){
				Backup.restore(Locations.modpackBackupFile, Locations.modpackFile);
			}else if (Backup.isPresent(Locations.thirdpartyBackupFile)){
				Backup.restore(Locations.thirdpartyBackupFile, Locations.thirdpartyFile);
			}else{
				return;
			}break;
			
		case "7":
			if (Backup.isPresent(Locations.thirdpartyBackupFile) && Backup.isPresent(Locations.modpackBackupFile)){
				Backup.restore(Locations.thirdpartyBackupFile, Locations.thirdpartyFile);
			}else if (Backup.isPresent(Locations.modpackBackupFile)){
				Backup.remove(Locations.modpackBackupFile);
			}else if (Backup.isPresent(Locations.thirdpartyBackupFile)){
				Backup.remove(Locations.thirdpartyBackupFile);
			}
			break;
			
		case "8":
			if (Backup.isPresent(Locations.thirdpartyBackupFile) && Backup.isPresent(Locations.modpackBackupFile)){
				Backup.remove(Locations.modpackBackupFile);
			}else if (Backup.isPresent(Locations.modpackBackupFile)){
				return;
			}else if (Backup.isPresent(Locations.thirdpartyBackupFile)){
				return;
			}
			break;
			
		case "9":
			if (Backup.isPresent(Locations.thirdpartyBackupFile) && Backup.isPresent(Locations.modpackBackupFile)){
				Backup.remove(Locations.thirdpartyBackupFile);
			}
			break;
			
		case "10":
			if (Backup.isPresent(Locations.thirdpartyBackupFile) && Backup.isPresent(Locations.modpackBackupFile)){
				return;
			}
		}
		Main.main(args);
	}
}
