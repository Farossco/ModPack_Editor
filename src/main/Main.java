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
		System.out.println("6. Quit");
		
		String choixMenu = scanner.nextLine();
		
		switch(choixMenu){
		
		case "1":
			ReadModpacks.main("display");
			break;
			
		case "2":
			ReadModpacks.main("edit");
			break;
		
		case "3":
			ReadModpacks.main("update");
			break;

		case "4":
			AddNewModpack.main();
			break;
			
		case "5":
			ReadModpacks.main("remove");
			break;
			
		case "6":
			return;
		}
		Main.main(args);
	}
}
