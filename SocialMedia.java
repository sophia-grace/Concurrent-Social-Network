/* Name: Sophia Trump
   File: SocialMedia.java
   Description: A social media simulation using concurrency. "Users" can either
                post or view.
   Date: 17 Feb 2019
*/

import java.util.*;


// resource for getting unique id of each thread:
// https://javahungry.blogspot.com/2016/01/how-to-get-thread-id-in-java-with-example.html

// error:
// https://www.journaldev.com/378/java-util-concurrentmodificationexception


/*public class User extends Thread {
  public User(String str) { //
    super(str);
  }
} */


public class SocialMedia extends Thread	{
  static List<String> lst = Collections.synchronizedList(new ArrayList<String>());

  public synchronized long uniqueId() {
    return this.getId();
  }

  public synchronized int position(String search) {
    return lst.indexOf(search);
  }

	public synchronized void post(long id) {
    System.out.println("Posting...");
    lst.add(Long.toString(id));
    System.out.println("list len:" + lst.size());
    System.out.println("User " + id + " at location " + lst.indexOf(Long.toString(id)) + ".\n");
  }

	public synchronized void view(long id) {
    System.out.println("User " + id + " is viewing...");
    System.out.flush();
    if(lst.size() == 0) {
      System.out.println("{Empty newsfeed}\n");
      System.out.flush();
    }
    else {
      int count = 0;
      Iterator i = lst.iterator();
      System.out.println("{");
      while(i.hasNext() && count < 6) {
        System.out.println(i.next());
      }
      System.out.println("}\n");
    }
  }

	public synchronized void run() {
    for (int i = 0; i < 5; i++) {
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
