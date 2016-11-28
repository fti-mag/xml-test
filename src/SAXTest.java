import java.nio.file.Paths;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;


public class SAXTest extends DefaultHandler {
	private int nelems = 0;
	private int nattrs = 0;
	public void startDocument() throws SAXException {
		System.out.println("startDocument");
	}
	
	public void endDocument() throws SAXException {
		System.out.println("endDocument");
		System.out.println("Elements: " + nelems);
		System.out.println("Attributes: " + nattrs);
	}
	
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		nelems += 1;
		System.out.println(localName);
		for(int i = 0; i < atts.getLength(); ++i) {
			nattrs += 1;
			System.out.println("  " + atts.getLocalName(i) + ": " + atts.getValue(i));
		}
	}
	
	public static void count(String filename) throws Exception {
		System.out.println("[SAX]");
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setContentHandler(new SAXTest());
		xmlReader.parse(Paths.get(filename).toUri().toString());
	}
}
