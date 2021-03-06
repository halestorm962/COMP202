/* Name: Alex Hale
 * ID: 260672475
 * Date: February 10, 2016 */

// import some utilities that will be necessary for the program
import java.util.Random;
import java.util.Scanner;

// declare the class
public class DrunkenSailor {
  // declare the main method
  public static void main(String[] args) {
    // create a scanner Object, to be used in case the user's direct steps input isn't valid
    Scanner sc = new Scanner(System.in);
    
    // a String in which the steps input will be stored
    String input;
    
    // check that there is only one value in the entered args array
    if (args.length != 1) {
      // display an error message and a new entry box if the initial entry is invalid
      System.out.println("Your entry is invalid. Please try again.");
      input = sc.nextLine();
    } else {
      // if the input array was of length 1, store it in the input String
      input = args[0];
    }
    
    // check that the input is a positive integer using the validSteps method
    while (!validSteps(input)) {
      System.out.println("You didn't enter a valid number of steps. Please try again.");
      input = sc.nextLine();
    }
    
    // declare initial values
    int steps = Integer.parseInt(input);                // change the number of steps to an integer
    int[] position = {0,0};                             // set an original position
    int[][] maxDistancePositions = new int[steps][2];   // create an array to store points tied for farthest
    int numMaxPositions = 0;                            // count the number of tied farthest points
    double distance = 0;                                // the current distance from the origin
    double maxDistance = 0;                             // the furthest distance from the origin
    
    // Print initial position
    System.out.println("Initial position: (0,0)");
    
    // create a Random object to generate the random direction
    Random rand = new Random();
    
    // iterate through the generation and moving procedure for as many steps as were entered
    for (int i = 0; i < steps; i++) {
      // generate a random number between 0 and 3
      int randomNumber = rand.nextInt(4);
      
      // change the position based on the random number, with each direction having equal probability
      switch (randomNumber) {
        case 0: position[0]++;
        break;
        case 1: position[0]--;
        break;
        case 2: position[1]++;
        break;
        default: position[1]--;
        break;
      }
      
      // print new position
      System.out.println("(" + position[0] + "," + position[1] + ")");
      
      // calculate new distance
      distance = Math.sqrt(Math.pow(position[0],2) + Math.pow(position[1],2));
      
      // check if we've reached a new max distance
      if (distance > maxDistance) {
        // change the max distance reached
        maxDistance = distance;
        
        // reset the number of max positions
        numMaxPositions = 0;
        
        // set the first elements of the max distance positions array to the current position
        /* we don't have to clear the old array because it will be overwritten, and the later elements 
         * that don't get overwritten will never be accessed */
        maxDistancePositions[numMaxPositions][0] = position[0];
        maxDistancePositions[numMaxPositions][1] = position[1];
        
        // add one to the counter of max positions
        numMaxPositions++;
      } else if (distance == maxDistance) {
        // if we've reached a distance equal to the max distance, add the position to the array
        maxDistancePositions[numMaxPositions][0] = position[0];
        maxDistancePositions[numMaxPositions][1] = position[1];
        
        // add one to the counter of max positions
        numMaxPositions++;
      }
    }
    
    // display the distance from the origin to where the sailor finished
    System.out.println("Final distance: " + distance);
    
    // if the sailor took zero steps, print an appropriate message
    if (steps == 0) {
      System.out.println("The sailor didn't take any steps! That was boring...");
      
      /* there was a little bit of code above that could have been skipped in this case. However, the entire for loop
       * is skipped, so its not overly inefficient */
    } else if (numMaxPositions == 1) {
      // if there is only one max position, display that position with an appropriate message
      System.out.println("The furthest point was (" + maxDistancePositions[0][0] + "," + maxDistancePositions[0][1] 
                           + "), at a distance of " + maxDistance + ".");
    } else {
      // if there are multiple max positions, display them all with an appropriate message
        // it isn't possible for there to be zero max positions, so we don't have to account for that
      
      // create a string in which all the max positions will be organized for display
      String positions = "";
      for (int i = 0; i < numMaxPositions; i++) {
        // add each max position to the string
        positions += ("(" + maxDistancePositions[i][0] + "," + maxDistancePositions[i][1] + "), ");
        
        // good grammar is fun! Add the word "and" before the last max position
        if (i == numMaxPositions - 2) {
          positions += "and ";
        }
      }
      
      // display all of the max positions
      System.out.println("There were " + numMaxPositions + " positions that tied for the furthest distance. " +
                         "The sailor reached " + positions + "which were all at a distance of " + distance + ".");
    }
  }
  
  // this method checks that the inputted steps is a non-negative integer (zero is ok!)
  public static boolean validSteps(String entry) {
    // if the input is empty, return false
    if (entry.equals("")) {
      return false;
    }
    
    // create an integer storage variable
    int steps = -1;
    
    // attempt to convert the input String to an integer
    try {
      steps = Integer.parseInt(entry);
    } catch (NumberFormatException n) {
      // if the String can't be converted, return false and exit the method
      return false;
    }
    // check that the integer is non-negative (we can't take negative steps)
    if (steps < 0) {
      return false;
    }
    return true;
  }
}