package main;

import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;



public class Write {
	
	public static void main(String[] args) {
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document= builder.newDocument();
			final Element racine = document.createElement("modpacks");
			document.appendChild(racine);
			final Element modpack = document.createElement("modpack");
			racine.appendChild(modpack);
			modpack.setAttribute("name", "TeamNT Modpack v2");
			modpack.setAttribute("author", "TeamNT Modpack v2");
			modpack.setAttribute("version", "TeamNT Modpack v2");
			modpack.setAttribute("repoVersion", "TeamNT Modpack v2");
			modpack.setAttribute("logo", "TeamNT Modpack v2");
			modpack.setAttribute("url", "TeamNT Modpack v2");
			modpack.setAttribute("image", "TeamNT Modpack v2");
			modpack.setAttribute("dir", "TeamNT Modpack v2");
			modpack.setAttribute("mcVersion", "TeamNT Modpack v2");
			modpack.setAttribute("serverPack", "TeamNT Modpack v2");
			modpack.setAttribute("description", "TeamNT Modpack v2");
			modpack.setAttribute("mods", "TeamNT Modpack v2");
			modpack.setAttribute("oldVersions", "TeamNT Modpack v2");
			
			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final DOMSource source = new DOMSource(document);
			final StreamResult sortie = new StreamResult(new File("D:/Fichiers/Desktop/modpacks2.xml"));
			
			try {
				final Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				transformer.transform(source, sortie);
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		catch (final ParserConfigurationException e) {
		    e.printStackTrace();
		}

	}

}