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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EditModpack {

	public static void main(int choixmodpack) {

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
				
				System.out.println("name: " + name);
				System.out.println("author: " + author);
				System.out.println("version: " + version);
				System.out.println("logo: " + logo);
				System.out.println("url: " + url);
				System.out.println("image: " + image);
				System.out.println("dir: " + dir);
				System.out.println("mcVersion: " + mcVersion);
				System.out.println("serverPack: " + serverPack);
				System.out.println("description: " + description);
				System.out.println("mods: " + mods);
				System.out.println("oldVersions: " + oldVersions);
				
				System.out.println("\nWhat value do you want to edit ?\nPress \"Enter\" when you finish");
				do{
					choixEdition = Main.scanner.nextLine();
				}while ( !choixEdition.equals("name") && !choixEdition.equals("author") && !choixEdition.equals("version") && !choixEdition.equals("repoVersion") && !choixEdition.equals("logo") && !choixEdition.equals("url") && !choixEdition.equals("image") && !choixEdition.equals("dir") && !choixEdition.equals("mcVersion") && !choixEdition.equals("serverPack") && !choixEdition.equals("description") && !choixEdition.equals("mods") && !choixEdition.equals("oldVersions") && !choixEdition.isEmpty() );
				
				if (!choixEdition.isEmpty()){
					System.out.println("Old value: " + modpack.getAttribute(choixEdition) + "\n");
					String newvalue = Main.scanner.nextLine();
				
					switch(choixEdition){
					case "name":
						name = newvalue;
						modpack.setAttribute("name", name);
						break;
					case "author":
						author = newvalue;
						modpack.setAttribute("author", author);
						break;
					case "version":
						version = newvalue;
						modpack.setAttribute("version", version);
						break;
					case "repoVersion":
						version = newvalue;
						modpack.setAttribute("repoVersion", repoVersion);
						break;
					case "logo":
						logo = newvalue;
						modpack.setAttribute("logo", logo);
						break;
					case "url":
						url = newvalue;
						modpack.setAttribute("url", url);
						break;
					case "image":
						image = newvalue;
						modpack.setAttribute("image", image);
						break;
					case "dir":
						dir = newvalue;
						modpack.setAttribute("dir", dir);
						break;
					case "mcVersion":
						mcVersion = newvalue;
						modpack.setAttribute("mcVersion", mcVersion);
						break;
					case "serverPack":
						serverPack = newvalue;
						modpack.setAttribute("serverPack", serverPack);
						break;
					case "description":
						description = newvalue;
						modpack.setAttribute("description", description);
						break;
					case "mods":
						mods = newvalue;
						modpack.setAttribute("mods", mods);
						break;
					case "oldVersions":
						oldVersions = newvalue;
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
}