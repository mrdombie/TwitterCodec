package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import contruction.CaptureTweet;
import twitter4j.Status;
import twitter4j.TwitterException;

public class Tweets extends DBHelper {
	
	private final static Logger LOGGER = Logger.getLogger(CreateTables.class
			.getName());
	
	private List<Status> status = null;
	private Statement stmt = null;
	
	private final String INSERTSQL = "INSERT INTO TWEETS(PROFILE_ID, TWEET_ID, MESSAGE, CREATEDDATE, URL, HASHTAGS, GEOLOCATION) VALUES (?,?,?,?,?,?,?)";
		
	public void addTweets(String topic) throws TwitterException,
			InterruptedException, SQLException, ClassNotFoundException {

		getConnection();
		
		CaptureTweet tweets = new CaptureTweet();
		status = tweets.getTweetsByTopic(topic, 5);
		c.setAutoCommit(false);

		for (Status statuss : status) {
			
			stmt = c.createStatement();
			
			PreparedStatement statement = c.prepareStatement(INSERTSQL);
			statement.setInt(1, 0);
			statement.setInt(2, 0);
			statement.setString(3, statuss.getText());
			statement.setString(4, statuss.getCreatedAt().toString());
			statement.setString(5, "TODO");
			statement.setString(6, "TODO");
			statement.setString(7, statuss.getUser().getProfileImageURL());
			statement.executeUpdate();
				
		}
		
		c.commit();
		stmt.close();
		endConnection();

	}
	
}
