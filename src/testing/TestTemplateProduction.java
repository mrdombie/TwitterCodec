package testing;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;

import templates.TweetTemplate;
import twitter4j.TwitterException;

public class TestTemplateProduction {

	@Test
	public void testCreateTemplates() throws TwitterException, InterruptedException, ParserConfigurationException, IOException{
		TweetTemplate prod = new TweetTemplate();
		prod.createTweetTemplates("http", 1);
	}
	
}
