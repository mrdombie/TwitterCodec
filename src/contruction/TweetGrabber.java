package contruction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;


public class TweetGrabber {
    public List<Status> getTweetsByTopic(String topic, int numberOfTweets) throws TwitterException, InterruptedException {
       
        // TODO: You may have to tweak the capacity of the queue, depends on the filter query
    	final TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        final BlockingQueue<Status> statuses = new LinkedBlockingQueue<Status>(10000); 
        final StatusListener listener = new StatusListener() {

            public void onStatus(Status status) {
                statuses.offer(status); // Add received status to the queue
            }

			@Override
			public void onException(Exception ex) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDeletionNotice(
					StatusDeletionNotice statusDeletionNotice) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
				
			}

        };

        final FilterQuery fq = new FilterQuery();
        final String keywords[] = {topic};
        
        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);
        
        final List<Status> collected = new ArrayList<Status>(numberOfTweets);
           
        while (collected.size() < numberOfTweets) {
            // TODO: Handle InterruptedException
            final Status status = statuses.poll(10, TimeUnit.SECONDS); 
           
            if (status == null) {
                continue;
            }
           
         	System.out.println("CAPTURE TWEET: " + status.getText());
            collected.add(status);
        }
        
        twitterStream.shutdown();
        return collected;
    }
    
} 