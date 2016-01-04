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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UpdateModpack {

	public static void main(int choixmodpack) {

		String XMLVersion = "";
		String XMLEncoding = "";

		String version = "";
		String repoVersion = "";
		String oldVersions = "";

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			//Reading modpacks.xml
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(Locations.path + Locations.inputFile));
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
			do{	
			
				for (int i = 0; i < 50; ++i) System.out.println();
				System.out.println("Version actuelle du modpack: " + version);
				System.out.println("\nEntrez la nouvelle valeur de version");
				newVersion = Main.scanner.nextLine();
				
				if (!newVersion.isEmpty()){
					version = newVersion;
					repoVersion = version.replace(".", "_");
					oldVersions = version + ";" + oldVersions;
				}
			}while((newVersion.isEmpty()));
			
		
			//Writing modpacks.xml
		
			modpack.setAttribute("version", version);
			modpack.setAttribute("repoVersion", repoVersion);
			modpack.setAttribute("oldVersions", oldVersions);

			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final DOMSource source = new DOMSource(document);
			final StreamResult sortie = new StreamResult(new File(Locations.path + Locations.inputFile));

			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.VERSION, XMLVersion);
			transformer.setOutputProperty(OutputKeys.ENCODING, XMLEncoding);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, sortie);
		}
		catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}catch (final TransformerConfigurationException e) {
			e.printStackTrace();
		}catch (final TransformerException e) {
			e.printStackTrace();
		}catch (final SAXException e) {
			e.printStackTrace();
		}catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
