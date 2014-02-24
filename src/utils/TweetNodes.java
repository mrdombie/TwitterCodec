package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import twitter4j.Status;
import twitter4j.TwitterException;
import contruction.CaptureTweet;
import entities.TweetSchemaTags;

public class TweetNodes {
	
	public static void main(String[] args) throws ParserConfigurationException, TwitterException, InterruptedException, IOException {
		
		getTagSchema("filler");
		
	}
	
	public static List<String> getTagSchema(String topic) throws ParserConfigurationException,
			TwitterException, InterruptedException, IOException {
		
		TweetToXML toXML = new TweetToXML();
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		
		CaptureTweet tweet = new CaptureTweet();
		List<Status> myStatus = new ArrayList<>();
		myStatus = tweet.getTweetsByTopic("#ukraine");
		List<String> tagList = new ArrayList<>();
		
		for (Status status : myStatus) {
			
			String temp = null;
			Document document = toXML.tweetToXML(status);		
			NodeList nodeList = document.getElementsByTagName("*");
			
			for (int i = 0; i < nodeList.getLength(); i++) {
		        Node node = nodeList.item(i);      
		        if (node.getNodeType() == Node.ELEMENT_NODE) {      		        	
		        	temp = setTemplateTag(temp, node, "word");         	
		        	addToList(tagList, temp, node);	        	
		        }
		    }		    	    
		}	
		
		for (String string : tagList) {
			System.out.println(string);
		}
		 
		return tagList;
	}
		
	private static void addToList(List<String> tagList, String temp, Node node) {
		
		String[] tags = {"[HASHTAG]", "[HTTP]"};
		
		for (String string : tags) {
			if(node.getNodeName().equals("POS") && !string.equals(temp) && node.getTextContent().equals("NN")){
				tagList.add("[NOUN]");
				break;
			}else if(node.getNodeName().equals("POS")) {
				tagList.add(temp);
				break;
			}
		}
	}
	
	private static String setTemplateTag(String temp, Node node, String word) {
		
		if(node.getNodeName().equals(word) && node.getTextContent().matches("^[#].*$")){  //pulls out words starting with # - we presume they are a hashtag following twitter format
			temp = "[HASHTAG]";	
		}else if(node.getNodeName().equals(word) && node.getTextContent().contains("http")){
			temp = "[HTTP]";
		}else if(node.getNodeName().equals(word)){
			temp = node.getTextContent();
		}
		return temp;		
	}
}
