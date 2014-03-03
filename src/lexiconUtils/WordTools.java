package lexiconUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import utils.ListReader;

public class WordTools {

	private static final String VERBLOCATION = "D:\\Programming\\Projects\\TwitterCodec\\dictionaries\\verb.txt";
	private static final String NOUNLOCATION = "D:\\Programming\\Projects\\TwitterCodec\\dictionaries\\noun.txt";
	private static final String SENSELOCATION = "D:\\Programming\\Projects\\TwitterCodec\\dictionaries\\sense.txt";
	private static final String INDEXLOCATION = "D:\\Programming\\Projects\\TwitterCodec\\dictionaries\\indexADV.txt";
		
	private String randomizer(String location) throws IOException{
		
		ListReader reader = new ListReader();
		List<String> wordList = reader.getList(location);
		
		Random rand = new Random(System.currentTimeMillis());
		System.out.println(wordList.size());
		String randomWord = wordList.get(rand.nextInt(wordList.size()));
		System.out.println(randomWord);
		return randomWord;
		
	}
	
	public String selectRandomNoun() throws IOException{
		return randomizer(NOUNLOCATION);
	}
	
	public String selectRandomSense() throws IOException{
		return randomizer(SENSELOCATION);
	}
	
	public String selectRandomIndexWord() throws IOException{
		return randomizer(INDEXLOCATION);
	}
	
	public String selectRandomVerb() throws IOException{
		return randomizer(VERBLOCATION);
	}
	
	public String generateHashTag(String word){
		StringBuilder builder = new StringBuilder();
		builder.append("#");
		builder.append(word);
		return builder.toString();
	}

}
