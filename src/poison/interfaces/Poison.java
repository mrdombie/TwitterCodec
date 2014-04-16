package poison.interfaces;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import poison.utils.AngelOfDeath;
import template.models.TweetTemplate;
import twitter4j.TwitterException;


public interface Poison {

	public abstract int getId();
	public abstract TweetTemplate applyPoison(AngelOfDeath deathAngel, String poisonWord) throws IOException, TwitterException, InterruptedException, ParserConfigurationException;
	public abstract TweetTemplate removePoison(TweetTemplate template);
	public abstract TweetTemplate waterMarkTweet(TweetTemplate template) throws IOException;
}

