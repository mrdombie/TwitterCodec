package testing;

import java.io.IOException;

import lexiconUtils.WordTools;

import org.junit.Test;

public class TestRandomizer {

	@Test
	public void testRandomizer() throws IOException{
		
		WordTools tag = new WordTools();
		tag.selectRandomIndexWord();
		tag.selectRandomNoun();
		tag.selectRandomSense();
		tag.selectRandomVerb();
		
	}
	
}
