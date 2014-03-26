package lexiconUtils;

import java.io.IOException;

import com.jcabi.aspects.Loggable;

public class HashTagger {
	
	@Loggable(Loggable.INFO)
	public static String generateHashTag() throws IOException{
		StringBuilder builder = new StringBuilder();
		builder.append("#");
		builder.append(WordTools.selectRandomSense());
		return builder.toString();
	};
	
}
