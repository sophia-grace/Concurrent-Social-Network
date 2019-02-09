/* Name: Sophia Trump
   File: SocialMedia.java
   Description: The social media simulation using concurrency. Gets the
                number of users, starts them, and randomly decides their actions.
                User actions are printed to the console.
   Date: 17 Feb 2019
*/

import java.util.*;
import java.util.concurrent.*;

public class SocialMedia extends User	{

  // the posts from all users
  // using a Deque that is thread safe as described here:
  // https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentLinkedDeque.html
  // and as recommended here:
  // https://dzone.com/articles/why-future-generations-will
  static ConcurrentLinkedDeque<String> newsFeed = new ConcurrentLinkedDeque<String>();

  // make the users active, i.e. run the threads
	public void run() {
    for (int i = 0; i < 10; i++) { // each user will be active 10 times
      try { // delay
        sleep((int)(Math.random() * 1000));
      } catch (InterruptedException e) {}
      // Posting or viewing?
      if ((int) (Math.random() * 100) % 2 == 0) { // Posting
			   post(this.uniqueId());
      }
		  else { // Viewing
			   view(this.uniqueId());
	    }
    }
  } // run()

	public static void main(String[] args) {
    int numOfUsers = Integer.parseInt(args[0]); // get the number of users

    if(numOfUsers <= 2) {
      throw new IllegalArgumentException("There must be at least 3 users in this social network.");
    }
    else {
      for(int i = 0; i < numOfUsers; i++) { // start each user
          new SocialMedia().start();
      }
    }
  } // main()
} // SocialMedia
