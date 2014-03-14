package templates;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import lexiconUtils.WordTools;
import utils.ModelReader;

public class TweetTemplateProcessor {

	private static final String TEMPLATELOCATION = "D:\\Programming\\Projects\\TwitterCodec\\output\\template.txt";
	private final static Logger LOGGER = Logger.getLogger(TweetTemplateProcessor.class.getName());
	
	public List<String> getTemplates() throws IOException{
		
		ModelReader reader = new ModelReader();
		List<String> templates = reader.getTemplatesAsStrings();
		logTemplateInfo(templates);		
		return templates;
		
	}
			
	private String replaceWithRandomVerb(String templateString) throws IOException{
		
		WordTools selector = new WordTools();
		String[] splitArray = splitString(templateString);

		for (int i = 0; i < splitArray.length; i++) {	
			if (splitArray[i].equalsIgnoreCase("[VERB]")) {
				splitArray[i] = selector.selectRandomVerb();
			}
		}
		
		return arrayToString(splitArray);		
	}
	
	private String replaceWithRandomSense(String templateString) throws IOException{
		
		WordTools selector = new WordTools();
		String[] splitArray = splitString(templateString);

		for (int i = 0; i < splitArray.length; i++) {	
			if (splitArray[i].equalsIgnoreCase("[SENSE]")) {
				splitArray[i] = selector.selectRandomSense();
			}
		}
		
		return arrayToString(splitArray);
		
	}
	
	private String replaceHashTag(String templateString) throws IOException{
		
		WordTools selector = new WordTools();
		String tag = selector.generateHashTag(selector.selectRandomSense());
		String[] splitArray = splitString(templateString);

		for (int i = 0; i < splitArray.length; i++) {	
			if (splitArray[i].equalsIgnoreCase("[HASHTAG]")) {
				splitArray[i] = tag;
			}
		}
		
		return arrayToString(splitArray);
	
	}
	
	private String replaceURL(String templateString) throws IOException{

		WordTools selector = new WordTools();
		String[] splitArray = splitString(templateString);

		for (int i = 0; i < splitArray.length; i++) {	
			if (splitArray[i].equalsIgnoreCase("[HTTP]")) {
				splitArray[i] = selector.selectRandomNoun();
			}
		}
		
		return arrayToString(splitArray);
	}
	
	public String replaceWithRandomNoun(String templateString) throws IOException {

		WordTools selector = new WordTools();		
		String[] splitArray = splitString(templateString);

		for (int i = 0; i < splitArray.length; i++) {	
			if (splitArray[i].equalsIgnoreCase("[NOUN]")) {
				splitArray[i] = selector.selectRandomNoun();
			}
		}

		return arrayToString(splitArray);
	
	}
	
	private String[] splitString(String templateString){	
		String[] splitArray = templateString.split("\\s+");
		return splitArray;
	}
	
	private String arrayToString(String[] splitArray){
		
		StringBuilder builder = new StringBuilder();
		for (String value : splitArray) {
		    builder.append(value + " ");
		}
		
		return builder.toString();
		
	}
	
	private void logTemplateInfo(List<String> myInfo){
		for (String contents : myInfo) {
			LOGGER.info(contents);
		}
	}
		
}
