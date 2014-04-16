package watermark.morcecode;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;

import lexiconUtils.WordTools;
import poison.enums.PoisonEnum;
import template.models.TweetTemplate;
import watermarker.WatermarkHelper;

public class Encrypter extends WatermarkHelper{

	protected TweetTemplate template;
	
	public Encrypter(TweetTemplate template){
		this.template = template;
	}
	
	public TweetTemplate getTweetTemplate(){
		return template;
	}
	
	private String getClassSimpleName(Class clazz){
		String myClazz = clazz.getSimpleName().substring(0 , 3);
		return myClazz;
	};
	
	private int whatShouldCountBe(Class clazz){
		int count = 0;
		for (PoisonEnum poison : EnumSet.allOf(PoisonEnum.class)) {
			if(getClassSimpleName(clazz).equalsIgnoreCase(poison.name())){
				count = poison.ordinal() + 1;
				return count;
			}
		}
		return count;
	}
	
	public void checkAndFix(Class clazz) throws IOException{
		int whatSizeShouldBe = whatShouldCountBe(clazz);
		int hashTagCount = getHashTagCount(template);
		
		if(whatSizeShouldBe != hashTagCount){
			fixHashTagSize(whatSizeShouldBe, hashTagCount);
		}
	}
	
	private void fixHashTagSize(int mustBeSize, int actualHashTagCount) throws IOException{
		int difference = 0;
		if(actualHashTagCount > mustBeSize){
			difference = actualHashTagCount - mustBeSize;
			reduceTagCount(difference);
		}else if(actualHashTagCount < mustBeSize){
			difference = mustBeSize - actualHashTagCount;
			increaseTagCount(difference);
		}
	};
	
	private String reduceTagCount(int difference) {
		String[] templateBody = splitString(template.getBody());
		for (int i = 0; i < templateBody.length; i++) {
			if (templateBody[i].startsWith("#")) {
				templateBody[i] = templateBody[i].replace("#", "@");
				difference--;
				if (difference == 0) {
					break;
				}
			}	
		}
	
		System.out.println(Arrays.toString(templateBody));
		return Arrays.toString(templateBody);
	}
	
	private void increaseTagCount(int difference) throws IOException{
		StringBuilder builder = new StringBuilder();
		builder.append(template.getBody());
		
		for (int i = 0; i < difference; i++) {
			builder.append(" ");
			builder.append(WordTools.generateHashTag());
		}
		
		System.out.println(builder.toString());
		template.setBody(builder.toString());
	}
	
	public static void main(String[] args) throws IOException {
		
		TweetTemplate template = new TweetTemplate();
		template.setBody("BLAH BLAH BLAH #TWEET BLAH #TWEET BLAH BLAH");
		
		Encrypter enc = new Encrypter(template);
		
	}	
}

