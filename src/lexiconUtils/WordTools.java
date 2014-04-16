package lexiconUtils;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import constants.WordTags;
import utils.ModelReader;
import edu.stanford.nlp.ling.WordTag;

public class WordTools {

	private static final String VERBLOCATION = "D:\\Programming\\Projects\\TwitterCodec\\dictionaries\\verb.txt";
	private static final String NOUNLOCATION = "D:\\Programming\\Projects\\TwitterCodec\\dictionaries\\noun.txt";
	private static final String SENSELOCATION = "D:\\Programming\\Projects\\TwitterCodec\\dictionaries\\sense.txt";
	private static final String INDEXLOCATION = "D:\\Programming\\Projects\\TwitterCodec\\dictionaries\\indexADV.txt";
	
	public static String randomizer(String location) throws IOException{
		
		ModelReader reader = new ModelReader();
		List<String> wordList = reader.getTemplatesAsStrings(location);
		Random rand = new Random(System.currentTimeMillis());
		String randomWord = wordList.get(rand.nextInt(wordList.size()));
		return randomWord;
		
	}
	
	public static String replaceWithRandomWord(WordType tags, String templateString) throws IOException {
		
		String[] splitArray = splitString(templateString);
		for (int i = 0; i < splitArray.length; i++) {
			if (splitArray[i].equalsIgnoreCase(tags.toString())) {
				splitArray[i] = tags.selectRandom();
			}
		}
		return arrayToString(splitArray);
	}
	
	public static String replaceWithFakeURL(){
		
		StringBuilder builder = new StringBuilder();
		builder.append("http://");
		final String alphabet = "ABCDEabcde";
	    final int N = alphabet.length();

	    Random r = new Random();

	    for (int i = 0; i < 7; i++) {
	       builder.append(alphabet.charAt(r.nextInt(N)));
	    }
	    builder.append(".net");
		System.out.println(builder.toString());
		
		return builder.toString();
	}
		
	private static String[] splitString(String templateString){	
		String[] splitArray = templateString.split("\\s+");
		return splitArray;
	}
	
	private static String arrayToString(String[] splitArray){
		
		StringBuilder builder = new StringBuilder();
		for (String value : splitArray) {
		    builder.append(value + " ");
		}
		
		return builder.toString();
		
	}
	
	public static String selectRandomNoun() throws IOException{
		return randomizer(NOUNLOCATION);
	}
	
	public static String selectRandomSense() throws IOException{
		return randomizer(SENSELOCATION);
	}
	
	public static String selectRandomIndexWord() throws IOException{
		return randomizer(INDEXLOCATION);
	}
	
	public static String selectRandomVerb() throws IOException{
		return randomizer(VERBLOCATION);
	}
	
	public static String generateHashTag() throws IOException{
		return HashTagger.generateHashTag();
	}
	
}
