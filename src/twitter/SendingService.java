package twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SendingService {
	
	public void updateStatus() throws TwitterException{
		
		Twitter twitter = TwitterFactory.getSingleton();
	    Status status = twitter.updateStatus("Gief me Mana");
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");		
	}
	
	public void sendTweet(String accountName, String bodyMessage) throws TwitterException{
		
		Twitter twitter = TwitterFactory.getSingleton();
	    Status status = twitter.updateStatus("@" + accountName + " " + bodyMessage);
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");		
		
	}

}
