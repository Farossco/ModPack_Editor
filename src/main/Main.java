package main;

import java.util.Scanner;

import utils.NewFile;
import utils.Resources;

public class Main {

	public static Scanner scanner;
	
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		
		NewFile.main();
		Resources.clear();
		System.out.println("Welcome to the Modpack edition program\nWhat do you want to do ?");
		System.out.println("1. Display a modpack");
		System.out.println("2. Edit a modpack (Raw)");
		System.out.println("3. Update a modpack");
		System.out.println("4. Add a new modpack");
		System.out.println("5. Remove a modpack (Be careful)");
		if (Resources.backupIsPresent()){
			System.out.println("6. Restore backup file");
			System.out.println("7. Remove backup file");
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
			if (Resources.backupIsPresent()){
				Backup.restore();
			}else{
				return;
			}
			break;
			
		case "7":
			if (Resources.backupIsPresent())
				Backup.remove();
			break;
			
		case "8":
			if (Resources.backupIsPresent())
				return;
			break;
			
			
		}
		
		Main.main(args);
	}
}
