package cli;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import poison.interfaces.Poison;
import poison.types.Base64Poison;
import poison.types.NewPoisonType;
import poison.utils.AngelOfDeath;
import template.models.TweetTemplate;
import templates.TweetTemplateFactory;
import watermark.morcecode.Decrypter;
import cli.helper.CLI;
import cli.helper.CommandLineHelper;
import cli.helper.Option;
import file.helper.FileHandler;

@CLI({
		@Option(opt = "h", longOpt = "help", description = "Help about this Twitter Codec"),
		@Option(opt = "i", longOpt = "insertData", required = false, hasArg = true, description = "Location of Data you need to insert into Tweets"),
		@Option(opt = "g", longOpt = "generate", required = false, hasArg = true, type = Integer.class, description = "Generate x amount of tweets", defaultValueStr = "10"),
		@Option(opt = "t", longOpt = "topic", required = false, hasArg = true, type = String.class, description = "Topic of the tweets being Generated"),
		@Option(opt = "p", longOpt = "poisons", required = false, hasArg = true, description = "Types of poison to be used to hide Data"),
		@Option(opt = "o", longOpt = "outputDirectory", required = false, hasArg = true, description = "Location of the folder where you want the tweets kept"),
		@Option(opt = "D", longOpt = "decode", required = false, hasArg = true, description = "Decodes the tweet hidden message based upon the poisonType")})

		//-i "D:\Programming\Projects\TwitterCodec\poisons\poisondata.txt" -p "s" -g 50
		//-i "D:\Programming\Projects\TwitterCodec\poisons\poisondata.txt" -p "s" -g 10 -o "D:\Programming\Projects\TwitterCodec\poisons\"	

public class CommandLineInterface {
	
	AngelOfDeath deathAngelOfDeath = new AngelOfDeath();

	public static void main(String[] args) {
		try {
			
			CommandLineHelper cmdHelper = null;
			cmdHelper = new CommandLineHelper(CommandLineInterface.class);
			cmdHelper.parse(args);
			
			String iValue = (String) cmdHelper.getOptionValue("i");
			String pValue = (String) cmdHelper.getOptionValue("p");
			int gValue = (Integer) cmdHelper.getOptionValue("g");
			String tValue = (String) cmdHelper.getOptionValue("t");
			String oValue = (String) cmdHelper.getOptionValue("o");
			String dValue = (String) cmdHelper.getOptionValue("D");
			
			CommandLineInterface myInterface = new CommandLineInterface();
			
			if(!"".equals(iValue) && iValue != null){
				myInterface.parseInsertValues(iValue);
			}
			
			if(!pValue.equals("") && pValue != null){
				try {
					myInterface.parsePoisonValues(pValue);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("ERROR NO VALUES TO BE INSERTED HAVE BEEN ASSIGNED");
				}
			}
				
			if(gValue != 0 && gValue != 10){
				myInterface.parseGenerateValues(gValue, tValue); //Number of Tweets and the Topic We want Generated on
			}
			
			if(!oValue.equals("") && oValue != null){
				myInterface.parseOutPutValues(oValue);
			}
			
			if(!"".equals(dValue)){
				myInterface.parseDecryptValues(dValue);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parseInsertValues(String iValue) throws IOException{
		deathAngelOfDeath.poisonTemplatesUsing(iValue);
	};
	
	public void parsePoisonValues(String pValue) throws Exception{
		System.out.println("USING THE FOLLOWING ENCRYPTION METHODS: " );
		List<Poison> poisons = new ArrayList<Poison>();
		
		for (char c : pValue.toCharArray()) { 
			if(c == 's'){
				poisons.add(new Base64Poison());
				System.out.println("BASE64 POISON");
			}
			if(c == 'h'){
				poisons.add(new Base64Poison());
				System.out.println("HEX POISON");
			}
			if(c == 'm'){
				poisons.add(new NewPoisonType());
				System.out.println("MOO POISON");
			}
		}
		
		deathAngelOfDeath.usingTheFollowingPoisons(poisons);
		deathAngelOfDeath.poison();
		deathAngelOfDeath.cleanTheScene();
		
	};
	
	public void parseGenerateValues(int gValue, String tValue) throws Exception{
		System.out.println("GENERATING " + gValue + " TWEETS, USING THE FOLLOWING TOPIC " + tValue);
		TweetTemplateFactory templateF = new TweetTemplateFactory(tValue, gValue);
		deathAngelOfDeath.templates(templateF.createTweetTemplates());
		deathAngelOfDeath.cleanTheScene();
	};
	
	public void parseOutPutValues(String oValue){
		FileHandler fh = new FileHandler();
		try {
			fh.createSingleFilesOutput(deathAngelOfDeath.getFinalTweetList(), oValue);
			for (TweetTemplate template : deathAngelOfDeath.getFinalTweetList()) {
				System.out.println("FINALIZED TEMPLATE " + template.getBody());
			}
			System.out.println("TWEETS CAN BE FOUND IN THE FOLLOWING DIRECTORY " + oValue);
		} catch (IOException e) {
			System.out.println(oValue + " is an invalid Directory Location - Please check that the folder is real");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parseDecryptValues(String directory) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException{
		System.out.println("DECODING:");
		FileHandler fh = new FileHandler();
		Decrypter decrypter = new Decrypter();
		decrypter.resolvePoisons(fh.readFilesFromDirectory(directory));
	}
}
