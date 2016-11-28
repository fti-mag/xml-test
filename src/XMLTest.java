
public class XMLTest {
	public static void main(String[] args) throws Exception {
		String filename = "xml/example.xml";
		XSDTest.validate("xml/schema.xml", filename);
		SAXTest.count(filename);
		DOMTest.test(filename);
		XSDTest.validate("xml/schema.xml", filename + ".mod.xml");
	}
}
