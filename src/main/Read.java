package main;

import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class Read {
	
	public static void main() {

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
		    final DocumentBuilder builder = factory.newDocumentBuilder();		
		    final Document document = builder.parse(new File("D:/Fichiers/Desktop/modpacks2.xml"));
			
			final Element racine = document.getDocumentElement();
			
			final NodeList racineNoeuds = racine.getChildNodes();
			
			final int nbRacineNoeuds = racineNoeuds.getLength();
			
			for (int i = 0; i<nbRacineNoeuds; i++) {
			    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
			        final Element modpack = (Element) racineNoeuds.item(i);
				System.out.println((i+1)/2 + ". " + modpack.getAttribute("name") + " (MC version: " + modpack.getAttribute("mcVersion") + ")");
			    }				
			}
			
		}
		catch (final ParserConfigurationException e) {
		    e.printStackTrace();
		}
		catch (final SAXException e) {
		    e.printStackTrace();
		}
		catch (final IOException e) {
		    e.printStackTrace();
		}
		
	}
}
