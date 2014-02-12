package testing;

import java.io.IOException;
import java.sql.SQLException;

import storage.Profiles;
import storage.TweetStorage;
import twitter4j.TwitterException;

public class Test {

	public static void main(String[] args) throws TwitterException,
			IOException, InterruptedException, SQLException,
			ClassNotFoundException {

		//testAddTweets();
		testAddProfiles();

	}

	private static void testAddTweets() throws TwitterException,
			InterruptedException, SQLException, ClassNotFoundException {
		TweetStorage ts = new TweetStorage();
		ts.addTweets("money");
	}

	private static void testAddProfiles() throws TwitterException,
			InterruptedException, SQLException, ClassNotFoundException {
	
		Profiles profiles = new Profiles();
		profiles.addProfiles();
	
	}

}
