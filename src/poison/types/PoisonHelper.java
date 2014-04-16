package poison.types;

import java.io.IOException;
import java.util.EnumSet;

import lexiconUtils.WordType;
import template.models.TweetTemplate;

public class PoisonHelper {

	public TweetTemplate replaceAndTidyTemplate(TweetTemplate template) throws IOException{
		for (WordType tags : EnumSet.allOf(WordType.class)) {
			if(template.getBody().contains(tags.toString())){
				template.setBody(tags.replace(template.getBody()));
			}
		}
		return template;
	};
	
}
