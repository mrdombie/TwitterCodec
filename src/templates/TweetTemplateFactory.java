package templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import template.models.TweetTemplate;
import twitter4j.Status;
import twitter4j.TwitterException;
import utils.TweetToFile;
import utils.TweetToXML;
import contruction.CaptureTweet;

public class TweetTemplateFactory {
	
	private final static Logger LOGGER = Logger.getLogger(TweetTemplateFactory.class
			.getName());
	
	private int size;
	private String topic;
	
	public TweetTemplateFactory(){}
	public TweetTemplateFactory(String topic, int size){
		this.topic = topic;
		this.size = size;
	}
	
	public List<String> createTweetTemplates(String topic, int size) throws TwitterException, InterruptedException, ParserConfigurationException, IOException{
		
		List<String> tweets = new ArrayList<String>();
		CaptureTweet captureTweet = new CaptureTweet();
		List<Status> statuss = captureTweet.getTweetsByTopic(topic, size);
		
		for (Status status : statuss) {	
			String tweet = arrayToString(getTagSchema(status));
			tweets.add(arrayToString(getTagSchema(status)));
			LOGGER.info(tweet);
		}
			
		return tweets;		
	}
	
	public List<TweetTemplate> createTweetTemplates() throws TwitterException, InterruptedException, ParserConfigurationException, IOException{
		
		List<String> tweetsTemplates = createTweetTemplates(topic, size);;
		List<TweetTemplate> templates = new ArrayList<TweetTemplate>();
		for (String string : tweetsTemplates) {
			TweetTemplate template = new TweetTemplate();
			template.setBody(string);
			templates.add(template);
		}
		return templates;
	}
	
	public void createTemplatesExportToFile() throws TwitterException, InterruptedException, ParserConfigurationException, IOException{
		TweetToFile.writeTweetsToTxtFile(createTweetTemplates(topic, size));
	}
	
	private List<String> getTagSchema(Status status) throws ParserConfigurationException,
			TwitterException, InterruptedException, IOException {
		
		TweetToXML toXML = new TweetToXML();
		List<String> tagList = new ArrayList<String>();
		
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
		return tagList;
	}
		
	private void addToList(List<String> tagList, String temp, Node node) {
		
		String[] tags = {"[HASHTAG]", "[HTTP]"};
		
		for (String string : tags) {
			if(node.getNodeName().equals("POS") && !string.equals(temp) && node.getTextContent().equals("NN")){
				tagList.add("[NOUN]");
				break;
			}else if(node.getNodeName().equals("POS") && !string.equals(temp) && node.getTextContent().equals("VB")){
				tagList.add("[VERB]");
				break;
			}else if(node.getNodeName().equals("POS") && !string.equals(temp) && node.getTextContent().equals("VBD")){
				tagList.add("[VERBPASTENCE]");
				break;
			}else if(node.getNodeName().equals("POS") && !string.equals(temp) && node.getTextContent().equals("JJ")){
				tagList.add("[ADJECTIVE]");
				break;
			}
			else if(node.getNodeName().equals("POS")) {
				tagList.add(temp);
				break;
			}
		}
	}
	
	private String setTemplateTag(String temp, Node node, String word) {
		
		if(node.getNodeName().equals(word) && node.getTextContent().matches("^[#].*$")){  //pulls out words starting with # - we presume they are a hashtag following twitter format
			temp = "[HASHTAG]";	
		}else if(node.getNodeName().equals(word) && node.getTextContent().contains("http")){
			temp = "[HTTP]";
		}else if(node.getNodeName().equals(word)){
			temp = node.getTextContent();
		}
		return temp;		
	}
	
	private String arrayToString(List<String> strings){
		
		StringBuilder builder = new StringBuilder();
		for (String string : strings) {
			builder.append(string + " ");
		}
		
		return builder.toString();	
	}
}
