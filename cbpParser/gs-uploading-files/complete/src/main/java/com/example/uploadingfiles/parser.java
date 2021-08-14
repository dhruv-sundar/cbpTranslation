package com.example.uploadingfiles;

import org.springframework.core.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;


public class parser {

    private Document doc;
    private NodeList list;
    private LinkedHashMap<String, String> transferValues;
    private File myObj;
    private FileWriter myWriter;
    public parser(File inputFile) {
        try {
            File input = inputFile; //temp input file to work with, can make user type in file name later on
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();   //setting up document to parse
            doc = builder.parse(input);
            doc.getDocumentElement().normalize();
            transferValues = new LinkedHashMap<>();//will contain values to transfer to text document
            //System.out.println("Root element " + doc.getDocumentElement().getNodeName());

            list = doc.getElementsByTagName("*");//grabbing list of all the elements

            myObj = new File("output.txt");

            if (myObj.createNewFile()) System.out.println("File created: " + myObj.getName());
            else System.out.println("File already exists.");

            myWriter = new FileWriter(myObj);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private String getContent(String tagName){
        for (int i = 0; i < list.getLength(); i++) {
            // Get element
            Element element = (Element) list.item(i);
            if (element.getTagName().equals(tagName))
                return element.getTextContent();
        }
        return "404";
    }

    public void transfer(ArrayList<String> tags){

        for(String tag : tags) {
            Element element = (Element) doc.getElementsByTagName(tag).item(0);

            //transferValues.put(element.getNodeName(),element.getTextContent());
            NodeList childNodes = element.getChildNodes();
            if(childNodes!=null)
                for (int j = 0; j < childNodes.getLength()-1; j++) {
                    Node child = childNodes.item(j);
                    if(child.getChildNodes()!=null)
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        Element node = (Element) child;
                        transferValues.put(node.getNodeName(), node.getTextContent());
                    }
                }
        }
    }


    public void transferHelper(Element a){
        NodeList x=a.getChildNodes();
        if(x==null)return;

        for (int j = 0; j < x.getLength()-2; j++) {
            Node child = x.item(j);
            if (child.getNodeType() == Node.ELEMENT_NODE && child.getChildNodes()!=null) {
                Element node = (Element) child;
                transferValues.put(node.getNodeName(), node.getTextContent());
            }
        }

    }

        /*public void transfer(ArrayList<String> tags) {

        for (String tag : tags) {
            Element element = (Element) doc.getElementsByTagName(tag).item(0);

            transferValues.put(element.getNodeName(), element.getTextContent());
            transferHelper(element);
        }
    }*/



    private HashMap<String, String> getTransfer(){
        return transferValues;
    }

    public void writeTo(){
        try{
            for(String s: transferValues.keySet()){
                //System.out.println(s + ": "+ transferValues.get(s));
                myWriter.write(s + ": "+ transferValues.get(s) + "\n");
            }
            myWriter.close();
        }catch (IOException e){
            System.out.println("Error occurred");
            e.printStackTrace();
        }

    }

    public Resource getTranslated(){
        return new FileSystemResource(myObj);
    }

    public static void main(String[] args) {
//        parser test = new parser("inputFile.xml");
//        ArrayList<String> x = new ArrayList<String>();
//        x.add("ITDS-PGA-e:MessageSenderID");
//        x.add("it:PartyName");
//        test.transfer(x);
//        test.writeTo();
    }
}
//look for certain tags and print out content to a file
//format output data to specifications
//standalone application to take in path and work with that directory
//create text file and regenerate XML file from text \
