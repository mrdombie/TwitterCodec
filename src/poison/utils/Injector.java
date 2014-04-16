package poison.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import lexiconUtils.WordType;
import poison.interfaces.Poison;
import template.models.TweetTemplate;

import com.jcabi.aspects.Loggable;

public class Injector {
	
	@Loggable(Loggable.INFO)
	public static List<TweetTemplate> inject(AngelOfDeath deathAngel) {
		
		List<TweetTemplate> templates = new ArrayList<TweetTemplate>();
		List<String> dataToInsert = deathAngel.getDataToInsert();
		List<Poison> poisons = deathAngel.getPoisons();
		
		int dataSize = deathAngel.getDataToInsert().size();
		int poisonSize = deathAngel.getPoisons().size();
		int index = 0;
		
		try {
			for (int i = 0; i < dataSize; i++) {		
				TweetTemplate template = poisons.get(index).applyPoison(deathAngel, dataToInsert.get(i));	
				index++;
				if(index == poisonSize){
					index = 0;
				}
				templates.add(template);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return templates;
	}
	
	@Loggable(Loggable.INFO)
	public static TweetTemplate injectKeyWord(String keyword, TweetTemplate template){
		outerloop: {
			for (WordType tag : EnumSet.allOf(WordType.class)) {
					if(template.getBody().contains(tag.toString())){
						template.setBody(template.getBody().replaceFirst(tag.toString(), keyword));
						break outerloop;
					}
				}
			}
		return template;
	}
	
	public static List<TweetTemplate> generateCleanTweetList(List<TweetTemplate> templates) throws IOException{
		for (TweetTemplate tweetTemplate : templates) {
			generateCleanSingleTweet(tweetTemplate);
		}
		return templates;
	}
	
	public static TweetTemplate generateCleanSingleTweet(TweetTemplate template) throws IOException{
		for (WordType tag : EnumSet.allOf(WordType.class)) {
			if(template.getBody().contains(tag.toString())){
				template.getBody().replace(tag.toString(), tag.selectRandom());
			}
		}
		return template;	
	}
}

