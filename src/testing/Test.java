package testing;

import java.io.IOException;
import java.sql.SQLException;

import storage.TweetStorage;
import twitter4j.TwitterException;

public class Test {

	 public static void main(String[] args) throws TwitterException, IOException, InterruptedException, SQLException, ClassNotFoundException{
	 	
		TweetStorage ts = new TweetStorage();
		ts.addTweets("money");
		
	}

}
