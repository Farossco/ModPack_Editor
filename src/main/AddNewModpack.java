package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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
			final Document document = builder.parse(new File(Locations.path + Locations.inputFile));
			final Element racine = document.getDocumentElement();
			final Element modpack = document.createElement("modpack");
			racine.appendChild(modpack);
			
			String XMLVersion = "";
			String XMLEncoding = "";
			
			XMLVersion = document.getXmlVersion();
			XMLEncoding = document.getXmlEncoding();
			
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
			String description = "";
			String mods = "";
			String mod = "";
			String oldVersions = "";
			String shortname = "";
			String ValidateInfo = "";
			
			do{
				for (int i = 0; i < 50; ++i) System.out.println();
				System.out.println("Welcome to the modpack adding program");
				if (!name.isEmpty()){
					System.out.println("Leave blank to keep initials values\n");
				}else{
					System.out.println("\n");
				}
				
				System.out.println("Modpack name: " + name);
				do{
					name = Main.scanner.nextLine();
				}while(name.isEmpty());
				
				System.out.println("Shortened modpack name (TNT, FTB...): " + shortname);
				do{
					shortname = Main.scanner.nextLine();
				}while(shortname.isEmpty());
				
				System.out.println("Modpack author: " + author);
				do{
					author = Main.scanner.nextLine();
				}while(author.isEmpty());
				
				System.out.println("Initial version (x / x.x / x.x.x ...): " + version);
				version = Main.scanner.nextLine();
				while (version.matches(".*[a-zA-Z²&é\"\'(è_çà)=,;:!?/§ù*^$¨£%µ+°~#{|`\\^@}].*") || version.contains(" ") || version.isEmpty()){
					System.out.println("Format incorrect, merci de n'utiliser que chiffres et des points");
					version = Main.scanner.nextLine();
				}
				repoVersion = version.replace(".", "_");
				oldVersions = version;
				
				url = shortname + ".zip";
				logo = shortname + "Splash.png";
				image = shortname + "Logo.png";
				dir = shortname;
				
				System.out.println("Minecraft version: (1.x.x) " + mcVersion);
				mcVersion = Main.scanner.nextLine();
				while (mcVersion.matches(".*[a-zA-Z²&é\"\'(è_çà)=,;:!?/§ù*^$¨£%µ+°~#{|`\\^@}].*") || mcVersion.contains(" ") || mcVersion.isEmpty()){
					System.out.println("Incorrect format. Please use only number and dots");
					mcVersion = Main.scanner.nextLine();
				}
				
				System.out.println("Provide server version ? (Y/N) " + provideServer.toUpperCase());
				boolean stay = true;
				while(stay){
					provideServer = Main.scanner.nextLine();
					if ( provideServer.equals("Y") || provideServer.equals("y") ){
						provideServerboolean = true;
						serverPack = shortname + "Server.zip";
						stay = false;
					}else if ( provideServer.equals("N") || provideServer.equals("n") ){
						provideServerboolean = false;
						serverPack = "";
						stay = false;
					}else{
						System.out.println("Please choose Y or N");
					}
				}
				
				System.out.println("Modpack description: " + description);
				description = Main.scanner.nextLine();
				
				System.out.println("Mods presents in the modpack: (Press \"enter\" when you finish) " + mods);
				
				mod = Main.scanner.nextLine();
				mods = mod;
				
				while( !mod.isEmpty() ){
					mod = Main.scanner.nextLine();
					if (!mod.isEmpty()) mods = mods + "; " + mod;
				}
				
				System.out.println("Further information: ");
				System.out.println("Logo file name (Image at the left of the modpack): " + logo);
				System.out.println("Image file name (Image at the top of the description): " + image);
				System.out.println("Name and path of client zip file: /FTB2/modpacks/" + dir + "/" + repoVersion + "/" + url);
				if (provideServerboolean){
					System.out.println("Name and path of server zip: /FTB2/modpacks/" + dir + "/" + repoVersion + "/" + serverPack);
				}else{
					System.out.println("No server file provided");
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
						System.out.println("Please choose Y or N");
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
			final StreamResult sortie = new StreamResult(new File(Locations.path + Locations.inputFile));

			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.VERSION, XMLVersion);
			transformer.setOutputProperty(OutputKeys.ENCODING, XMLEncoding);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, sortie);
			
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (final ParserConfigurationException e) {
		    e.printStackTrace();
		} catch (final SAXException e1) {
			e1.printStackTrace();
		} catch (final IOException e1) {
			e1.printStackTrace();
		}
	}
}