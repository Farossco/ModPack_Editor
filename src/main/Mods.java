package main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.Constants;
import utils.Resources;

public class Mods {

	static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	public static void display(String mods) {
		
		int j = 0;
		String mod= ""; //Mod currently analyzed
		int length = 1;
		
		try{
			for (int i=0;i<mods.length();i++)
				if (mods.charAt(i) == ';' && mods.charAt(i+1) == ' ') //Getting length of the Tab by reading the occurrences of "; "
					length++;
			
		}catch (StringIndexOutOfBoundsException e){
			mods = mods.substring(0, mods.length()-1); //If last character is ";" we just delete it
		}
		
		String modsTab[] = new String[length]; //New String with length = number of mods
		
		for (int i=0;i<mods.length();i++){
			
    		if (mods.charAt(i) == ';' && mods.charAt(i+1) == ' '){ //If we read "; " we write the mod in a new case of the Tab
    			modsTab[j] = mod;
    			j++;
    			i++; //Just jumping a case, so the space is not read
    			mod = "";
    		}else{
    			mod += mods.charAt(i); //Writing word letter by letter
    		}
    	}
		
		modsTab[j] = mod;
		j++;
		
		Resources.clear(); //Clear screen
		System.out.println("Currently present mods:\n");
		for(int i = 0; i < j; i++)
        	System.out.println("[" + (i + 1) + "] " + modsTab[i]); //Displaying all mods
		}
	
	public static String sort(String mods) {
		
		int j = 0;
		String mod= ""; //Mod currently analyzed
		int length = 1;
		
		try{
			for (int i=0;i<mods.length();i++)
				if (mods.charAt(i) == ';' && mods.charAt(i+1) == ' ') //Getting length of the Tab by reading the occurrences of "; "
					length++;
			
		}catch (StringIndexOutOfBoundsException e){
			mods = mods.substring(0, mods.length()-1); //If last character is ";" we just delete it
		}
		
		String modsTab[] = new String[length]; //New String with length = number of mods
		
		for (int i=0;i<mods.length();i++){
			
    		if (mods.charAt(i) == ';' && mods.charAt(i+1) == ' '){ //If we read "; " we write the mod in a new case of the Tab
    			modsTab[j] = mod;
    			j++;
    			i++; //Just jumping a case, so the space is not read
    			mod = "";
    		}else{
    			mod += mods.charAt(i); //Writing word letter by letter
    		}
    	}
		
		modsTab[j] = mod;
		j++;
		
		Arrays.sort(modsTab); //Sorting mods alphabetically
		
		mods = modsTab[0];
		for(int i = 1; i < modsTab.length; i++){
			mods += "; " + modsTab[i];
		}
		
		return mods;
	}
	
	public static String add(String mods, String file) {
	
		boolean stay = true;
		
		Backup.create(file); //Create Backup
		System.out.println("Please type mods you want to add: (Press \"Enter\" when you finish) ");
		
		do{
			String entry = Menu.scanner.nextLine();
		
			if (!entry.isEmpty()){
				mods += "; " + entry;
			}else{
				stay = false;
			}
		}while(stay);
		
		 return mods;
	}
	
	public static String remove(String mods, String file) {
		
		boolean stay = true;
		
		Backup.create(file); //Create Backup
		System.out.println("Please type the name of the mods you want to remove: (Press \"Enter\" when you finish) ");
		
		do{
			String entry = Menu.scanner.nextLine();
		
			if (!entry.isEmpty()){
				mods = mods.replace("; " + entry, "");
			}else{
				stay = false;
			}
		}while(stay);
		
		 return mods;
	}
	
	public static void manage(int choixmodpack, String file) {
		
		String XMLVersion = "";
		String XMLEncoding = "";

		String name = "";
		String author = "";
		String version = "";
		String repoVersion = "";
		String logo = "";
		String url = "";
		String image = "";
		String dir = "";
		String mcVersion = "";
		String serverPack = "";
		String description = "";
		String mods = "";
		String oldVersions = "";
		@SuppressWarnings("unused")
		String entry = "";

		try {
			//Reading modpacks.xml
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document modpacksDocument = builder.parse(new File(Constants.path + file));
			XMLVersion = modpacksDocument.getXmlVersion();
			XMLEncoding = modpacksDocument.getXmlEncoding();
			final Element modpacksRacine = modpacksDocument.getDocumentElement();
			final NodeList modpacksRacineNoeuds = modpacksRacine.getChildNodes();
			
			final Element modpack = (Element) modpacksRacineNoeuds.item(choixmodpack);

			name = modpack.getAttribute("name");
			author = modpack.getAttribute("author");
			version = modpack.getAttribute("version");
			repoVersion = version.replace(".", "_");
			logo = modpack.getAttribute("logo");
			url = modpack.getAttribute("url");
			image = modpack.getAttribute("image");
			dir = modpack.getAttribute("dir");
			mcVersion = modpack.getAttribute("mcVersion");
			serverPack = modpack.getAttribute("serverPack");
			description = modpack.getAttribute("description");
			mods = modpack.getAttribute("mods");
			oldVersions = modpack.getAttribute("oldVersions");
			
			//Modpack modifications
			
			boolean stay1 = true;
			boolean stay2;
			
			while(stay1 == true){
			
				mods = sort(mods);
				display(mods);
			
				/*Resources.clear(); //Clear screen
				System.out.println("Currently present mods:\n");
				for(int i = 0; i < j; i++)
	            	System.out.println("[" + (i + 1) + "] " + modsTab[i]); //Displaying all mods*/
				 
				System.out.println("\n\nWhat do you want to do ?");
				System.out.println("1. Add mods");
				System.out.println("2. Remove mods");
				System.out.println("3. Finish and apply changes");
				
				stay2 = true;
				while (stay2){	
					String choice = Menu.scanner.nextLine();
					switch(choice){
						
					case "1":
						mods = add(mods, file);
						stay2 = false;
						break;
					
					case "2":
						mods = remove(mods, file);
						stay2 = false;
						break;
				
					case "3":
						stay1 = false;
						stay2 = false;
						break;
					}
				}
			
			}
			
			mods = sort(mods);
			//Writing modpacks.xml
		
			modpack.setAttribute("name", name);
			modpack.setAttribute("author", author);
			modpack.setAttribute("version", version);
			repoVersion = version.replace(".", "_");
			modpack.setAttribute("repoVersion", repoVersion);
			modpack.setAttribute("logo", logo);
			modpack.setAttribute("url", url);
			modpack.setAttribute("image", image);
			modpack.setAttribute("dir", dir);
			modpack.setAttribute("mcVersion", mcVersion);
			modpack.setAttribute("serverPack", serverPack);
			modpack.setAttribute("description", description);
			modpack.setAttribute("mods", mods);
			modpack.setAttribute("oldVersions", oldVersions);

			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final DOMSource source = new DOMSource(modpacksDocument);
			final StreamResult sortie = new StreamResult(new File(Constants.path + file));

			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.VERSION, XMLVersion);
			transformer.setOutputProperty(OutputKeys.ENCODING, XMLEncoding);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, sortie);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
}
