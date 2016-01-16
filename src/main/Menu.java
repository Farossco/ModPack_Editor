package main;

import java.util.Scanner;

import utils.*;

public class Menu {

	public static Scanner scanner;
	
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		
		Resources.newFile();
		Resources.clear();
		System.out.println("-- Modpack edition program v" + Constants.version + " - build " + Constants.build + " --\n\nWhat do you want to do ?");
		System.out.println("1. Display a modpack");
		System.out.println("2. Edit a modpack (Raw)");
		System.out.println("3. Update a modpack");
		System.out.println("4. Add a new modpack");
		System.out.println("5. Remove a modpack");
		System.out.println("6. Manage mods");
		if (Backup.isPresent(Constants.thirdpartyBackupFile) && Backup.isPresent(Constants.modpackBackupFile)){
			System.out.println("7. Manage backups (Modpacks and Thirdparty)");
		}else if (Backup.isPresent(Constants.modpackBackupFile)){
			System.out.println("7. Manage backups (Modpacks)");
		}else if (Backup.isPresent(Constants.thirdpartyBackupFile)){
			System.out.println("7. Manage backups (Thirdparty)");
		}else{
			System.out.println("7. Manage backups (No backup file)");
		}
		System.out.println("8. Quit");
		
		
		
		String choice = scanner.nextLine();
		
		switch(choice){
		
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
			Modpack.create();
			break;
			
		case "5":
			Modpack.list("remove");
			break;
			
		case "6":
			Modpack.list("manage");
			break;
			
		case "7":
			Backup.manage();
			break;
			
		case "8":
			return;
		}
		Menu.main(args);
	}
}
