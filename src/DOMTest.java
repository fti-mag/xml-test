import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;


public class DOMTest {
	public static void printAll(Node root, int depth) {
		if(root.getNodeType() == Node.ELEMENT_NODE) {
			String spaces = "";
			for(int i = 0; i < depth; ++i) {
				spaces += "  ";
			}
			System.out.println(spaces + root.getNodeName());
			NodeList childNodes = ((Element) root).getChildNodes();
			for(int i = 0; i < childNodes.getLength(); ++i) {
				printAll(childNodes.item(i), depth + 1);
			}
		}
		
		//returns specific attribute
		// getAttribute("attributeName"); 
		
		//returns a Map (table) of names/values
		// getAttributes();
		
		//returns a list of subelements of specified name
		// getElementsByTagName("subelementName"); 
		
		//returns a list of all child nodes
		// getChildNodes();
	}
	
	public static void test(String filename) throws Exception {
		System.out.println("[DOM]");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.parse(new File(filename));
		
		Element root = doc.getDocumentElement();
		printAll(root, 0);
		
		// root.setAttribute("attr", "value");
		Element msg = doc.createElement("message");
		Element elem = null;
		
		elem = doc.createElement("from");
		elem.setTextContent("Max");
		msg.appendChild(elem);
		
		elem = doc.createElement("to");
		elem.setTextContent("Alex");
		msg.appendChild(elem);
		
		elem = doc.createElement("subject");
		elem.setTextContent("Hi!");
		msg.appendChild(elem);
		
		elem = doc.createElement("text");
		elem.setTextContent("R U at home today?");
		msg.appendChild(elem);
		
		msg.setAttribute("date", "2016-05-16");
		root.appendChild(msg);
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File(filename + ".mod.xml"));
		Source input = new DOMSource(doc);

		transformer.transform(input, output);
	}
}
