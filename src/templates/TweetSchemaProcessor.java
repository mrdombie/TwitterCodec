package templates;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import edu.stanford.nlp.ling.Word;
import lexiconUtils.WordTools;
import utils.ListReader;

public class TweetSchemaProcessor {

	private static final String TEMPLATELOCATION = "D:\\Programming\\Projects\\TwitterCodec\\output\\template.txt";
	private final static Logger LOGGER = Logger.getLogger(TweetSchemaProcessor.class.getName());
	
	public List<String> getTemplates() throws IOException{
		
		ListReader reader = new ListReader();
		List<String> templates = reader.getList(TEMPLATELOCATION);
		logTemplateInfo(templates);		
		return templates;
		
	}
		
	private String replaceWithRandomNoun(String templateString) throws IOException{
		
		WordTools selector = new WordTools();
		String replacementString = templateString.replace("[NOUN]", selector.selectRandomNoun());
		return replacementString;
		
	}
	
	private String replaceWithRandomVerb(String templateString) throws IOException{
		
		WordTools selector = new WordTools();
		String replacementString = templateString.replace("[VERB]", selector.selectRandomNoun());
		return replacementString;
		
	}
	
	private String replaceWithRandomSense(String templateString) throws IOException{
		
		WordTools selector = new WordTools();
		String replacementString = templateString.replace("[SENSE]", selector.selectRandomSense());
		return replacementString;
		
	}
	
	private String replaceHashTag(String templateString) throws IOException{
		WordTools selector = new WordTools();
		String tag = selector.generateHashTag(selector.selectRandomSense());
		String replacementString = templateString.replace("[HASHTAG]", tag);
		return replacementString;
	}
	
	private String replaceURL(String templateString) throws IOException{
		WordTools selector = new WordTools();
		String replacementString = templateString.replace("[HTTP]", selector.selectRandomNoun());
		return replacementString;
	}
	
	private void logTemplateInfo(List<String> myInfo){
		for (String contents : myInfo) {
			LOGGER.info(contents);
		}
	}
		
}
