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
}