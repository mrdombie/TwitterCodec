package testing;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;

import templates.TweetTemplateFactory;
import twitter4j.TwitterException;

public class TestTemplateProduction {

	@Test
	public void testCreateTemplates() throws TwitterException, InterruptedException, ParserConfigurationException, IOException{
		TweetTemplateFactory prod = new TweetTemplateFactory();
		prod.createTweetTemplates("http", 1);
	}
	
}
