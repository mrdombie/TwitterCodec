package poison.types;

import lexiconUtils.WordTools;
import template.models.TweetTemplate;

public class HashTagPoison {

	public String poisonAsHashTag(String poisonWord, TweetTemplate template) {

		WordTools tools = new WordTools();
		StringBuilder builder = new StringBuilder();
		builder.append(template + " " + tools.generateHashTag(poisonWord));
		return builder.toString();

	}

}
