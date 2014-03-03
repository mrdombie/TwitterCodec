package testing;

import java.io.IOException;

import org.junit.Test;

import templates.TweetSchemaProcessor;

public class TestTemplateParsing {

	@Test
	public void testTemplateIsStored() throws IOException {

		TweetSchemaProcessor tsp = new TweetSchemaProcessor();
		tsp.getTemplates();
		
	}

}
