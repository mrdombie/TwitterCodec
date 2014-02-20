package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.xpath.operations.Equals;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import twitter4j.Status;
import twitter4j.TwitterException;
import contruction.CaptureTweet;

public class TweetNodes {
	
	public static void main(String[] args) throws ParserConfigurationException, TwitterException, InterruptedException, IOException {
		
		getTagSchema();
		
	}
	
	public static void getTagSchema() throws ParserConfigurationException,
			TwitterException, InterruptedException, IOException {
		
		TweetToXML toXML = new TweetToXML();
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		
		CaptureTweet tweet = new CaptureTweet();
		List<Status> myStatus = new ArrayList<>();
		myStatus = tweet.getTweetsByTopic("BBC");
		
		for (Status status : myStatus) {
			
			List<String> tagList = new ArrayList<>();
			String temp = null;
		
			Document document = toXML.tweetToXML(status);
			
			NodeList nodeList = document.getElementsByTagName("*");
		    for (int i = 0; i < nodeList.getLength(); i++) {
		        Node node = nodeList.item(i);      
		        if (node.getNodeType() == Node.ELEMENT_NODE) {
		        	
		       
		        	
		        	if(node.getNodeName().equals("word")){
		        		temp = node.getTextContent();
		        	}
		        	
		        	if (node.getNodeName().equals("POS")) {
	        			if(node.getTextContent().equals("NN")){
	        				tagList.add("NN");
	        			}else if(temp != null){
	        				tagList.add(temp);
	        			}
		        		
		        		
					}
		        	
		        }
		    }
		    
		    for (String string : tagList) {
				System.out.println(string);
			}
		    
		}	
	}
}
