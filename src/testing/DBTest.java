package testing;

import java.io.IOException;
import java.sql.SQLException;

import lexiconUtils.WordTools;
import storage.Profiles;
import storage.Tweets;
import twitter4j.TwitterException;

public class DBTest {

	public static void main(String[] args) throws TwitterException,
			IOException, InterruptedException, SQLException,
			ClassNotFoundException {

		testAddTweets();
		testAddProfiles();
		
	}

	private static void testAddTweets() throws TwitterException,
			InterruptedException, SQLException, ClassNotFoundException {
		Tweets ts = new Tweets();
		ts.addTweets("money");
	}

	private static void testAddProfiles() throws TwitterException,
			InterruptedException, SQLException, ClassNotFoundException {
	
		Profiles profiles = new Profiles();
		profiles.addProfiles();
	
	}
}
