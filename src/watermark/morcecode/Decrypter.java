package watermark.morcecode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import poison.enums.PoisonEnum;
import poison.types.PoisonClassList;
import template.models.TweetTemplate;

public class Decrypter {
	
	public void getPoisonInfo(){};
	
	public void resolvePoisons(List<TweetTemplate> templates) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException{
		
		PoisonClassList classList = new PoisonClassList();
		List<Class<?>> poisonss = classList.getPoisonClasses();
		
		for (TweetTemplate tweetTemplate : templates) {
			int tagCount = getHashTagCount(tweetTemplate);		
				for (Class clazz: poisonss) {
					Object object = clazz.getMethod("getId").invoke(clazz.newInstance());
					int i = (int) object;
					if(getHashTagCount(tweetTemplate) == i){
						System.out.println("BEFORE: " + tweetTemplate.getBody());
						TweetTemplate temp = decrypt(clazz, tweetTemplate);
						System.out.println("AFTER: " + temp.getBody());
					}
				}
		  }
	};
	
	private TweetTemplate decrypt(Class clazz, TweetTemplate template) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Method method;
		method = clazz.getMethod("removePoison", TweetTemplate.class);
		Object tweetTemplate = method.invoke(clazz.newInstance(), template);
		TweetTemplate myTemp = (TweetTemplate) tweetTemplate;
		return myTemp;
	};
	
	private List<String> getTags(TweetTemplate template) {

		Pattern tagMatcher = Pattern.compile("#+[-\\w]+\\b");
		Matcher m = tagMatcher.matcher(template.getBody());
		ArrayList<String> hashtags = new ArrayList<>();

		while (m.find()) {
			hashtags.add(m.group());
		}
		return hashtags;
	}
	
	private int getHashTagCount(TweetTemplate template){
		int numberOfHashTags = 0;
		List<String> hashTags = getTags(template);
		for (String string : hashTags) {
			numberOfHashTags++;
		}
		return numberOfHashTags;
	}
}
