package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import template.models.TweetTemplate;
import contruction.CaptureTweet;

public class ModelReader {
	
	private final static Logger LOGGER = Logger.getLogger(CaptureTweet.class
			.getName());
	
	private static final String LOCATION = "D:\\Programming\\Projects\\TwitterCodec\\output\\template.txt";
	private static final String POISONDATALOCATION = "D:\\Programming\\Projects\\TwitterCodec\\poisons\\poisondata.txt";
	
	public static List<String> getTemplatesAsStrings(String location) throws IOException {
		File file = new File(location);
		List<String> wordList = FileUtils.readLines(file, "UTF-8");
		return wordList;
	}
	
	public static List<String> getTemplatesAsStrings() throws IOException {
		return getTemplatesAsStrings(LOCATION);
	}
	
	public static List<TweetTemplate> getTemplates(String directoryLocation) throws IOException{
		
		File file = new File(directoryLocation);
		List<TweetTemplate> tweetTemplates = new ArrayList<>();
		List<String> wordList = FileUtils.readLines(file, "UTF-8");
	
		for (String string : wordList) {
			TweetTemplate template = new TweetTemplate();
			template.setBody(string);
			tweetTemplates.add(template);
		}
		
		return tweetTemplates;
	}
	
	public static List<TweetTemplate> getTemplates() throws IOException{
		return getTemplates(LOCATION);
	}
	
	public static List<String> getPoisonDataList() throws IOException{
		return getTemplatesAsStrings(POISONDATALOCATION);
	}
}
