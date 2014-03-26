package lexiconUtils;

import java.io.IOException;

public interface Tag {

	public String selectRandom() throws IOException;
	public String replace(String templateString) throws IOException;
	
}
