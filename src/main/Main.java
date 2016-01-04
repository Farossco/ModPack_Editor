package main;

import java.util.Scanner;

public class Main {

	public static Scanner scanner;
	
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		
		
		for (int i = 0; i < 50; ++i) System.out.println();
		
		NewFile.main();
		System.out.println("Welcome to the Modpack edition program\nWhat do you want to do ?");
		System.out.println("1. Display modpacks");
		System.out.println("2. Edit a modpack (Raw)");
		System.out.println("3. Update a modpack");
		System.out.println("4. Add a new modpack");
		System.out.println("5. Remove a modpack (Soon) (Be careful)");
		System.out.println("6. Quit");
		
		String choixMenu = scanner.nextLine();
		int back;
		String choixModpack;
		int choixModpackInt;
		
		switch(choixMenu){
		
		case "1":
			do{
				for (int i = 0; i < 50; ++i) System.out.println();
				System.out.println("What modpack do you want to display ?");
				back = DisplayModpacks.main();
				choixModpack = scanner.nextLine();
				if (choixModpack.isEmpty()) choixModpack = "0";
				choixModpackInt = Integer.parseInt(choixModpack);
			}while(choixModpackInt > back || choixModpackInt < 1);
			
			if (choixModpackInt == back) Main.main(args);
			ReadModpack.main(choixModpackInt*2-1);
			break;
			
		case "2":
			do{
				for (int i = 0; i < 50; ++i) System.out.println();
				System.out.println("What modpack do you want to edit ?");
				back = DisplayModpacks.main();
				choixModpack = scanner.nextLine();
				if (choixModpack.isEmpty()) choixModpack = "0";
				choixModpackInt = Integer.parseInt(choixModpack);
			}while(choixModpackInt > back || choixModpackInt < 1);
			
			if (choixModpackInt == back) Main.main(args);
			EditModpack.main(choixModpackInt*2-1);
			break;
		
		case "3":
			do{
				for (int i = 0; i < 50; ++i) System.out.println();
				System.out.println("What modpack do you want to update ?");
				back = DisplayModpacks.main();
				choixModpack = scanner.nextLine();
				if (choixModpack.isEmpty()) choixModpack = "0";
				choixModpackInt = Integer.parseInt(choixModpack);
				System.out.println(back);
			}while(choixModpackInt > back || choixModpackInt < 1);
			
			if (choixModpackInt == back) Main.main(args);
			UpdateModpack.main(choixModpackInt*2-1);
			break;

		case "4":
			AddNewModpack.main();
			break;
			
		case "5":
			break;
			
		case "6":
			return;
		}
		Main.main(args);
	}
}
