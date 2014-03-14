package poison.utils;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import com.jcabi.aspects.Loggable;

import constants.WordTags;
import poison.interfaces.Poison;
import template.models.TweetTemplate;

public class Injector {
	
	@Loggable(Loggable.INFO)
	public static void inject(AngelOfDeath deathAngel) {
		
		List<String> dataToInsert = deathAngel.getDataToInsert();
		int dataSize = deathAngel.getDataToInsert().size();
;		int poisonSize = deathAngel.getPoisons().size();
		List<Poison> poisons = deathAngel.getPoisons();
		
		Random rand = new Random();
		
		for (int i = 0; i < dataSize; i++) {
			int theRand = rand.nextInt(poisonSize);
			System.out.println(theRand);
			poisons.get(theRand).applyPoison(deathAngel, dataToInsert.get(i));
		}
	}
	
	@Loggable(Loggable.INFO)
	public static void injectKeyWord(String keyword, TweetTemplate template){
		for (WordTags tag : EnumSet.allOf(WordTags.class)) {
			if(template.getBody().contains(tag.toString())){
				template.getBody().replace(tag.toString(), keyword);
			}
		}	
	}
}

