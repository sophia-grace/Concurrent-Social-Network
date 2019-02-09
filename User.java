/* Name: Sophia Trump
   File: User.java
   Description: The User class for the Social Media simulation. Implements
                posting and viewing functionalities for the users. Generates
                a random String for each post.
   Date: 17 Feb 2019
*/

import java.util.*;
import java.util.concurrent.*;

public class User extends Thread {
  // all the users
  static List<String> userList = Collections.synchronizedList(new ArrayList<String>());

  // resource for getting unique id of each thread:
  // https://javahungry.blogspot.com/2016/01/how-to-get-thread-id-in-java-with-example.html
  public String uniqueId() {
    return Long.toString(this.getId()); // put in String form to add to userList<String>
  } // uniqueId()

  public void post(String id) {
    System.out.println("Posting...");
    if(userList.indexOf(id) == -1) { // check if user is already recorded
      userList.add(id); // add to the front of the deque
    }
    SocialMedia.newsFeed.addFirst("User " + id + " at location " + userList.indexOf(id)
                                 + " says: " + random((int)((Math.random() + 1) * 4))); // add the post to the news feed
    System.out.println(SocialMedia.newsFeed.getFirst() + "\n"); // output to the console
  } // post()

  public synchronized String random(int len) { // generates a random String
        String alpha = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

        // using StringBuffer as it is thread safe as indicated here:
        // https://www.journaldev.com/538/string-vs-stringbuffer-vs-stringbuilder
        StringBuffer result = new StringBuffer();
        Random rnd = new Random();
        while (result.length() < len) { // length of the random string
            int index = (int) (rnd.nextFloat() * alpha.length()); // get a random letter
            result.append(alpha.charAt(index));
        }
        return result.toString();
    } // random()

  public void view(String id) {
    System.out.println("User " + id + " is viewing...");
    if(SocialMedia.newsFeed.size() == 0) { // if there is nothing in the news feed
      System.out.println("{Empty newsfeed}\n");
    }
    else {
      int count = 0;
      Iterator i = SocialMedia.newsFeed.iterator();
      System.out.println("{");
      while(i.hasNext() && count < 6) { // print the 6 most recent posts (or the < 6 most recent)
        System.out.println(i.next());
        count += 1;
      }
      System.out.println("}\n");
    }
  } // view()
} // User
