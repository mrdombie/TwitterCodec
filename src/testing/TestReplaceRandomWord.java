package testing;

import java.io.IOException;
import java.util.List;

import lexiconUtils.WordTools;

import org.junit.Test;

import templates.TweetTemplateProcessor;

public class TestReplaceRandomWord {

	@Test
	public void replaceWordTest() throws IOException{
		
		TweetTemplateProcessor tsp = new TweetTemplateProcessor();
		WordTools select = new WordTools();
		List<String> templates = tsp.getTemplates();
		System.out.println(tsp.replaceWithRandomNoun(templates.get(1)));
	}
	
	@Test
	public void replaceSingleWordTest() throws IOException{
		
		TweetTemplateProcessor proc = new TweetTemplateProcessor();
		System.out.println(proc.replaceWithRandomNoun("[NOUN]"));
		
	}
	
	@Test
	public void replaceWordAdvanced() throws IOException{
		
		TweetTemplateProcessor tsp = new TweetTemplateProcessor();
		WordTools select = new WordTools();
		List<String> templates = tsp.getTemplates();
		System.out.println(tsp.replaceWithRandomNoun((templates.get(1))));
	}
	
}
