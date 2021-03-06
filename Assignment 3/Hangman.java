/* Name: Alex Hale
 * ID: 260672475
 * Date: 18/3/2016 */

// import the Scanner utility, which will be used to ask the user for new guesses
import java.util.Scanner;

public class Hangman {
  // create fields which will be used to store the game's data
  private Letter[] letters;      // stores the letters in the word
  private char[] guesses;        // stores the incorrect guessed letters
  private boolean gameOver;      // true while the game is on, false when the game is over
  private int maxNumGuesses;     // stores the maximum allowed wrong guesses in the game
  private int numGuessesMade;    // stores the number of wrong guesses that have been made
  
  // Main method included for testing purposes. The code in this method code could be written in any class.
  public static void main(String[] args) {
    Hangman game = new Hangman("Ravenclaw");
    game.playGame();
  }
  
  // the first available constructor, which takes a String as input and automatically chooses a maxNumGuesses
  public Hangman(String input) {
    input = input.toUpperCase();                     // change the input string to all uppercase letters
    this.letters = new Letter[input.length()];       // initialize the length of the Letters array
    for (int i = 0; i < input.length(); i++) {       // loop through each letter of the input word
      Letter letter = new Letter(input.charAt(i));   // create a new letter object for each letter of the input word
      this.letters[i] = letter;                      // add the new letter object to the array of input word letters
    }
    this.guesses = new char[26];    // set the length of the wrong guesses array to 26, more than the maximum possible
    this.gameOver = false;          // declare that the game is not yet over
    this.maxNumGuesses = 8;         // set the number of allowed wrong guesses to 8 (a default value)
    this.numGuessesMade = 0;        // start the number of wrong guesses made at zero
  }
  
  // the second available constructor, which takes a String and a maxNumGuesses as input
  public Hangman(String input, int guesses) {
    input = input.toUpperCase();                     // change the input string to all uppercase letters
    this.letters = new Letter[input.length()];       // initialize the length of the Letters array
    for (int i = 0; i < input.length(); i++) {       // loop through each letter of the input word
      Letter letter = new Letter(input.charAt(i));   // create a new letter object for each letter of the input word
      this.letters[i] = letter;                      // add the new letter object to the array of input word letters
    }
    this.guesses = new char[26];    // set the length of the wrong guesses array to 26, more than the maximum possible
    this.gameOver = false;          // declare that the game is not yet over
    this.maxNumGuesses = guesses;   // set the number of allowed wrong guesses to the specified value
    this.numGuessesMade = 0;        // start the number of wrong guesses made at zero
  }
  
  public void playGame() {
    Scanner scan = new Scanner(System.in);  // create a Scanner object
    
    // continue allowing new guesses while the word is incomplete and there are guesses remaining
    while (!this.gameOver && this.numGuessesMade < this.maxNumGuesses) {
      displayBoard();                                  // print out game info to the user
      System.out.println("What is your next guess?");  // prompt the user for a new guess

      boolean validLength = false;    // declare test variables and set them to false for the first loop
      boolean validContent = false;
      
      // ask for new guesses until the guess has the right length and is a letter
      while (!validLength || !validContent) {
        validLength = false;   // reset the variables to false so this new guess can be tested
        validContent = false;
        String initialInput = scan.nextLine();    // get the new guess from the user
        
        // print out a message if the guess wasn't of length 1
        if (initialInput.length() != 1) {
          System.out.println("Your guess wasn't the proper length. Try again!");
        } else {
          validLength = true;   // set the length to valid for the next loop
          char input = initialInput.charAt(0);   // change the entry String to a char
          
          // print out a message if the guess wasn't a letter
          if (!guess(input)) {
            System.out.println("Invalid guess. Try again!");
          } else {
            validContent = true;   // set the content to valid for the next loop
          }
        }
      } 
      completeWord(); // check if the word is complete, therefore exiting the loop and ending the game
    }
    
    // display a message depending on who won, and display the full mystery word in either case
    String word = "";
    for (int i = 0; i < this.letters.length; i++) {
      word += this.letters[i].getValue();   // add each letter from the letters array to an output word String
    }
    if (this.numGuessesMade < this.maxNumGuesses) {
      System.out.println("Congratulations! You guessed the correct mystery word: " + word + ". You had "
                         + (this.maxNumGuesses - this.numGuessesMade) + " guesses remaining.");
    } else {
      System.out.println("Out of tries! The mystery word was " + word + ".");
    }
  }
  
  // this method checks whether a guess is valid, and if so, checks if it is in the mystery word
  private boolean guess(char input) {
    // check if the character is a symbol. If so, return false
    if (input < 65 || (input > 90 && input < 97) || input > 122) {
      return false;
    }
    
    // We now know that the guess is a letter. Convert it to uppercase
    input = Character.toUpperCase(input);
    
    // check if the guess is in the array of wrong guesses. If so, return false.
    for (int i = 0; i < this.numGuessesMade; i++) {
      if (guesses[i] == input) {
        return false;
      }
    }
    
    // check if the guess has already been revealed in the word. If so, return false
    for (int i = 0; i < this.letters.length; i++) {
      if (this.letters[i].getRevealed()) {
        char letter = this.letters[i].getValue();
        if (input == letter) {
          return false;
        }
      }
    }
    
    // If we've reached here, the input is valid. Check if it's in the word, then return true.
    boolean found = false;
    for (int i = 0; i < this.letters.length; i++) {
      if (this.letters[i].getValue() == input) {
        this.letters[i].reveal(); // if the guess is found in the mystery word, set it to revealed in the letter array
        found = true;
      }
    }
    if (!found) {
      this.guesses[this.numGuessesMade] = input;    // if the guess wasn't in the word, add it to the guesses array
      numGuessesMade++;
    }
    return true;
  }
  
  // this method prints out the mystery word in its current state of guessedness, along with other game information
  private void displayBoard() {
    // display the incorrect guesses that have already been made
    String output = "";
    for (int i = 0; i < this.numGuessesMade+1; i++) {
      output += guesses[i] + " ";
    }
    System.out.println("You have made the following guesses: " + output);
    
    // display the mystery word, with blanks replacing letters that haven't been guessed
    output = "";
    for (int i = 0; i < this.letters.length; i++) {
      if (this.letters[i].getRevealed()) {
        output += this.letters[i].getValue();
      } else {
        output += "_";
      }
      output += " ";
    }
    System.out.println("Word so far: " + output);
    
    // display the number of guesses remaining
    System.out.println("You have " + (maxNumGuesses - numGuessesMade) + " guesses remaining.");
  }
  
  // this method checks whether or not each of the letters in the word have been guessed
  private void completeWord() {
    this.gameOver = true;   // set gameOver to true before the loop 
    for (int i = 0; i < this.letters.length; i++) {
      if (!this.letters[i].getRevealed()) {
        gameOver = false;    // if one of the letters still hasn't been guessed, set gameOver to false
      }
    }
  }
}