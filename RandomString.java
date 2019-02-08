/* Name: Sophia Trump
   File: RandomString.java
   Description: Generates a random string.
   Date: 17 Feb 2019
*/
import java.util.*;

public class RandomString {
  public String random(int len) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer result = new StringBuffer();
        Random rnd = new Random();
        while (result.length() < len) { // length of the random string
            int index = (int) (rnd.nextFloat() * alpha.length()); // get a random letter
            result.append(alpha.charAt(index));
        }
        return result.toString();
    }
}
