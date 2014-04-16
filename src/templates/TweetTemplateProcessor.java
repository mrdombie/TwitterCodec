package templates;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import utils.ModelReader;

public class TweetTemplateProcessor {

	public List<String> getTemplates() throws IOException{
		
		ModelReader reader = new ModelReader();
		List<String> templates = reader.getTemplatesAsStrings();
		return templates;
	}
			
}
