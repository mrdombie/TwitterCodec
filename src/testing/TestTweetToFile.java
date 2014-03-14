package testing;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;

import templates.TweetTemplateFactory;
import twitter4j.TwitterException;
import utils.TweetToFile;

public class TestTweetToFile {

	@Test
	public void testWriteTweets() throws ParserConfigurationException, TwitterException, InterruptedException, IOException{
		
		TweetToFile ttf = new TweetToFile();
		TweetTemplateFactory tweep = new TweetTemplateFactory();
		
		List<String> list = tweep.createTweetTemplates("science", 50); 
		ttf.writeTweetsToTxtFile(list);
		
	}
	
}
