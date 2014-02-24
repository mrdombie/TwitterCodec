package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import twitter4j.TwitterException;
 
public class TweetToFile {
	
	public static void main(String[] args) throws ParserConfigurationException, TwitterException, InterruptedException, IOException {
		
		List<String> list = null;
		list = TweetNodes.getTagSchema("foo");
		writeTweets(list);
		
	}
	
	public static void writeTweets(List<String> list) {
 
		File file = new File("D:/Programming/Projects/TwitterCodec/output/template.txt");
 
		try (FileOutputStream fop = new FileOutputStream(file)) {
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			byte[] contentInBytes = null;
			
			for (String string : list) {
				String append = string + " ";
				contentInBytes = append.getBytes();
				fop.write(contentInBytes);
				fop.flush();
				
			}
			
			fop.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}