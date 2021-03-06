/* Name: Alex Hale
 * ID: 260672475
 * Date: February 15, 2016 */

// import utilities that will be necessary for the program
import java.util.Scanner;

// declare the class
public class Conversion {
  // declare the main method
  public static void main(String[] args) {
    // create a scanner Object, to be used in case the user's direct steps input isn't valid
    Scanner sc = new Scanner(System.in);
    
    // a String in which the user input will be stored
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
    while (!validInput(input)) {
      System.out.println("The input isn't binary! Please try again.");
      input = sc.nextLine();
    }
    // we now know that the input is valid
    
    // create an integer to store the base 10 result
    int base10 = 0;
    
    // loop through the valid input string
    for (int i = 0; i < input.length(); i++) {
      // check if the character being checked is the digit 1
      if (input.charAt(i) == 49) {
        // create code to multiply by the power of 2 (instead of using Math.pow())
        
        // create an int variable to store the result
        int temp = 1;
        
        // multiply temp by 2 for each space between it and the right end of the input
        for (int z = input.length() - (i+1); z > 0; z--) {
          temp *= 2;
        }
        // add the power of 2 to the overall base10 sum
        base10 += temp;
      }
    }
    // print out the input in base 10
    System.out.println("The binary number " + input + " is " + base10 + " in base 10.");
  }
  
  // a method to check whether an input String contains only binary characters
  public static boolean validInput(String input) {
    // if the input is empty, return false
    if (input.equals("")) {
      return false;
    }
    
    // loop through each digit in the input
    for (int i = 0; i < input.length(); i++) {
      // if one of the characters is anything other than a 0 or 1, return false
      if (input.charAt(i) != 48 && input.charAt(i) != 49) {
        return false;
      }
    }
    // otherwise, return true
    return true;
  }
}