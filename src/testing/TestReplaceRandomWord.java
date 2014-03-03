package testing;

import java.io.IOException;
import java.util.List;

import lexiconUtils.WordTools;

import org.junit.Test;

import templates.TweetSchemaProcessor;

public class TestReplaceRandomWord {

	@Test
	public void replaceWordTest() throws IOException{
		
		TweetSchemaProcessor tsp = new TweetSchemaProcessor();
		WordTools select = new WordTools();
		List<String> templates = tsp.getTemplates();
		System.out.println(tsp.replaceWithRandomNoun(templates.get(1)));
	}
	
	@Test
	public void replaceSingleWordTest() throws IOException{
		TweetSchemaProcessor proc = new TweetSchemaProcessor();
		System.out.println(proc.replaceWithRandomNoun("[NOUN]"));
		
	}
	
}
