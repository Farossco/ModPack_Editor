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



public class CreateModpack {
	
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
			String serverPackChoice = "";
			boolean serverPackChoiceboolean = false;
			String description = "";
			String mods = "";
			String mod = "";
			String oldVersions = "";
			String shortname = "";
			String ValidateDatas = "";
			
			do{
				for (int i = 0; i < 50; ++i) System.out.println();
				System.out.println("Bienvenue dans le Programme d'ajout de modpack");
				if (!name.isEmpty()){
					System.out.println("Entrez \"NULL\" pour conserver les données initiales\n");
				}else{
					System.out.println("\n");
				}
				
				System.out.println("Nom du Modpack: " + name);
				do{
					name = Main.scanner.nextLine();
				}while(name.isEmpty());
				
				System.out.println("Nom de modpack abrégé (TNT, FTB...): " + shortname);
				do{
					shortname = Main.scanner.nextLine();
				}while(shortname.isEmpty());
				
				System.out.println("Auteur du modpack: " + author);
				do{
					author = Main.scanner.nextLine();
				}while(author.isEmpty());
				
				System.out.println("Version initiale: " + version);
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
					System.out.println("Format incorrect, merci de n'utiliser que chiffres et des points");
					mcVersion = Main.scanner.nextLine();
				}
				
				System.out.println("Mettre une version serveur à disposition ? (Y/N) " + serverPackChoice.toUpperCase());
				boolean stay = true;
				while(stay){
					serverPackChoice = Main.scanner.nextLine();
					if ( serverPackChoice.equals("Y") || serverPackChoice.equals("y") ){
						serverPackChoiceboolean = true;
						serverPack = shortname + "Server.zip";
						stay = false;
					}else if ( serverPackChoice.equals("N") || serverPackChoice.equals("n") ){
						serverPackChoiceboolean = false;
						serverPack = "";
						stay = false;
					}else{
						System.out.println("Choisissez Y ou N");
					}
				}
				
				System.out.println("Description du modpack: " + description);
				description = Main.scanner.nextLine();
				
				System.out.println("Mods qui constituent le modpack: (Apuyez sur \"entrer\" quand vous avez terminé) " + mods);
				
				mod = Main.scanner.nextLine();
				mods = mod;
				
				while( !mod.isEmpty() ){
					mod = Main.scanner.nextLine();
					if (!mod.isEmpty()) mods = mods + "; " + mod;
				}
				
				System.out.println("Informations complementaires: ");
				System.out.println("Nom du logo (Petite image à gauche du modpack): " + logo);
				System.out.println("Nom de l'image (Grande image au dessus de la description): " + image);
				System.out.println("Nom et adresse du fichier zip: /FTB2/modpacks/" + dir + "/" + repoVersion + "/" + url);
				if (serverPackChoiceboolean){
					System.out.println("Nom et adresse du fichier zip (serveur): /FTB2/modpacks/" + dir + "/" + repoVersion + "/" + serverPack);
				}else{
					System.out.println("Pas de version serveur disponible");
				}
				
				System.out.println("\nValider les information ? (Y/N)");
				
				stay = true;
				while(stay){
					ValidateDatas = Main.scanner.nextLine();
					if ( ValidateDatas.equals("Y") || ValidateDatas.equals("y") ){
						stay = false;
					}else if ( ValidateDatas.equals("N") || ValidateDatas.equals("n") ){
						stay = false;
					}else{
						System.out.println("Choisissez Y ou N");
					}
				}
				
			}while( ValidateDatas.equals("N") || ValidateDatas.equals("n") );
			
			
			
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