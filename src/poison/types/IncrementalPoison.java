package poison.types;

import java.util.List;
import java.util.Random;

import poison.interfaces.Poison;
import poison.utils.AngelOfDeath;
import template.models.TweetTemplate;

public class IncrementalPoison implements Poison{
	
	public void incrementalPoison(String poison, List<TweetTemplate> templates) {

		Random random = new Random();
		int size = templates.size();
		char[] charArray = poison.toCharArray();

		for (int i = 0; i < charArray.length; i++) {

			int randomIndex = random.nextInt(size);
			StringBuilder builder = new StringBuilder();
			builder.append(i + String.valueOf(charArray[i]) + randomIndex + " ");
			System.out.println(builder.toString());
			builder.append(templates.get(randomIndex).getBody());
			templates.get(randomIndex).setBody(builder.toString());
		}

		for (TweetTemplate c : templates) {
			System.out.println(c.getBody());
		}
	}

	@Override
	public void applyPoison(AngelOfDeath deathAngel, String poisonWord) {
		incrementalPoison(poisonWord, deathAngel.getTemplates());
	}	
}
