import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.xml.sax.SAXException;


public class XSDTest {
	public static void validate(String schemaFilename, String filename) throws Exception {
		System.out.println("[XSD]");
		Source xmlFile = new StreamSource(new File(filename));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(new File(schemaFilename));
		} catch (SAXException e) {
			System.out.println(schemaFilename + " contains errors");
			System.out.println(e.getLocalizedMessage());
			return;
		}
		Validator validator = schema.newValidator();
		try {
			validator.validate(xmlFile);
			System.out.println(filename + " is valid");
		} catch (SAXException e) {
			System.out.println(filename + " is not valid");
			System.out.println(e.getLocalizedMessage());
		}
	}
}
