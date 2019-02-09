# Concurrent-Social-Network

## Name: Sophia Trump
## Date: 17 Feb 2019

Description:

             Simulates a social network using multithreading. "Users" (i.e., threads)
             can either post or view. User actions are printed to the console. Implemented in Java.

Files:

1. SocialMedia.java

        Description: The social media simulation using concurrency. Gets the
                     number of users, starts them, and randomly decides their actions.
                     Prints user actions to the console.

2. User.java

        Description: The User class for the Social Media simulation. Implements
                     posting and viewing functionalities for the users. Generates
                     a random String for each post.


      NOTE: The SocialMedia class is a subtype of the User class, and the
            User class is a subtype of the Thread class. As follows:

                                   Thread
                                     ^
                                     |
                                    User
                                     ^
                                     |
                                 SocialMedia

How to Run:

Save SocialMedia.java and User.java in the same directory.
From the command line, run

            javac SocialMedia.java User.java

Then,

            java SocialMedia <number of users>

where <number of users> is the number of users you would like
to simulate. Please note that <number of users> must be greater
than 2.
