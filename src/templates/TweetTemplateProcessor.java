package templates;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import lexiconUtils.WordType;
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
				
	private void logTemplateInfo(List<String> myInfo){
		for (String contents : myInfo) {
			LOGGER.info(contents);
		}
	}
		
}
