/* Name: Sophia Trump
   File: SocialMedia.java
   Description: A social media simulation using concurrency. "Users" can either
                post or view.
   Date: 17 Feb 2019
*/

import java.util.*;
import java.util.concurrent.*;


// resource for getting unique id of each thread:
// https://javahungry.blogspot.com/2016/01/how-to-get-thread-id-in-java-with-example.html

// error: need to change from synchronizedList!
// https://www.journaldev.com/378/java-util-concurrentmodificationexception


public class SocialMedia extends Thread	{
  // all the users
  static List<String> userList = Collections.synchronizedList(new ArrayList<String>());

  // the posts from all users
  // using a Deque that is thread safe as described here:
  // https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentLinkedDeque.html
  // and as recommended here:
  // https://dzone.com/articles/why-future-generations-will
  static ConcurrentLinkedDeque<String> newsFeed = new ConcurrentLinkedDeque<String>();

  public synchronized String uniqueId() {
    return Long.toString(this.getId());
  }

	public synchronized void post(String id) {
    System.out.println("Posting...");
    if(userList.indexOf(id) == -1) {
      userList.add(id); // add to the front of the deque
    }
    newsFeed.addFirst("User " + id + " at location " + userList.indexOf(id));
    System.out.println("User " + id + " at location " + userList.indexOf(id) + "\n");
  }

	public synchronized void view(String id) {
    System.out.println("User " + id + " is viewing...");
    if(newsFeed.size() == 0) {
      System.out.println("{Empty newsfeed}\n");
    }
    else {
      int count = 0;
      Iterator i = newsFeed.iterator();
      System.out.println("{");
      while(i.hasNext() && count < 6) {
        System.out.println(i.next());
        count += 1;
      }
      System.out.println("}\n");
    }
  }

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
