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


public class ListModpacks {
	
	public static int main(String choice) {

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		String choixModpack = "";
		int choixModpackInt, i;
		int back;
		
		try {
			do{
				for (i = 0; i < 50; ++i) System.out.println();
				System.out.println("What modpack do you want to " + choice + " ?");
				
			    final DocumentBuilder builder = factory.newDocumentBuilder();		
			    final Document document = builder.parse(new File(Locations.path + Locations.modpackFile));
				
				final Element racine = document.getDocumentElement();
				final NodeList racineNoeuds = racine.getChildNodes();
				
				final int nbRacineNoeuds = racineNoeuds.getLength();
				
				for (i = 0; i<nbRacineNoeuds; i++) {
				    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
				        final Element modpack = (Element) racineNoeuds.item(i);
					System.out.println((i+1)/2 + ". " + modpack.getAttribute("name") + " (MC version: " + modpack.getAttribute("mcVersion") + ")");
				    }				
				}
				System.out.println((i+1)/2 + ". Back");
				back = (i+1)/2;
			
				choixModpack = Main.scanner.nextLine();
				if (choixModpack.isEmpty()) choixModpack = "0";
				choixModpackInt = Integer.parseInt(choixModpack);
			}while(choixModpackInt > back || choixModpackInt < 1);
			
			if (choixModpackInt == back) return 0;
			
			
			if (choice.equals("display")){
				DisplayModpack.main(choixModpackInt*2-1);
			}else if (choice.equals("edit")){
				EditModpack.main(choixModpackInt*2-1);
			}else if (choice.equals("update")){
				UpdateModpack.main(choixModpackInt*2-1);
			}else if (choice.equals("remove")){
				RemoveModpack.main(choixModpackInt*2-1);
			}
			
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}return 0;
	}
}
