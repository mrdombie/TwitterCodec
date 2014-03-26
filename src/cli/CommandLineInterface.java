package cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import poison.interfaces.Poison;
import poison.types.Base64Poison;
import poison.utils.AngelOfDeath;
import templates.TweetTemplateFactory;
import cli.helper.CLI;
import cli.helper.CommandLineHelper;
import cli.helper.Option;

@CLI({
		@Option(opt = "h", longOpt = "help", description = "Help about this Twitter Codec"),
		@Option(opt = "i", longOpt = "insertData", required = true, hasArg = true, description = "Location of Data you need to insert into Tweets"),
		@Option(opt = "g", longOpt = "generate", required = true, hasArg = true, type = Integer.class, description = "Generate x amount of tweets", defaultValueStr = "10"),
		@Option(opt = "p", longOpt = "poisons", required = true, hasArg = true, description = "Types of poison to be used to hide Data"),
		@Option(opt = "D", longOpt = "decode", required = true, hasArg = true, description = "Decodes the tweet hidden message based upon the poisonType")})

		//@Option(opt = "t", description = "test", required = true, hasArg = false, type = Double.class, defaultValueStr = "0.5"),
		//-i "D:\Programming\Projects\TwitterCodec\poisons\poisondata.txt" -p "s" -g 50

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
			
			CommandLineInterface myInterface = new CommandLineInterface();
			myInterface.parsePoisonValues(pValue);
			myInterface.parseInsertValues(iValue);
			myInterface.parseGenerateValues(gValue);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parseInsertValues(String iValue) throws IOException{
		deathAngelOfDeath.poisonTemplatesUsing(iValue);
	};
	
	public void parsePoisonValues(String pValue){
		
		List<Poison> poisons = new ArrayList<Poison>();
		
		for (char c : pValue.toCharArray()) { 
			if(c == 's'){
				poisons.add(new Base64Poison());
			}
		}
		
		deathAngelOfDeath.usingTheFollowingPoisons(poisons);
		
	};
	
	public void parseGenerateValues(int gValue) throws Exception{
		TweetTemplateFactory templateF = new TweetTemplateFactory("BBC", gValue);
		deathAngelOfDeath.templates(templateF.createTweetTemplates());
		deathAngelOfDeath.poison();
		deathAngelOfDeath.cleanTheScene();
		deathAngelOfDeath.outPutCompleteInfectedList();
	};
	
}
