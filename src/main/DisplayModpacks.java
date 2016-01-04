package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DisplayModpacks {
	
	public static int main() {

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
		    final DocumentBuilder builder = factory.newDocumentBuilder();		
		    final Document document = builder.parse(new File(Locations.path + Locations.inputFile));
			
			final Element racine = document.getDocumentElement();
			final NodeList racineNoeuds = racine.getChildNodes();
			
			final int nbRacineNoeuds = racineNoeuds.getLength();
			
			int i;
			for (i = 0; i<nbRacineNoeuds; i++) {
			    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
			        final Element modpack = (Element) racineNoeuds.item(i);
				System.out.println((i+1)/2 + ". " + modpack.getAttribute("name") + " (MC version: " + modpack.getAttribute("mcVersion") + ")");
			    }				
			}
			System.out.println((i+1)/2 + ". Back");
			return (i+1)/2;
			
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
		
		return 0;
	}
}
