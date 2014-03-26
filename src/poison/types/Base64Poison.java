package poison.types;

import java.io.IOException;

import org.apache.xerces.impl.dv.util.Base64;

import poison.interfaces.Poison;
import poison.utils.AngelOfDeath;
import poison.utils.Injector;
import template.models.TweetTemplate;
import watermark.morcecode.Encrypter;

import com.jcabi.aspects.Loggable;

public class Base64Poison implements Poison {
	
	@Override
	public TweetTemplate applyPoison(AngelOfDeath deathAngel, String word) throws IOException {
		return waterMarkTweet(base64Poison(deathAngel, word));
	}
	
	@Loggable(Loggable.INFO)
	private TweetTemplate base64Poison(AngelOfDeath deathAngel, String word){
		TweetTemplate template = deathAngel.getRandomTemplate();
		Injector.injectKeyWord(word, template);
		template.setBody(Base64.encode(template.getBody().getBytes()));		
		return template;	
	}

	@Override
	public void removePoison(TweetTemplate template) {
		byte[] body = Base64.decode(template.getBody());
		template.setBody(new String(body));
		System.out.println(template.getBody()  + "DEBUG");
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
