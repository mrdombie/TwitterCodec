package cli;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class GeneratorCLI {

	public static void main(String args[]) {
		
		Options options = new Options();
		options.addOption("n", true, "[name] your name");
		
		Option timeOption = new Option("t", false, "current time");
		options.addOption(timeOption);

		// ** now lets parse the input
		CommandLineParser parser = new BasicParser();
		CommandLine cmd;
		
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException pe) {
			usage(options);
			return;
		}

		// ** now lets interrogate the options and execute the relevant parts

		if (cmd.hasOption("t")) {

			System.out.println("You have given argument is t");
			System.err.println("Date/Time: " + new java.util.Date());
		}

		if (cmd.hasOption("n")) {
			System.out.println("You have given argument is n");
			System.err.println("Nice to meet you: " + cmd.getOptionValue('n'));
		}

	}

	private static void usage(Options options) {

		// Use the inbuilt formatter class
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("Generator", options);
	}
}
