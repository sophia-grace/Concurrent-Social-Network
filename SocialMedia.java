/* Name: Sophia Trump
   File: SocialMedia.java
   Description: A social media simulation using concurrency. "Users" can either
                post or view.
   Date: 17 Feb 2019
*/

import java.util.*;


// resource for getting unique id of each thread:
// https://javahungry.blogspot.com/2016/01/how-to-get-thread-id-in-java-with-example.html


/*public class User extends Thread {
  public User(String str) { //
    super(str);
  }
} */


public class SocialMedia extends Thread	{
  List<String> lst = Collections.synchronizedList(new ArrayList<String>());
	public synchronized void post(String s, long id) {
    System.out.println(id + " says: " + s + ".");
  }

	public synchronized String view(long id) {
    System.out.println(id + " is viewing...");
  }
	public void run() {
    for (int i = 0; i < 100; i++) {
      if ((int) Math.random() % 2 == 0) {
         System.out.println("Posting...");
			   post(this.post, this.uniqueId());
      }
		  else {
         System.out.println("Viewing...")
			   view(this.uniqueId());
	    }
    }
  }
	public static void main(String[] args) {
    int numOfUsers = Integer.parseInt(args[0]);

    if(numOfUsers < 2) {
      throw new IllegalArgumentException("There must be at least 2 users in this social network.");
    }
    else {
      for(int i = 0; i < numOfUsers; i++) {
          new SocialMedia().start();
      }
    }
  } // main()
} // SocialMedia
