package test;

import twitter.SendingService;
import twitter4j.TwitterException;

public class TestSending {

	public static void main(String[] args) throws TwitterException {
		
		SendingService sendingService = new SendingService();
		sendingService.sendTweet("jamesrwhite", "Hello James this is Dombot #botCode");
		
	}

}

