package info.androidhive.slidingmenu.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class StringToDocConverter {

	public static Document convertStringToDocument(String src) {
		Document dest = null;

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser;

		try {
			parser = dbFactory.newDocumentBuilder();
			dest = parser.parse(new ByteArrayInputStream(src.getBytes()));
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			
		} catch (SAXException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}

		return dest;
	}
}
