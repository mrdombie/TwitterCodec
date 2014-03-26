package poison.types;

import lexiconUtils.WordType;
import template.models.TweetTemplate;

public class HashTagPoison {

	public String poisonAsHashTag(String poisonWord, TweetTemplate template) {

		WordType tools = new WordType();
		StringBuilder builder = new StringBuilder();
		builder.append(template + " " + tools.generateHashTag(poisonWord));
		return builder.toString();

	}

}
