package watermark.morcecode;

import java.util.EnumSet;
import java.util.List;

import template.models.TweetTemplate;
import watermarker.WatermarkHelper;
import watermarker.keyIndex.KeyIndex;

public class EncrypterTODO extends WatermarkHelper {
	
	protected TweetTemplate template;
	protected List<String> hashTags;
	protected List<Integer> keyBlock;
	protected Class clazz;
	
	public EncrypterTODO(TweetTemplate template, Class clazz) {
		this.template = template;
		this.hashTags = getTags(template);
		this.clazz = clazz;
		this.keyBlock = getKeyBlock();
	}
	
	private void setMorseCodeKey(){};
	
	private int getKeyType(){
		for (KeyIndex keyIndex : EnumSet.allOf(KeyIndex.class)) {
			if(clazz.getSimpleName().equals(keyIndex.name())){
				return keyIndex.ordinal();
			}
		}
		return 9999;
	}
	
	private List<Integer> getKeyBlock(){
		for (String hashTag : hashTags) {
			keyBlock.add(hashTag.length());
		}
		return keyBlock;
	};
	
	private int getSumLength(){
		int sum = 0;
		for (int i : keyBlock) {
			sum =+ i;
		}
		return sum;
	}
	
	private void workOut(){
		while(getKeyType() != getSumLength()){
			int difference  = getKeyType() - getSumLength();
			assignLengths(template.getBody(), difference);
		}
	}
	
	private void assignLengths(String body, int difference){
		String[] arrayStrings = splitString(body);
		while(difference != 0){
			for (int i = 0; i < arrayStrings.length; i++) {
				if(arrayStrings[i].startsWith("#")){
					arrayStrings[i] = arrayStrings[i].substring(0, arrayStrings[i].length() - 1);
					difference--;
				}
			}
		}
	}	
}
