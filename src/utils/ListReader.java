package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import contruction.CaptureTweet;

public class ListReader {
	
	private final static Logger LOGGER = Logger.getLogger(CaptureTweet.class
			.getName());
	
	public List<String> getList(String location) throws IOException {

		File file = new File(location);
		List<String> wordList = FileUtils.readLines(file, "UTF-8");
		return wordList;
		
	}
	
}
