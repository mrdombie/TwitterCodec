package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import nu.xom.Document;
import nu.xom.Node;
import nu.xom.converters.DOMConverter;

import org.apache.xerces.xni.XMLDocumentHandler;
import org.w3c.dom.DOMImplementation;

import twitter4j.Status;
import twitter4j.TwitterException;
import contruction.CaptureTweet;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.XMLOutputter;

public class TweetToXML {
	
	public static org.w3c.dom.Document convertToDOM(nu.xom.Document doc) throws ParserConfigurationException{
		
		final nu.xom.Document xomDoc = doc;		
		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final DOMImplementation impl = builder.getDOMImplementation();
		final org.w3c.dom.Document w3cDoc = DOMConverter.convert(doc, impl);
		
		return w3cDoc;

	}

	public static org.w3c.dom.Document tweetToXML(Status status) throws IOException, ParserConfigurationException {

		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos");
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// create an empty Annotation just with the given text
		Annotation document = new Annotation(status.getText());

		// run all Annotators on this text
		pipeline.annotate(document);

		Document xmldoc = XMLOutputter.annotationToDoc(document, pipeline);
		
		convertToDOM(xmldoc);
		
		System.out.println(xmldoc.toXML());
		
		FileOutputStream os = new FileOutputStream(new File("./target/", "nlp.xml"));
	    pipeline.xmlPrint(document, os); //just creates an xml file - for testing purposes mainly. - can remove at later date safely
	
	    return convertToDOM(xmldoc);
	    
	}
}
