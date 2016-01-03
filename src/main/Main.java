package main;

import java.util.Scanner;
import main.Read;

public class Main {

	static Scanner scanner;

	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		
		for (int i = 0; i < 50; ++i) System.out.println();
		System.out.println("Bienvenue dans le programme d'édition de modpacks\nQue voulez-vous faire ?");
		System.out.println("1. Ajouter un modpack (Soon)");
		System.out.println("2. Supprimer un modpack (Soon) (Fais pas le con)");
		System.out.println("3. Editer un modpack");
		System.out.println("4. Afficher un modpack (Soon)");
		System.out.println("5. Quitter");
		
		String choixMenu = scanner.nextLine();
		
		switch(choixMenu){
		case "1":
			break;
			
		case "2":
			break;
			
		case "3":
			for (int i = 0; i < 50; ++i) System.out.println();
			System.out.println("Quel modpack souhaitez-vous éditer ?");
			Read.main();
			String choixModpack = scanner.nextLine();
			Edit.main(Integer.parseInt(choixModpack)*2-1);
			break;
			
		case "4":
			break;
			
		case "5":
			return;
		default:
			Main.main(args);
		}
		Main.main(args);
	}
}
