package poison.types;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.xerces.impl.dv.util.Base64;

import poison.interfaces.Poison;
import poison.utils.AngelOfDeath;
import poison.utils.Injector;
import template.models.TweetTemplate;
import templates.generator.TweetTemplateGenerator;
import twitter4j.TwitterException;
import watermark.morcecode.Encrypter;

import com.jcabi.aspects.Loggable;

public class Base64Poison extends PoisonHelper implements Poison {
	
	@Override
	public TweetTemplate applyPoison(AngelOfDeath deathAngel, String word) throws IOException, TwitterException, InterruptedException, ParserConfigurationException {
		return waterMarkTweet(base64Poison(deathAngel, word));
	}
	
	private TweetTemplate base64Poison(AngelOfDeath deathAngel, String word) throws TwitterException, InterruptedException, ParserConfigurationException, IOException{
		TweetTemplateGenerator templateGenerator = new TweetTemplateGenerator();
		TweetTemplate template = replaceAndTidyTemplate(Injector.injectKeyWord(word, templateGenerator.generateSingleTemplate()));
		template.setBody(Base64.encode(template.getBody().getBytes()));
		return template;	
	}

	@Override
	public TweetTemplate removePoison(TweetTemplate template) {
		String[] splitArray = template.getBody().split(" ");
		
		//Fix this later I think - its a bit hacky
		for (int i = 0; i < splitArray.length; i++) {
			System.out.println(splitArray[i]);
			if (splitArray[i].startsWith("#")) {
				splitArray[i] = "";
			}
		}
		String arrayBody = StringUtils.join(splitArray);
		byte[] body = Base64.decode(arrayBody);
		template.setBody(new String(body));
		return template;
	}
	
	@Override
	public TweetTemplate waterMarkTweet(TweetTemplate template) throws IOException {
		Encrypter enc = new Encrypter(template);
		enc.checkAndFix(Base64Poison.class);
		return enc.getTweetTemplate();
	}

	@Override
	public int getId() {
		return 1;
	}
	
}
