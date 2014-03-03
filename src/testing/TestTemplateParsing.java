package testing;

import java.io.IOException;

import org.junit.Test;

import templates.TweetTemplateProcessor;

public class TestTemplateParsing {

	@Test
	public void testTemplateIsStored() throws IOException {

		TweetTemplateProcessor tsp = new TweetTemplateProcessor();
		tsp.getTemplates();
		
	}

}
