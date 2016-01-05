package main;

public class Resources {
	
	//Clear the screen (have to be modified)
	public static void clear(){
		for (int i = 0; i < 50; ++i)
			System.out.println();
	}
	
	//Detect if the backup file is present
	public static boolean backupIsPresent(){
		java.io.File monFichier = new java.io.File(Locations.backupPath + Locations.backupFile);
		if (monFichier.exists()) {
			return true;
		}else{
			return false;
		}
	}
	
}
