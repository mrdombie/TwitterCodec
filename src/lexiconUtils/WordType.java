package lexiconUtils;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import utils.ModelReader;
import watermark.morcecode.MorseCode;

public enum WordType implements Tag{
	
	NOUN("[NOUN]"){

		@Override
		public String selectRandom() throws IOException {
			return WordTools.selectRandomNoun();
		}

		@Override
		public String replace(String templateString) throws IOException {
			return WordTools.replaceWithRandomWord(NOUN, templateString);
		}
		
	},
	
	SENSE("[SENSE]"){

		@Override
		public String selectRandom() throws IOException {
			return WordTools.selectRandomSense();
		}

		@Override
		public String replace(String templateString) throws IOException {
			return WordTools.replaceWithRandomWord(SENSE, templateString);
		}
		
	},
	
	INDEX("[INDEX]"){

		@Override
		public String selectRandom() throws IOException {
			return WordTools.selectRandomIndexWord();
		}

		@Override
		public String replace(String templateString) throws IOException {
			return WordTools.replaceWithRandomWord(INDEX, templateString);
		}
		
	},
	
	VERB("[VERB]"){

		@Override
		public String selectRandom() throws IOException {
			return WordTools.selectRandomVerb();
		}

		@Override
		public String replace(String templateString) throws IOException {
			return WordTools.replaceWithRandomWord(VERB, templateString);
		}
		
	},
	
	HTTP("[HTTP]"){

		@Override
		public String selectRandom() throws IOException {
			return WordTools.replaceWithFakeURL();
		}

		@Override
		public String replace(String templateString) throws IOException {
			return WordTools.replaceWithRandomWord(HTTP, templateString);
		}
		
	},
	
	ADJECTIVE("[ADJECTIVE]"){

		@Override
		public String selectRandom() throws IOException {
			return WordTools.selectRandomVerb();
		}

		@Override
		public String replace(String templateString) throws IOException {
			return WordTools.replaceWithRandomWord(ADJECTIVE , templateString);
		}
		
	},
	
	HASHTAG("[HASHTAG]"){

		@Override
		public String selectRandom() throws IOException {
			return HashTagger.generateHashTag();
		}

		@Override
		public String replace(String templateString) throws IOException {
			return WordTools.replaceWithRandomWord(HASHTAG , templateString);
		}
		
	};

	private String type;
		
	WordType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString(){
		return type;
	}
	
	public String getWordType(String string){
		return type;
	}

}
