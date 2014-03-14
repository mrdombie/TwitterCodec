package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
 
public class TweetToFile {
	
	public static void writeTweetsToTxtFile(List<String> templateList) throws IOException {
		
		File file = new File("D:/Programming/Projects/TwitterCodec/output/template.txt");
		
		for (String string : templateList) {
			FileUtils.writeStringToFile(file, "\n" + string, true);
		}
	}
}