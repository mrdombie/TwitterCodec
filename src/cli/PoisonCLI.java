package cli;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import templates.TweetTemplateFactory;
import twitter4j.TwitterException;
import utils.constants.FileLocations;

public class PoisonCLI {
	
public static void main(String args[]) throws TwitterException, InterruptedException, ParserConfigurationException, IOException {
		
		//These Represent Each Option that is available to the command line -g Generate -etc etc	
		Options options = new Options();
		Option generator = OptionBuilder.withArgName("topic> <number of tweets> <location" )
	            .withValueSeparator(' ')
	            .hasArgs(3)
	            .withLongOpt("generate")
	            .withDescription("Use to generate tweet templates")
	            .create("g");
		
		options.addOption(generator);
		
		// ** now lets parse the input
		CommandLineParser parser = new BasicParser();
		CommandLine cmd;
		
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException pe) {
			usage(options);
			return;
		}

		if (cmd.hasOption("g")) {
			System.out.println("Generating Tweet Templates...");
			String[] arguements = cmd.getOptionValues("g");		
			TweetTemplateFactory factory = new TweetTemplateFactory(arguements[0], Integer.parseInt(arguements[1]));
			factory.createTemplatesExportToFile();
			System.out.println("Tweets have been Generated.. Please Check " + FileLocations.DIRECTORY + " for the template");
		}
	}

	private static void usage(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("Generator", options);
	}
}
