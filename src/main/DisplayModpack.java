package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DisplayModpack {

	public static void main(int choixmodpack) {

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
			System.out.println("mods: " + mods);
			System.out.println("oldVersions: " + oldVersions);
			
			System.out.println("\n\nPress \"Enter\" to continue");
			Main.scanner.nextLine();
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
