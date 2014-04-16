package templates.generator;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import template.models.TweetTemplate;
import templates.TweetTemplateFactory;
import twitter4j.TwitterException;

public class TweetTemplateGenerator {

	public TweetTemplate generateSingleTemplate() throws TwitterException, InterruptedException, ParserConfigurationException, IOException{
		TweetTemplateFactory factory = new TweetTemplateFactory(selectRandomTopic(), 1);
		List<TweetTemplate> templates = factory.createTweetTemplates();
		return templates.get(0);
	}
	
	public List<TweetTemplate> generateManyTemplate(int numberOfTweets) throws TwitterException, InterruptedException, ParserConfigurationException, IOException{
		TweetTemplateFactory factory = new TweetTemplateFactory(selectRandomTopic(), numberOfTweets);
		List<TweetTemplate> templates = factory.createTweetTemplates();
		return templates;
	}
	
	private String selectRandomTopic(){
		String[] topics = {"BBC", "NEWS", "SCIENCE", "WAR", "MUSIC"};
		Random rand = new Random();
		String topic = topics[rand.nextInt(topics.length)];
		return topic;
	};
	
}
