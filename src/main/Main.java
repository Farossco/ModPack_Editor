package main;

import java.util.Scanner;

public class Main {

	public static Scanner scanner;
	
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		
		
		for (int i = 0; i < 50; ++i) System.out.println();
		
		NewFile.main();
		System.out.println("Bienvenue dans le programme d'édition de modpacks\nQue voulez-vous faire ?");
		System.out.println("1. Afficher un modpack");
		System.out.println("2. Editer un modpack (Brute)");
		System.out.println("3. Mettre à jour un modpack");
		System.out.println("4. Ajouter un modpack");
		System.out.println("5. Supprimer un modpack (Soon) (Fais pas le con)");
		System.out.println("6. Quitter");
		
		String choixMenu = scanner.nextLine();
		int back;
		String choixModpack;
		int choixModpackInt;
		
		switch(choixMenu){
		
		case "1":
			do{
				for (int i = 0; i < 50; ++i) System.out.println();
				System.out.println("Quel modpack souhaitez-vous afficher ?");
				back = ShowModpacks.main();
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
				System.out.println("Quel modpack souhaitez-vous éditer ?");
				back = ShowModpacks.main();
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
				System.out.println("Quel modpack souhaitez-vous mettre à jour ?");
				back = ShowModpacks.main();
				choixModpack = scanner.nextLine();
				if (choixModpack.isEmpty()) choixModpack = "0";
				choixModpackInt = Integer.parseInt(choixModpack);
				System.out.println(back);
			}while(choixModpackInt > back || choixModpackInt < 1);
			
			if (choixModpackInt == back) Main.main(args);
			UpdateModpack.main(choixModpackInt*2-1);
			break;

		case "4":
			CreateModpack.main();
			break;
			
		case "5":
			break;
			
		case "6":
			return;
		}
		Main.main(args);
	}
}
