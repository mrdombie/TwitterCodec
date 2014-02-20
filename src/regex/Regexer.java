package regex;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexer {

	//Small Util Class that was needed to create the dictionary list of verbs etc
	
	final static String FILE_NAME = "C:/Users/mrdombie/Desktop/dict/index.verb";
	final static String OUTPUT_FILE_NAME = "C:/Users/mrdombie/Desktop/dict/outputttttt.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;

	public static void main(String... aArgs) throws IOException {
		Regexer text = new Regexer();

		// treat as a large file - use some buffering
		text.readLargerTextFile(FILE_NAME);

	}

	// For smaller files

	/**
	 * Note: the javadoc of Files.readAllLines says it's intended for small
	 * files. But its implementation uses buffering, so it's likely good even
	 * for fairly large files.
	 */
	
	List<String> readSmallTextFile(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		return Files.readAllLines(path, ENCODING);
	}

	void writeSmallTextFile(List<String> aLines, String aFileName)
			throws IOException {
		Path path = Paths.get(aFileName);
		Files.write(path, aLines, ENCODING);
	}

	// For larger files

	void readLargerTextFile(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				// process each line in some way
				log(scanner.nextLine());
			}
		}
	}

	private static void log(String aMsg) {
		
		Pattern p = Pattern.compile("^(\\S+?)[\\s%]");
		Matcher m = p.matcher(aMsg);
		if(m.find()){
		System.out.println(m.group());
		}
		//System.out.println(String.valueOf(aMsg));
	}

}