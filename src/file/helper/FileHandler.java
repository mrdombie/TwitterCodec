package file.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import template.models.TweetTemplate;

public class FileHandler {

	public void createSingleFilesOutput(List<TweetTemplate> templates, String tempDirectory) throws IOException {
		int index = 0;
		for (TweetTemplate tweetTemplate : templates) {
			FileUtils.writeStringToFile(new File(tempDirectory + index++ + ".txt" ), tweetTemplate.getBody(), true);
		}
	}
	
	public void createOneFileOutPut(List<TweetTemplate> templates, String tempDirectory) throws IOException{
		File file = new File(tempDirectory);
		for (TweetTemplate tweetTemplate : templates) {
			FileUtils.writeStringToFile(file, tweetTemplate.getBody(), true);
		}
	}
	
	public List<TweetTemplate> readFilesFromDirectory(String directory) throws IOException{
		
		List<TweetTemplate> tempList = new ArrayList<TweetTemplate>();
		File dir = new File(directory);

		System.out.println("Getting all files in " + dir.getCanonicalPath() + " including those in subdirectories");
		List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {		
			System.out.println("file: " + file.getCanonicalPath());
			TweetTemplate temp = new TweetTemplate();
			temp.setBody(FileUtils.readFileToString(file));
			tempList.add(temp);
		}
		return tempList;
	}
}
