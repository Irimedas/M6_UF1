import java.io.File;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;

public class LlegirFitxerXML {

	public static void main(String[] args) {
        try {
            File archivo = new File("alumnes.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            
            document.getDocumentElement().normalize();
            NodeList listaAlumnes = document.getChildNodes();
            obtenerTextoNodosInternos("", listaAlumnes);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	//Función recursiva de lectura de lista de nodos
	private static void obtenerTextoNodosInternos(String linea, NodeList nodeList) {
		
		//Recoremos la lista de nodes obtenida
		for (int i = 0; i < nodeList.getLength(); i++) {
			
			String nodeNom = "";
			String lineaAtribut = "";
			String contingut = "";
			String lineaCompleta = "";
			
			lineaCompleta += linea;
        	Node node = nodeList.item(i);
        	
        	//Si el nodo tiene atributos
        	if(node.hasAttributes()) {
        		NamedNodeMap atributs = node.getAttributes();
        		for (int j = 0; j < atributs.getLength(); j++) {
        			lineaAtribut += " " + node.getAttributes().item(j);
    			}
        	}
        	
        	nodeNom = node.getNodeName();
        	
        	//Si el nodo no tiene nodos hijo
        	if(node.hasChildNodes()) {
        		lineaCompleta += "<" + nodeNom + lineaAtribut + ">";
        		System.out.println(lineaCompleta);
        		
        		NodeList newNodeList = node.getChildNodes();
        		obtenerTextoNodosInternos(linea + "  ", newNodeList);
        		
        		lineaCompleta = linea + "</" + nodeNom + ">";
        		System.out.println(lineaCompleta);
        		
        	//Si el nodo no tiene nodos hijo
        	}else{
        		contingut = node.getNodeValue().trim();
        		if (!contingut.equals("")) {
        			lineaCompleta += "<" + lineaAtribut + ">";
            		System.out.println(linea + contingut);
                }
        	}
        }
	}
}
