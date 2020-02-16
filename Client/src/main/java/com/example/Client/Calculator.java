package com.example.Client;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.swing.text.html.HTML;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Node;

public class Calculator {
    private String input;
    private String input1;
    private String input2;
    private String input3="1";
    private String output3;

    public String getInput() {
        return input;
    }
    public String getInput1() {
        return input1;
    }
    public String getInput2() {
        return input2;
    }
    public String getInput3() {
        return input3;
    }
    public String getOutput3() {
        return output3;
    }
    public void setInput(String input) throws ParserConfigurationException, SAXException, IOException {
        this.input = input;
    }
    public void setInput1(String input1) throws ParserConfigurationException, SAXException, IOException {
        this.input1 = input1;
    	String SERVICE_URL1 = "http://localhost:8081/UserManagement/rest/UserService/add?name=" + input + "&prof=" + input1;
    	Calculator rce = new Calculator();
    	GenericType<String> string = new GenericType<String>() {};
		String s = rce.client.target(SERVICE_URL1).request(MediaType.APPLICATION_XML).get(string);
    }
    public void setInput2(String input2) throws ParserConfigurationException, SAXException, IOException {
        this.input2 = input2;
    	String SERVICE_URL2 = "http://localhost:8081/UserManagement/rest/UserService/delete?id=" + input2;
    	Calculator rce = new Calculator();
    	GenericType<String> string = new GenericType<String>() {};
		String s = rce.client.target(SERVICE_URL2).request(MediaType.APPLICATION_XML).get(string);
    }
    
    public void setInput3(String input3) throws ParserConfigurationException, SAXException, IOException {
        setOutput3(calculateExpression3(input3)); // OBS!
        this.input3 = input3;
    }
    
    public void setOutput3(String output3) {
        this.output3 = output3;
        System.out.println(output3);
    }  


    	private Client client;
    	private String SERVICE_URL = "http://localhost:8081/UserManagement/rest/UserService/users";
    	
    	public Calculator() {
    		client = ClientBuilder.newClient();
    	}
    	

    public String calculateExpression3(String expression3) throws ParserConfigurationException, SAXException, IOException {
		Calculator rce = new Calculator();

		GenericType<String> string = new GenericType<String>() {};
		String s = rce.client.target(SERVICE_URL).request(MediaType.APPLICATION_XML).get(string);

		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(s));

		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("user");
		
		String ss = null;

		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);

			NodeList name = element.getElementsByTagName("id");
			Element line = (Element) name.item(0);
			if(i==0) {
				ss="Id: "+getCharacterDataFromElement(line)+", ";
			}
			else {
			ss=ss+"Id: "+getCharacterDataFromElement(line)+", ";
			}
			NodeList title = element.getElementsByTagName("name");
			line = (Element) title.item(0);
			ss=ss+"Name: "+getCharacterDataFromElement(line)+", ";

			NodeList prof = element.getElementsByTagName("profession");
			line = (Element) prof.item(0);
			ss=ss+"Profession: "+getCharacterDataFromElement(line)+"\n";

			
		}
		
		
		return ss;
	}


    public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
 
}