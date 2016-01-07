package main;

import java.io.File;
import java.io.IOException;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.*;

public class Modpack {
	
	static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	public static void list(String choice) {

		String choixModpack = "";
		int choixModpackInt, i, j;
		int back;
		int modpackRecurrence = 0;
		int thirdpartyRecurrence = 0;
		
		try {
			do{
				modpackRecurrence = 0;
				thirdpartyRecurrence = 0;
				Resources.clear();
				System.out.println("What modpack do you want to " + choice + " ?");
				
			    final DocumentBuilder builder = factory.newDocumentBuilder();
			    
			    final Document modpacksDocument = builder.parse(new File(Locations.path + Locations.modpackFile));
				final Element modpacksRacine = modpacksDocument.getDocumentElement();
				final NodeList modpacksRacineNoeuds = modpacksRacine.getChildNodes();
				final int modpacksNbRacineNoeuds = modpacksRacineNoeuds.getLength();
				
				System.out.println("\n-- modpacks.xml --");
				for (i = 0; i<modpacksNbRacineNoeuds; i++) {
				    if(modpacksRacineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
				        final Element modpack = (Element) modpacksRacineNoeuds.item(i);
				        modpackRecurrence++;
				        System.out.println(modpackRecurrence + ". " + modpack.getAttribute("name") + " (v" + modpack.getAttribute("version") + " / MC version: " + modpack.getAttribute("mcVersion") + ")");
				    }				
				}
				if (modpackRecurrence == 0){
					i = 1;
					System.out.println("- No modpack detected");
				}

				final Document thirdpartyDocument = builder.parse(new File(Locations.path + Locations.thirdpartyFile));
				final Element thirdpartyRacine = thirdpartyDocument.getDocumentElement();
				final NodeList thirdpartyRacineNoeuds = thirdpartyRacine.getChildNodes();
				final int thirdpartyNbRacineNoeuds = thirdpartyRacineNoeuds.getLength();
				
				System.out.println("\n-- thirdparty.xml --");
				for (j = 0; j<thirdpartyNbRacineNoeuds; j++) {
				    if(thirdpartyRacineNoeuds.item(j).getNodeType() == Node.ELEMENT_NODE) {
				        final Element modpack = (Element) thirdpartyRacineNoeuds.item(j);
				        thirdpartyRecurrence ++;
						System.out.println( modpackRecurrence + thirdpartyRecurrence + ". " + modpack.getAttribute("name") + " (v" + modpack.getAttribute("version") + " / MC version: " + modpack.getAttribute("mcVersion") + ")");
				    }				
				}
				if (thirdpartyRecurrence == 0){
					System.out.println("- No modpack detected");
				}
				
				back = modpackRecurrence + thirdpartyRecurrence + 1;
				
				System.out.println("\n" + back + ". Back");
				
				choixModpack = Menu.scanner.nextLine();
				if (choixModpack.isEmpty()) choixModpack = "0";
				choixModpackInt = Integer.parseInt(choixModpack);
			}while(choixModpackInt > back || choixModpackInt < 1);
			
			if (choixModpackInt == back){
				return;
				
			}else if (choixModpackInt < (i+1)/2 && choixModpackInt >= 0){
				
				switch(choice){
				case "display":
					Modpack.display(choixModpackInt*2-1,  Locations.modpackFile);
					break;

				case "edit":
					Modpack.edit(choixModpackInt*2-1,  Locations.modpackFile);
					break;

				case "update":
					Modpack.update(choixModpackInt*2-1,  Locations.modpackFile);
					break;

				case "remove":
					Modpack.remove(choixModpackInt*2-1,  Locations.modpackFile);
					break;

				case "manage":
					Mods.manage(choixModpackInt*2-1,  Locations.modpackFile);
					break;
				}
				
			}else if(choixModpackInt >= (i+1)/2 && choixModpackInt < back){
				
				switch(choice){
				case "display":
					Modpack.display( (choixModpackInt - (i-1)/2) * 2 - 1, Locations.thirdpartyFile);
					break;

				case "edit":
					Modpack.edit( (choixModpackInt - (i-1)/2) * 2 - 1, Locations.thirdpartyFile);
					break;

				case "update":
					Modpack.update( (choixModpackInt - (i-1)/2) * 2 - 1, Locations.thirdpartyFile);
					break;

				case "remove":
					Modpack.remove( (choixModpackInt - (i-1)/2) * 2 - 1, Locations.thirdpartyFile);
					break;

				case "manage":
					Mods.manage( (choixModpackInt - (i-1)/2) * 2 - 1, Locations.thirdpartyFile);
					break;
				}
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			Modpack.list(choice);
		}return;
	}
	
	public static void display(int choixmodpack, String file) {

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

		

		try {
			System.out.println(choixmodpack);
			final DocumentBuilder builder = factory.newDocumentBuilder();
			
			
			final Document modpacksDocument = builder.parse(new File(Locations.path + file));
			final Element modpacksRacine = modpacksDocument.getDocumentElement();
			final NodeList modpacksRacineNoeuds = modpacksRacine.getChildNodes();
			
			final Element modpack = (Element) modpacksRacineNoeuds.item(choixmodpack);

			name = modpack.getAttribute("name");
			author = modpack.getAttribute("author");
			version = modpack.getAttribute("version");
			repoVersion = modpack.getAttribute("repoVersion");
			logo = modpack.getAttribute("logo");
			url = modpack.getAttribute("url");
			image = modpack.getAttribute("image");
			dir = modpack.getAttribute("dir");
			mcVersion = modpack.getAttribute("mcVersion");
			serverPack = modpack.getAttribute("serverPack");
			description = modpack.getAttribute("description");
			mods = modpack.getAttribute("mods");
			oldVersions = modpack.getAttribute("oldVersions");
			
			Resources.clear();
			
			System.out.println("name: " + name);
			System.out.println("author: " + author);
			System.out.println("version: " + version);
			System.out.println("repoVersion: " + repoVersion);
			System.out.println("logo: " + logo);
			System.out.println("url: " + url);
			System.out.println("image: " + image);
			System.out.println("dir: " + dir);
			System.out.println("mcVersion: " + mcVersion);
			System.out.println("serverPack: " + serverPack);
			System.out.println("description: " + description);
			System.out.print("Mods: ");
			if (mods.isEmpty()){
				System.out.println("None");
			}else{
				System.out.println("\n- " + mods.replace("; ", "\n- "));
			}
			System.out.println("oldVersions: " + oldVersions);
			
			System.out.println("\n\nPress \"Enter\" to continue");
			Menu.scanner.nextLine();
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void edit(int choixmodpack, String file) {

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
		String entry = "";

		try {
			//Reading modpacks.xml
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document modpacksDocument = builder.parse(new File(Locations.path + file));
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
			
			String choixEdition = "";
			do{	
			
				Resources.clear();
				
				System.out.println("1. name: " + name);
				System.out.println("2. author: " + author);
				System.out.println("3. version: " + version);
				System.out.println("4. logo: " + logo);
				System.out.println("5. url: " + url);
				System.out.println("6. image: " + image);
				System.out.println("7. dir: " + dir);
				System.out.println("8. mcVersion: " + mcVersion);
				System.out.println("9. serverPack: " + serverPack);
				System.out.println("10. description: " + description);
				System.out.println("11. mods: " + mods);
				System.out.println("12. oldVersions: " + oldVersions);
				
				System.out.println("\nWhat value do you want to edit ? (Press \"Enter\" when you finish)");
				System.out.println("Leave blank to keep initials values\n");
				do{
					choixEdition = Menu.scanner.nextLine();
				}while ( !choixEdition.equals("1") && !choixEdition.equals("2") && !choixEdition.equals("3") && !choixEdition.equals("4") && !choixEdition.equals("5") && !choixEdition.equals("6") && !choixEdition.equals("7") && !choixEdition.equals("8") && !choixEdition.equals("9") && !choixEdition.equals("10") && !choixEdition.equals("11") && !choixEdition.equals("12") && !choixEdition.equals("13") && !choixEdition.isEmpty() );
				
				if (!choixEdition.isEmpty()){
					
					switch(choixEdition){
					case "1":
						System.out.println("Old value: " + name + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && name.isEmpty());
						
						if (!entry.isEmpty()){
							name = entry;
						}
						modpack.setAttribute("name", name);
						break;
					case "2":
						System.out.println("Old value: " + author + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && author.isEmpty());
						
						if (!entry.isEmpty()){
							author = entry;
						}
						modpack.setAttribute("author", author);
						break;
					case "3":
						System.out.println("Old value: " + version + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && version.isEmpty());
						
						if (!entry.isEmpty()){
							version = entry;
						}
						modpack.setAttribute("version", version);
						break;
					case "4":
						System.out.println("Old value: " + logo + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && logo.isEmpty());
						
						if (!entry.isEmpty()){
							logo = entry;
						}
						modpack.setAttribute("logo", logo);
						break;
					case "5":
						System.out.println("Old value: " + url + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && url.isEmpty());
						
						if (!entry.isEmpty()){
							url = entry;
						}
						modpack.setAttribute("url", url);
						break;
					case "6":
						System.out.println("Old value: " + image + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && image.isEmpty());
						
						if (!entry.isEmpty()){
							image = entry;
						}
						modpack.setAttribute("image", image);
						break;
					case "7":
						System.out.println("Old value: " + dir + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && dir.isEmpty());
						
						if (!entry.isEmpty()){
							dir = entry;
						}
						modpack.setAttribute("dir", dir);
						break;
					case "8":
						System.out.println("Old value: " + mcVersion + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && mcVersion.isEmpty());
						
						if (!entry.isEmpty()){
							mcVersion = entry;
						}
						modpack.setAttribute("mcVersion", mcVersion);
						break;
					case "9":
						System.out.println("Old value: " + serverPack + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && serverPack.isEmpty());
						
						if (!entry.isEmpty()){
							serverPack = entry;
						}
						modpack.setAttribute("serverPack", serverPack);
						break;
					case "10":
						System.out.println("Old value: " + description + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && description.isEmpty());
						
						if (!entry.isEmpty()){
							description = entry;
						}
						modpack.setAttribute("description", description);
						break;
					case "11":
						System.out.println("Old value: " + mods + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && mods.isEmpty());
						
						if (!entry.isEmpty()){
							mods = entry;
						}
						modpack.setAttribute("mods", mods);
						break;
					case "12":
						System.out.println("Old value: " + oldVersions + "\n");
						do{
							entry = Menu.scanner.nextLine();
						}while(entry.isEmpty() && oldVersions.isEmpty());
						
						if (!entry.isEmpty()){
							oldVersions = entry;
						}
						modpack.setAttribute("oldVersions", oldVersions);
						break;
					}
				}
			}while( !choixEdition.isEmpty() );
		
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
			final StreamResult sortie = new StreamResult(new File(Locations.path + file));

			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.VERSION, XMLVersion);
			transformer.setOutputProperty(OutputKeys.ENCODING, XMLEncoding);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, sortie);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(int choixmodpack, String file) {

		String XMLVersion = "";
		String XMLEncoding = "";

		String version = "";
		String repoVersion = "";
		String oldVersions = "";
		
		try {
			//Reading modpacks.xml
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document modpacksDocument = builder.parse(new File(Locations.path + file));
			XMLVersion = modpacksDocument.getXmlVersion();
			XMLEncoding = modpacksDocument.getXmlEncoding();
			final Element modpacksRacine = modpacksDocument.getDocumentElement();
			final NodeList modpacksRacineNoeuds = modpacksRacine.getChildNodes();
			
			final Element modpack = (Element) modpacksRacineNoeuds.item(choixmodpack);

			version = modpack.getAttribute("version");
			repoVersion = version.replace(".", "_");
			oldVersions = modpack.getAttribute("oldVersions");
			
			//Modpack modifications
			
			String newVersion = "";
			
			Resources.clear();
			System.out.println("Current version: " + version);
			System.out.println("\nPlease enter new version (\"Enter\" to cancel)");
			
			newVersion = Menu.scanner.nextLine();
			while (newVersion.matches(".*[a-zA-Z�&�\"\'(�_��)=,;:!?/��*^$��%�+�~#{|`\\^@}].*") || newVersion.contains(" ") ){
				System.out.println("Incorrect format. Please use only number and dots");
				newVersion = Menu.scanner.nextLine();
			}
			
			if (!newVersion.isEmpty()){
				version = newVersion;
				repoVersion = version.replace(".", "_");
				oldVersions = version + ";" + oldVersions;
			}else{
				return;
			}
			
		
			//Writing modpacks.xml
		
			modpack.setAttribute("version", version);
			modpack.setAttribute("repoVersion", repoVersion);
			modpack.setAttribute("oldVersions", oldVersions);

			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final DOMSource source = new DOMSource(modpacksDocument);
			final StreamResult sortie = new StreamResult(new File(Locations.path + file));

			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.VERSION, XMLVersion);
			transformer.setOutputProperty(OutputKeys.ENCODING, XMLEncoding);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, sortie);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static void create() {
		
		String entry;
		String file = "";
		boolean stay = true;
		
		while (stay){
			
			Resources.clear();
			System.out.println("In what file do you want to add modpack ?");
			System.out.println("1. modpacks.xml");
			System.out.println("2. thirdparty.xml");
			entry = Menu.scanner.nextLine();
			
			switch(entry){
			
			case "1":
				file = Locations.modpackFile;
				stay = false;
				break;
			case "2":
				file = Locations.thirdpartyFile;
				stay = false;
				break;
			}
		}

		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document modpacksDocument = builder.parse(new File(Locations.path + file));
			final Element modpacksRacine = modpacksDocument.getDocumentElement();
			final Element modpack = modpacksDocument.createElement("modpack");
			modpacksRacine.appendChild(modpack);
			
			String XMLVersion = "";
			String XMLEncoding = "";
			
			XMLVersion = modpacksDocument.getXmlVersion();
			XMLEncoding = modpacksDocument.getXmlEncoding();
			
			String name = "NoName";
			String author = "Anonymous";
			String version = "1.0.0";
			String repoVersion;
			String logo;
			String url;
			String image;
			String dir;
			String mcVersion = "1.7.10";
			String serverPack = "";
			String provideServer = "N";
			boolean provideServerboolean = false;
			String provideServerString = "No";
			String description = "";
			String mods = "";
			String oldVersions;
			String shortname = "";
			String ValidateInfo = "";
			
			do{
				Resources.clear();
				System.out.println("Welcome to the modpack adding program");
				if (!name.isEmpty()){
					System.out.println("Leave blank to keep initials values\n");
				}else{
					System.out.println("\n");
				}
				
				System.out.println("Modpack name: " + name);
				entry = Menu.scanner.nextLine();
				
				if (!entry.isEmpty()){
					name = entry;
				}
				
				//Auto-generate shortname proposition
				char tempchar;
				for ( int i=0;i<name.length();i++){
					tempchar = name.charAt(i);
		    		
		    		if (Character.isSpaceChar(tempchar)){
		    			break;
		    		}else if ("bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ".contains(String.valueOf(tempchar))){
		    			shortname += tempchar;
		    		}
		    	}shortname = shortname.toUpperCase();
				
				System.out.println("Shortened modpack name (TNT, FTB...): " + shortname);
				entry = Menu.scanner.nextLine();
				
				if (!entry.isEmpty()){
					shortname = entry;
				}
				
				System.out.println("Modpack author: " + author);
				entry = Menu.scanner.nextLine();
				
				if (!entry.isEmpty()){
					author = entry;
				}
				
				System.out.println("Initial version (x / x.x / x.x.x ...): " + version);
				entry = Menu.scanner.nextLine();
				while (entry.matches(".*[a-zA-Z�&�\"\'(�_��)=,;:!?/��*^$��%�+�~#{|`\\^@}].*") || entry.contains(" ") ){
					System.out.print("Incorrect format. Please use only number and dots: ");
					entry = Menu.scanner.nextLine();
				}
				
				if (!entry.isEmpty()){
					version = entry;
				}
				
				repoVersion = version.replace(".", "_");
				oldVersions = version;
				
				url = shortname + ".zip";
				logo = shortname + "Logo.png";
				image = shortname + "Splash.png";
				dir = shortname;
				
				System.out.println("Minecraft version (1.x.x): " + mcVersion);
				entry = Menu.scanner.nextLine();
				while (entry.matches(".*[a-zA-Z�&�\"\'(�_��)=,;:!?/��*^$��%�+�~#{|`\\^@}].*") || entry.contains(" ") ){
					System.out.print("Incorrect format. Please use only number and dots: ");
					entry = Menu.scanner.nextLine();
				}
				
				if (!entry.isEmpty()){
					mcVersion = entry;
				}
				
				System.out.println("Provide server version ? (Y/N): " + provideServer.toUpperCase());
				stay = true;
				while(stay){
					entry = Menu.scanner.nextLine();
					if ("Yy".contains(String.valueOf(entry))){
						provideServer = entry;
						provideServerboolean = true;
						provideServerString = "Yes";
						serverPack = shortname + "Server.zip";
						stay = false;
					}else if ("Nn".contains(String.valueOf(entry))){
						provideServer = entry;
						provideServerboolean = false;
						provideServerString = "No";
						serverPack = "";
						stay = false;
					}else if( entry.isEmpty()){
						stay = false;
					}else{
						System.out.print("Please choose Y or N: ");
					}
				}
				
				description = name + " by " + author;
				
				System.out.println("Modpack description: " + description);
				entry = Menu.scanner.nextLine();
				
				if (!entry.isEmpty())
					description = entry;
				
				System.out.println("Mods: (Press \"Enter\" when you finish) " + mods);
				entry = Menu.scanner.nextLine();
				
				if (!entry.isEmpty()){
					mods = entry;
				}
				
				while( !entry.isEmpty() ){
					entry = Menu.scanner.nextLine();
					if (!entry.isEmpty()) mods = mods + "; " + entry;
				}
				
				Resources.clear();
				System.out.println("Summary:");
				System.out.println("Modpack name: " + name);
				System.out.println("Shortened modpack name: " + shortname);
				System.out.println("Modpack author: " + author);
				System.out.println("Initial version: " + version);
				System.out.println("Minecraft version: " + mcVersion);
				System.out.println("Provide server version ? " + provideServerString);
				System.out.print("Mods: ");
				if (mods.isEmpty()){
					System.out.println("None");
				}else{
					System.out.println("\n " + mods.replace("; ", "\n- "));
				}
				System.out.println("Modpack description: " + description);
				
				System.out.println("\nFurther information: ");
				System.out.println("Logo file (Image at the left of the modpack): /FTB2/" + logo);
				System.out.println("Image file (Image at the top of the description): /FTB2/" + image);
				System.out.println("Zip file path: /FTB2/modpacks/" + dir + "/" + repoVersion + "/" + url);
				if (provideServerboolean){
					System.out.println("Name and path of server zip: /FTB2/modpacks/" + dir + "/" + repoVersion + "/" + serverPack);
				}else{
					System.out.println("Server file not provided");
				}
				
				System.out.println("\nValidate informations ? (Y/N)");
				
				stay = true;
				while(stay){
					ValidateInfo = Menu.scanner.nextLine();
					if ("YyNn".contains(String.valueOf(ValidateInfo)) && !ValidateInfo.isEmpty()){
						stay = false;
					}else{
						System.out.print("Please choose Y or N: ");
					}
				}
				
			}while( ValidateInfo.equals("N") || ValidateInfo.equals("n") );
			
			
			
			modpack.setAttribute("name", name);
			modpack.setAttribute("author", author);
			modpack.setAttribute("version", version);
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
			final StreamResult sortie = new StreamResult(new File(Locations.path + file));

			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.VERSION, XMLVersion);
			transformer.setOutputProperty(OutputKeys.ENCODING, XMLEncoding);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, sortie);
			
		}  catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void remove(int choixmodpack, String file) {
	
		String XMLVersion = "";
		String XMLEncoding = "";
		
		String name = "";
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			//Reading modpacks.xml
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document modpacksDocument = builder.parse(new File(Locations.path + file));
			XMLVersion = modpacksDocument.getXmlVersion();
			XMLEncoding = modpacksDocument.getXmlEncoding();
			final Element modpacksRacine = modpacksDocument.getDocumentElement();
			final NodeList modpacksRacineNoeuds = modpacksRacine.getChildNodes();
			
			final Element modpack = (Element) modpacksRacineNoeuds.item(choixmodpack);
			
			name = modpack.getAttribute("name");
			
			Resources.clear();
			
			if (file.equals(Locations.modpackFile)){
				Backup.create(Locations.modpackBackupFile, Locations.modpackFile);
			}else if (file.equals(Locations.thirdpartyFile)){
				Backup.create(Locations.thirdpartyBackupFile, Locations.thirdpartyFile);
			}else{
				System.out.println("Impossible to create backup !");
			}
				
			
			System.out.println("Are you sure you want to delete \"" + name + "\" ? (Y/N) ");
			boolean stay = true;
			String entry = "";
			while(stay){
				entry = Menu.scanner.nextLine();
				if ( entry.equals("Y") || entry.equals("y") ){
					stay = false;
				}else if ( entry.equals("N") || entry.equals("n") ){
					return;
				}else{
					System.out.print("Please choose Y or N: ");
				}
			}
			
			//Modpack modifications
			modpacksRacine.removeChild(modpack);
			
			//Writing modpacks.xml
	
			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final DOMSource source = new DOMSource(modpacksDocument);
			final StreamResult sortie = new StreamResult(new File(Locations.path + file));
	
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
