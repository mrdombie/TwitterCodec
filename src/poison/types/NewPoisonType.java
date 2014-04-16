package poison.types;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.impl.dv.util.Base64;

import poison.interfaces.Poison;
import poison.utils.AngelOfDeath;
import poison.utils.Injector;
import template.models.TweetTemplate;
import templates.generator.TweetTemplateGenerator;
import twitter4j.TwitterException;
import watermark.morcecode.Encrypter;

public class NewPoisonType extends PoisonHelper implements Poison{

	@Override
	public int getId() {
		return 3;
	}

	@Override
	public TweetTemplate applyPoison(AngelOfDeath deathAngel, String poisonWord)
			throws IOException, TwitterException, InterruptedException,
			ParserConfigurationException {
		
		return waterMarkTweet(mooPoison(deathAngel, poisonWord));
	}
	
	private TweetTemplate mooPoison(AngelOfDeath deathAngel, String word) throws TwitterException, InterruptedException, ParserConfigurationException, IOException{
		
		TweetTemplateGenerator templateGenerator = new TweetTemplateGenerator();
		TweetTemplate template = replaceAndTidyTemplate(Injector.injectKeyWord(word, templateGenerator.generateSingleTemplate()));
		
		template.setBody("MOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		return template;
	}

	@Override
	public TweetTemplate removePoison(TweetTemplate template) {
		template.setBody("I think the template was eaten by cows... ooops.");
		return template;
	}

	@Override
	public TweetTemplate waterMarkTweet(TweetTemplate template)
			throws IOException {
		Encrypter enc = new Encrypter(template);
		enc.checkAndFix(NewPoisonType.class);
		return enc.getTweetTemplate();
	}

}
