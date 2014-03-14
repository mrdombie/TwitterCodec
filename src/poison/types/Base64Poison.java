package poison.types;

import java.util.List;

import org.apache.xerces.impl.dv.util.Base64;

import poison.interfaces.Poison;
import poison.utils.AngelOfDeath;
import poison.utils.Injector;
import template.models.TweetTemplate;

public class Base64Poison implements Poison {

	@Override
	public void applyPoison(AngelOfDeath deathAngel, String word) {
		base64Poison(deathAngel, word);
	}
	
	private String base64Poison(AngelOfDeath deathAngel, String word){
		
		TweetTemplate template = deathAngel.getRandomTemplate();
		Injector.injectKeyWord(word, template);
		
		String encoded = Base64.encode(template.getBody().getBytes());
		return encoded;
	}
}
