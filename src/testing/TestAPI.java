package testing;

import java.util.ArrayList;
import java.util.List;

import file.helper.FileHandler;
import poison.interfaces.Poison;
import poison.types.Base64Poison;
import poison.types.PoisonClassList;
import poison.utils.AngelOfDeath;
import template.models.TweetTemplate;
import utils.ModelReader;
import watermarker.Decrypter;

public class TestAPI {
	
	private static String dataToInsertIntoTweets = "D:/Programming/Projects/TwitterCodec/poisons/poisondata.txt";
	
	public static void main(String[] args) throws Exception {
		
	/*	List<Poison> poisons = new ArrayList<Poison>();
		poisons.add(new Base64Poison());		
	
		AngelOfDeath deathAngel = new AngelOfDeath();
		deathAngel.poisonTemplatesUsing(dataToInsertIntoTweets);
		deathAngel.usingTheFollowingPoisons(poisons);
		deathAngel.templates(ModelReader.getTemplates());
		deathAngel.poison();
		deathAngel.cleanTheScene();
		deathAngel.getFinalTweetList();
	
		List<TweetTemplate> template = deathAngel.getPoisonedTweets();
		
		for (TweetTemplate tweetTemplate : template) {
			System.out.println(tweetTemplate.getBody());
		}*/
		
		PoisonClassList poison = new PoisonClassList();
		List<Class<?>> poisonss = poison.getPoisonClasses();
				
	}
}
