/* Name: Alex Hale
 * ID: 260672475
 * Date: January 24, 2016
 * Title: Assignment 1, Question 1 - ParseDigits
 */

// Declare the class, which will house all the methods of the program.
public class ParseDigits {
  // Declare the main method, where the program will start.
  public static void main(String[] args){
    /* I will allow the program to handle inputs of different lengths.
     * I will check that each character entered into the program is a number. */
    
    /* First, call the allNumbers() method to check if the input string contains anyting other than positive numbers.
     * I used a new method because I could make the code cleaner by using a return statement to end the loop. */
    if (allNumbers(args[0]) == false) {
      /* If allNumbers() reveals that there is anything other than numbers in the input, display a message to the user
       * and don't run any of the other code. */
      System.out.println("\"" + args[0] + "\" is not a positive integer.");
    } else {
      // Declare all of the variables we'll need for the math of calculating the sum and product.
      int number = Integer.parseInt(args[0]);  // convert the input string to an int and save it for later use
      int modulo = 1;                          // a number we'll use to take each digit out of the integer
      int dig;                                 // a place to save the digit we're currently working with
      int sum = 0;                             // the sum of the digits
      int product = 1;                         // the product of the digits
      
      /* Contain the mathematical process in a for loop so that it will run on each digit, one at a time, until
       * it runs out of digits. */
      for (int i = 0; i < args[0].length(); i++) {
        // increase the modulo counter by a factor of 10, in order to move on to the next digit to the left
        modulo *= 10;
        
        /* To find the next digit to the left:
         *  1) Use the modulo (%) operator and the modulo counter to get the digit and all the digits after it.
            2) Divide the result by 1/10 of the modulo counter to move the digit to one spot left of the decimal.
            3) Assign the result to an int variable to chop off the decimal places, leaving the digit. */
        dig = (number % modulo)/(modulo/10);
        
        // add the digit to the sum, and multiply the product by the digit
        sum += dig;
        product *= dig;
      }
      
      // Print out the results of the calculated sum and product of the digits.
      System.out.println("The sum of the digits is " + sum + ".");
      System.out.println("The product of the digits is " + product + ".");
    
      // The third line of the results message depends the values of the sum and product.
      if (sum > product) {
        // If the sum is larger, print an appropriate message that includes the difference.
        System.out.println("The sum is " + (sum-product) + " greater than the product.");  
      } else if (sum < product) {
        // If the product is larger, print an appropriate message that includes the difference.
        System.out.println("The product is " + (product-sum) + " greater than the sum.");
      } else {
        // If they are equal, print an appropriate message.
        System.out.println("The sum of " + sum + " is equal to the product of " + product + ".");
      }
    }
  }
  
  // This method checks whether a string is only made up of numbers.
  public static boolean allNumbers(String input) {
    // Iterate through the entire length of the String.
    for (int i = 0; i < input.length(); i++) {
      /* Use the isDigit() method in the Character library to test character in the string for digit-ness. */
      if (!Character.isDigit(input.charAt(i))) {
        // Return false if any one of the characters is found not to be digit-ness-less.
        return false;
      }
    }
    // Return true if all of the characters were found to be digits-ful.
    return true;
  }
}