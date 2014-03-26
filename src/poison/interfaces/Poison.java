package poison.interfaces;

import java.io.IOException;

import poison.utils.AngelOfDeath;
import template.models.TweetTemplate;


public interface Poison {

	public abstract int getId();
	public abstract TweetTemplate applyPoison(AngelOfDeath deathAngel, String poisonWord) throws IOException;
	public abstract void removePoison(TweetTemplate template);
	public abstract TweetTemplate waterMarkTweet(TweetTemplate template) throws IOException;
}

