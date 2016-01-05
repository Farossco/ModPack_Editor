package main;

import java.util.Scanner;

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
			ListModpacks.main("display");
			break;
			
		case "2":
			ListModpacks.main("edit");
			break;
		
		case "3":
			ListModpacks.main("update");
			break;

		case "4":
			AddNewModpack.main();
			break;
			
		case "5":
			ListModpacks.main("remove");
			break;
			
		case "6":
			if (Resources.backupIsPresent()){
				RestoreBackup.main();
			}else{
				return;
			}
			break;
			
		case "7":
			if (Resources.backupIsPresent())
				RemoveBackup.main();
			break;
			
		case "8":
			if (Resources.backupIsPresent())
				return;
			break;
			
			
		}
		
		Main.main(args);
	}
}
