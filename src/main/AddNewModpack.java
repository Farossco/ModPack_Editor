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
import org.xml.sax.SAXException;



public class AddNewModpack {
	
	public static void main() {
		
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
				logo = shortname + "Splash.png";
				image = shortname + "Logo.png";
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
}