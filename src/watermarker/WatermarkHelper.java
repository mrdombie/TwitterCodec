package watermarker;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import template.models.TweetTemplate;

public class WatermarkHelper {
		
	public List<String> getTags(TweetTemplate template){
		
		Pattern tagMatcher = Pattern.compile("#+[-\\w]+\\b");
		Matcher m = tagMatcher.matcher(template.getBody());
		ArrayList<String> hashtags = new ArrayList<>();

		while (m.find()) {
		    hashtags.add(m.group());
		}
		return hashtags;
	}
	
	public static String[] splitString(String templateString){	
		String[] splitArray = templateString.split("\\s+");
		return splitArray;
	}
	
	public int getHashTagCount(TweetTemplate template){
		int numberOfHashTags = 0;
		List<String> hashTags = getTags(template);
		for (String string : hashTags) {
			numberOfHashTags++;
		}
		return numberOfHashTags;
	}
	
}
