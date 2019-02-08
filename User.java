/* Name: Sophia Trump
   File: User.java
   Description: The User class for Social Media simulation. "Users" can either
                post or view.
   Date: 17 Feb 2019
*/

import java.util.*;
import java.util.concurrent.*;


public class User extends Thread {
  // all the users
  static List<String> userList = Collections.synchronizedList(new ArrayList<String>());

  public String uniqueId() {
    return Long.toString(this.getId());
  }

  public synchronized void post(String id) {
    System.out.println("Posting...");
    if(userList.indexOf(id) == -1) {
      userList.add(id); // add to the front of the deque
    }
    SocialMedia.newsFeed.addFirst("User " + id + " at location " + userList.indexOf(id)
                                 + " says: " + random((int)((Math.random() + 1) * 4)));
  //  System.out.println("User " + id + " at location " + userList.indexOf(id) + "\n");
    System.out.println(SocialMedia.newsFeed.getFirst());
  }

  public synchronized String random(int len) {
        String alpha = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < len) { // length of the random string
            int index = (int) (rnd.nextFloat() * alpha.length()); // get a random letter
            result.append(alpha.charAt(index));
        }
        return result.toString();
      //  return "hi";
    }

  public synchronized void view(String id) {
    System.out.println("User " + id + " is viewing...");
    if(SocialMedia.newsFeed.size() == 0) {
      System.out.println("{Empty newsfeed}\n");
    }
    else {
      int count = 0;
      Iterator i = SocialMedia.newsFeed.iterator();
      System.out.println("{");
      while(i.hasNext() && count < 6) {
        System.out.println(i.next());
        count += 1;
      }
      System.out.println("}\n");
    }
  }
}
