package watermarker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.List;

import poison.enums.PoisonEnum;
import poison.interfaces.Poison;
import poison.types.PoisonClassList;
import poison.utils.AngelOfDeath;
import template.models.TweetTemplate;

public class Decrypter extends WatermarkHelper {
	
	protected AngelOfDeath deathAngel;
		
	public Decrypter(AngelOfDeath deathAngel){
		this.deathAngel = deathAngel;
	}
	
	public void decrypt(Class clazz, TweetTemplate template) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Method method;
		method = clazz.getMethod("removePoison", TweetTemplate.class);
		method.invoke(clazz.newInstance(), template);
	};
	
	public void decrypt() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException{
		
		List<Poison> poisons = deathAngel.getPoisons();
		List<TweetTemplate> templates = deathAngel.getPoisonedTweets();
		PoisonClassList poisonClasses = new PoisonClassList();
		
		for (TweetTemplate tweetTemplate : templates) {
			for (Class clazz: poisonClasses.getPoisonClasses()) {
				Object object = clazz.getMethod("getId").invoke(clazz.newInstance());
				int i = (int) object;
				if(getHashTagCount(tweetTemplate) == i){
					decrypt(clazz, tweetTemplate);
				}
			}
		}
	}
	
	private String checkPoisonType(TweetTemplate template){
		int hashTagCount = getHashTagCount(template);
		return PoisonEnum.getNameByCode(hashTagCount);
	}
	
}
