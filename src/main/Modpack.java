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

import utils.Locations;
import utils.Resources;

public class Modpack {
	
	public static int list(String choice) {

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		String choixModpack = "";
		int choixModpackInt, i;
		int back;
		
		try {
			do{
				for (i = 0; i < 50; ++i) System.out.println();
				System.out.println("What modpack do you want to " + choice + " ?");
				
			    final DocumentBuilder builder = factory.newDocumentBuilder();		
			    final Document document = builder.parse(new File(Locations.path + Locations.modpackFile));
				
				final Element racine = document.getDocumentElement();
				final NodeList racineNoeuds = racine.getChildNodes();
				
				final int nbRacineNoeuds = racineNoeuds.getLength();
				
				for (i = 0; i<nbRacineNoeuds; i++) {
				    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
				        final Element modpack = (Element) racineNoeuds.item(i);
					System.out.println((i+1)/2 + ". " + modpack.getAttribute("name") + " (v" + modpack.getAttribute("version") + " / MC version: " + modpack.getAttribute("mcVersion") + ")");
				    }				
				}
				back = (i+1)/2;
				System.out.println(back + ". Back");
				
				choixModpack = Main.scanner.nextLine();
				if (choixModpack.isEmpty()) choixModpack = "0";
				choixModpackInt = Integer.parseInt(choixModpack);
			}while(choixModpackInt > back || choixModpackInt < 1);
			
			if (choixModpackInt == back) return 0;
			
			
			if (choice.equals("display")){
				Modpack.display(choixModpackInt*2-1);
			}else if (choice.equals("edit")){
				Modpack.edit(choixModpackInt*2-1);
			}else if (choice.equals("update")){
				Modpack.update(choixModpackInt*2-1);
			}else if (choice.equals("remove")){
				Modpack.remove(choixModpackInt*2-1);
			}
			
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}return 0;
	}
	
	public static void display(int choixmodpack) {

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
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(Locations.path + Locations.modpackFile));
			final Element racine = document.getDocumentElement();
			final NodeList racineNoeuds = racine.getChildNodes();
			
			final Element modpack = (Element) racineNoeuds.item(choixmodpack);

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
			Main.scanner.nextLine();
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void edit(int choixmodpack) {

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

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			//Reading modpacks.xml
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(Locations.path + Locations.modpackFile));
			XMLVersion = document.getXmlVersion();
			XMLEncoding = document.getXmlEncoding();
			final Element racine = document.getDocumentElement();
			final NodeList racineNoeuds = racine.getChildNodes();
			
			final Element modpack = (Element) racineNoeuds.item(choixmodpack);

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
					choixEdition = Main.scanner.nextLine();
				}while ( !choixEdition.equals("1") && !choixEdition.equals("2") && !choixEdition.equals("3") && !choixEdition.equals("4") && !choixEdition.equals("5") && !choixEdition.equals("6") && !choixEdition.equals("7") && !choixEdition.equals("8") && !choixEdition.equals("9") && !choixEdition.equals("10") && !choixEdition.equals("11") && !choixEdition.equals("12") && !choixEdition.equals("13") && !choixEdition.isEmpty() );
				
				if (!choixEdition.isEmpty()){
					
					switch(choixEdition){
					case "1":
						System.out.println("Old value: " + name + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && name.isEmpty());
						
						if (!entry.isEmpty()){
							name = entry;
						}
						modpack.setAttribute("name", name);
						break;
					case "2":
						System.out.println("Old value: " + author + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && author.isEmpty());
						
						if (!entry.isEmpty()){
							author = entry;
						}
						modpack.setAttribute("author", author);
						break;
					case "3":
						System.out.println("Old value: " + version + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && version.isEmpty());
						
						if (!entry.isEmpty()){
							version = entry;
						}
						modpack.setAttribute("version", version);
						break;
					case "4":
						System.out.println("Old value: " + logo + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && logo.isEmpty());
						
						if (!entry.isEmpty()){
							logo = entry;
						}
						modpack.setAttribute("logo", logo);
						break;
					case "5":
						System.out.println("Old value: " + url + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && url.isEmpty());
						
						if (!entry.isEmpty()){
							url = entry;
						}
						modpack.setAttribute("url", url);
						break;
					case "6":
						System.out.println("Old value: " + image + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && image.isEmpty());
						
						if (!entry.isEmpty()){
							image = entry;
						}
						modpack.setAttribute("image", image);
						break;
					case "7":
						System.out.println("Old value: " + dir + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && dir.isEmpty());
						
						if (!entry.isEmpty()){
							dir = entry;
						}
						modpack.setAttribute("dir", dir);
						break;
					case "8":
						System.out.println("Old value: " + mcVersion + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && mcVersion.isEmpty());
						
						if (!entry.isEmpty()){
							mcVersion = entry;
						}
						modpack.setAttribute("mcVersion", mcVersion);
						break;
					case "9":
						System.out.println("Old value: " + serverPack + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && serverPack.isEmpty());
						
						if (!entry.isEmpty()){
							serverPack = entry;
						}
						modpack.setAttribute("serverPack", serverPack);
						break;
					case "10":
						System.out.println("Old value: " + description + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && description.isEmpty());
						
						if (!entry.isEmpty()){
							description = entry;
						}
						modpack.setAttribute("description", description);
						break;
					case "11":
						System.out.println("Old value: " + mods + "\n");
						do{
							entry = Main.scanner.nextLine();
						}while(entry.isEmpty() && mods.isEmpty());
						
						if (!entry.isEmpty()){
							mods = entry;
						}
						modpack.setAttribute("mods", mods);
						break;
					case "12":
						System.out.println("Old value: " + oldVersions + "\n");
						do{
							entry = Main.scanner.nextLine();
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
			final DOMSource source = new DOMSource(document);
			final StreamResult sortie = new StreamResult(new File(Locations.path + Locations.modpackFile));

			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.VERSION, XMLVersion);
			transformer.setOutputProperty(OutputKeys.ENCODING, XMLEncoding);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, sortie);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(int choixmodpack) {

		String XMLVersion = "";
		String XMLEncoding = "";

		String version = "";
		String repoVersion = "";
		String oldVersions = "";

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			//Reading modpacks.xml
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(Locations.path + Locations.modpackFile));
			XMLVersion = document.getXmlVersion();
			XMLEncoding = document.getXmlEncoding();
			final Element racine = document.getDocumentElement();
			final NodeList racineNoeuds = racine.getChildNodes();
			
			final Element modpack = (Element) racineNoeuds.item(choixmodpack);

			version = modpack.getAttribute("version");
			repoVersion = version.replace(".", "_");
			oldVersions = modpack.getAttribute("oldVersions");
			
			//Modpack modifications
			
			String newVersion = "";
			
			Resources.clear();
			System.out.println("Current version: " + version);
			System.out.println("\nPlease enter new version (\"Enter\" to cancel)");
			
			newVersion = Main.scanner.nextLine();
			while (newVersion.matches(".*[a-zA-Z²&é\"\'(è_çà)=,;:!?/§ù*^$¨£%µ+°~#{|`\\^@}].*") || newVersion.contains(" ") ){
				System.out.println("Incorrect format. Please use only number and dots");
				newVersion = Main.scanner.nextLine();
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
			final DOMSource source = new DOMSource(document);
			final StreamResult sortie = new StreamResult(new File(Locations.path + Locations.modpackFile));

			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.VERSION, XMLVersion);
			transformer.setOutputProperty(OutputKeys.ENCODING, XMLEncoding);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, sortie);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static void addNew() {
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(Locations.path + Locations.modpackFile));
			final Element racine = document.getDocumentElement();
			final Element modpack = document.createElement("modpack");
			racine.appendChild(modpack);
			
			String XMLVersion = "";
			String XMLEncoding = "";
			
			XMLVersion = document.getXmlVersion();
			XMLEncoding = document.getXmlEncoding();
			
			String entry = "";
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
			String provideServer = "";
			boolean provideServerboolean = false;
			String provideServerString = "";
			String description = "";
			String mods = "";
			String oldVersions = "";
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
				do{
					entry = Main.scanner.nextLine();
				}while(entry.isEmpty() && name.isEmpty());
				
				if (!entry.isEmpty()){
					name = entry;
				}
				
				System.out.println("Shortened modpack name (TNT, FTB...): " + shortname);
				do{
					entry = Main.scanner.nextLine();
				}while(entry.isEmpty() && shortname.isEmpty());
				
				if (!entry.isEmpty()){
					shortname = entry;
				}
				
				System.out.println("Modpack author: " + author);
				do{
					entry = Main.scanner.nextLine();
				}while(entry.isEmpty() && author.isEmpty());
				
				if (!entry.isEmpty()){
					author = entry;
				}
				
				System.out.println("Initial version (x / x.x / x.x.x ...): " + version);
				entry = Main.scanner.nextLine();
				while (entry.matches(".*[a-zA-Z²&é\"\'(è_çà)=,;:!?/§ù*^$¨£%µ+°~#{|`\\^@}].*") || entry.contains(" ") || ( entry.isEmpty() && version.isEmpty() ) ){
					System.out.println("Incorrect format. Please use only number and dots");
					entry = Main.scanner.nextLine();
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
				
				System.out.println("Minecraft version: (1.x.x) " + mcVersion);
				entry = Main.scanner.nextLine();
				while (entry.matches(".*[a-zA-Z²&é\"\'(è_çà)=,;:!?/§ù*^$¨£%µ+°~#{|`\\^@}].*") || entry.contains(" ") || ( entry.isEmpty() && mcVersion.isEmpty() ) ){
					System.out.println("Incorrect format. Please use only number and dots");
					entry = Main.scanner.nextLine();
				}
				
				if (!entry.isEmpty()){
					mcVersion = entry;
				}
				
				System.out.println("Provide server version ? (Y/N) " + provideServer.toUpperCase());
				boolean stay = true;
				while(stay){
					entry = Main.scanner.nextLine();
					if ( entry.equals("Y") || entry.equals("y") ){
						provideServer = entry;
						provideServerboolean = true;
						provideServerString = "Yes";
						serverPack = shortname + "Server.zip";
						stay = false;
					}else if ( entry.equals("N") || entry.equals("n") ){
						provideServer = entry;
						provideServerboolean = false;
						provideServerString = "No";
						serverPack = "";
						stay = false;
					}else if( entry.isEmpty() ){
						stay = false;
					}else{
						System.out.print("Please choose Y or N: ");
					}
				}
				
				System.out.println("Modpack description: " + description);
				entry = Main.scanner.nextLine();
				
				if (!entry.isEmpty()){
					description = entry;
				}else if(description.isEmpty()){
					description = name + " by " + author;
				}
				
				System.out.println("Mods presents in the modpack: (Press \"Enter\" when you finish) " + mods);
				
				entry = Main.scanner.nextLine();
				
				if (!entry.isEmpty()){
					mods = entry;
				}
				
				while( !entry.isEmpty() ){
					entry = Main.scanner.nextLine();
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
					System.out.println("\n " + mods.replace("; ", "\n "));
				}
				System.out.println("Modpack description: " + description);
				
				System.out.println("\nFurther information: ");
				System.out.println("Logo file name (Image at the left of the modpack): " + logo);
				System.out.println("Image file name (Image at the top of the description): " + image);
				System.out.println("Name and path of client zip file: /FTB2/modpacks/" + dir + "/" + repoVersion + "/" + url);
				if (provideServerboolean){
					System.out.println("Name and path of server zip: /FTB2/modpacks/" + dir + "/" + repoVersion + "/" + serverPack);
				}else{
					System.out.println("Server file not provided");
				}
				
				System.out.println("\nValidate informations ? (Y/N)");
				
				stay = true;
				while(stay){
					ValidateInfo = Main.scanner.nextLine();
					if ( ValidateInfo.equals("Y") || ValidateInfo.equals("y") ){
						stay = false;
					}else if ( ValidateInfo.equals("N") || ValidateInfo.equals("n") ){
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
			final DOMSource source = new DOMSource(document);
			final StreamResult sortie = new StreamResult(new File(Locations.path + Locations.modpackFile));

			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.VERSION, XMLVersion);
			transformer.setOutputProperty(OutputKeys.ENCODING, XMLEncoding);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, sortie);
			
		}  catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void remove(int choixmodpack) {
	
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
	
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			//Reading modpacks.xml
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(Locations.path + Locations.modpackFile));
			XMLVersion = document.getXmlVersion();
			XMLEncoding = document.getXmlEncoding();
			final Element racine = document.getDocumentElement();
			final NodeList racineNoeuds = racine.getChildNodes();
			
			final Element modpack = (Element) racineNoeuds.item(choixmodpack);
			
			name = modpack.getAttribute("name");
			
			Resources.clear();
			
			Backup.create();
			System.out.println("Are you sure you want to delete \"" + name + "\" ? (Y/N) ");
			boolean stay = true;
			String entry = "";
			while(stay){
				entry = Main.scanner.nextLine();
				if ( entry.equals("Y") || entry.equals("y") ){
					stay = false;
				}else if ( entry.equals("N") || entry.equals("n") ){
					return;
				}else{
					System.out.print("Please choose Y or N: ");
				}
			}
			
			//Modpack modifications
			racine.removeChild(modpack);
			
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
			final DOMSource source = new DOMSource(document);
			final StreamResult sortie = new StreamResult(new File(Locations.path + Locations.modpackFile));
	
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
