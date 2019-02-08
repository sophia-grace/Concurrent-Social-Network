/* Name: Sophia Trump
   File: SocialMedia.java
   Description: The social media simulation using concurrency.
   Date: 17 Feb 2019
*/

import java.util.*;
import java.util.concurrent.*;

// resource for getting unique id of each thread:
// https://javahungry.blogspot.com/2016/01/how-to-get-thread-id-in-java-with-example.html

public class SocialMedia extends User	{

  // the posts from all users
  // using a Deque that is thread safe as described here:
  // https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentLinkedDeque.html
  // and as recommended here:
  // https://dzone.com/articles/why-future-generations-will
  static ConcurrentLinkedDeque<String> newsFeed = new ConcurrentLinkedDeque<String>();

	public void run() {
    for (int i = 0; i < 100; i++) {
      try { // delay
        sleep((int)(Math.random() * 1000));
      } catch (InterruptedException e) {}

      // Posting
      if ((int) (Math.random() * 100) % 2 == 0) {
			   post(this.uniqueId());
      }
      // Viewing
		  else {
			   view(this.uniqueId());
	    }
    }
  }

	public static void main(String[] args) {
    int numOfUsers = Integer.parseInt(args[0]);

    if(numOfUsers <= 2) {
      throw new IllegalArgumentException("There must be at least 3 users in this social network.");
    }
    else {
      for(int i = 0; i < numOfUsers; i++) {
          new SocialMedia().start();
      }
    }
  } // main()
} // SocialMedia
