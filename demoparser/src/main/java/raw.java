import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class raw {

        public static void main(String[] args) {
            try {
                File input = new File("inputFile.xml"); //temp input file to work with, can make user type in file name later on
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();   //setting up document to parse
                Document doc = builder.parse(input);
                doc.getDocumentElement().normalize();

                System.out.println("Root element " + doc.getDocumentElement().getNodeName());

                NodeList list = doc.getElementsByTagName("*");//grabbing list of all the elements

                for (int i = 0; i < list.getLength(); i++)
                {
                    // Get element
                    Element element = (Element)list.item(i);
                    String s = doc.getElementsByTagName(element.getTagName()).item(0).getTextContent();
                    System.out.println(s);
                    //System.out.println(element.getNodeName() + s);
                }


            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
