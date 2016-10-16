
package craps;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Simeon Kakpovi
 * Java course at Howard university
 * Midterm project
 * Professor: Alton Henley
 */
public class Craps {
   
    /**
     * 
     * @param args
     * User starts with $1000
     * User can bet up to max pot into to win more
     * Game is terminated if the user rolls 0
     * or if they have $0 left
     */
    public static void main(String[] args) {
        
        ArrayList<String> events = new ArrayList<>();
        System.out.println("We'll start you with $1000");
        int pot = 1000; //this is how much money they have
        
        int atStake = getBet(pot);

        
        while((atStake * pot > 0)){//neither can be 0
            int start = firstRoll(); //the player rolls die and gets an outcome
            switch (start) {
                case 1: 
                    System.out.println("$"+ atStake + " added to your pot");
                    events.add("You won $"+ atStake  );
                    pot += atStake;
                    break;
                case 0:
                    System.out.println("$"+ atStake + " removed from your pot");
                    events.add("You lost $"+ atStake  );
                    pot -= atStake;
                    break;
                default:
                    //if they didnt win or lose on first roll
                    //do other stuff
                    int round = playPoint();
                    if (round == 1){
                        System.out.println("$"+ atStake + " added to your pot");
                        events.add("You won $"+ atStake  );
                        pot += atStake;
                    } else {
                        System.out.println("$"+ atStake  +" removed from your pot");
                        events.add("You lost $"+ atStake  );
                        pot -= atStake;
                    }   break;
            }//end switch
            
            System.out.println("Your total pot is $" + pot+'\n');
            atStake = getBet(pot);
        }//end while
        
        events.stream().forEach((event) -> {
            System.out.println(event);
        });
        
    }
    
    
    /*
    First roll is set as point
    If the user consequenty rolls point, they win
    If the user rolls 7, they lose
    */
    public static int playPoint(){
        //do other stuff
        int result = 0;
        int POINT = getRolls();
        System.out.println("You rolled a " + POINT + " This is now POINT"); //this is POINT
            
        int playing = 0;
        while (playing == 0){
            int newRoll = getRolls();
            if (newRoll == 7){
                System.out.println("Sorry! You rolled a 7 so you lose!");
                playing = 1;
                result = 0;
            } else if (newRoll == POINT) {
                System.out.println("Congratulations! You rolled POINT so you  win!");
                playing = 1;
                result = 1;
            } else {
                System.out.println("You rolled a " + newRoll + " POINT is " + POINT);
            }
        }
        
        return result; 
    }
    
    
    /*
    Get the result of the user's first roll
    User wills if they get a 7 or 11
    User loses if they get a 2,3, or 12
    */
    public static int firstRoll(){
        
        int roll = getRolls();
        System.out.println("You rolled a " + roll);
        if ((roll == 7)||(roll == 11)){
            System.out.println("You win");
            return 1;
        } else if ((roll == 2)|| (roll == 3) || (roll == 12)) {
            System.out.println("You lose!");
            return 0;
        } else {
            System.out.println("You get to keep playing");
            return 2;
        }
    }
    
    
    /*
    Get the result of two dice rolls
    */
    public static int getRolls(){
        Random rnd = new Random();
        int die1 = 1 + rnd.nextInt(5);
        int die2 = 1 + rnd.nextInt(5);
        
        int sum = die1 + die2;
        return sum; 
    }
    
    
  
    /*
    Getting the bet from the user
    User cannot bet more than they have
    */
    public static int getBet(int pot){
        System.out.println("How much would you like to bet?");
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        while(amount > pot){
            System.out.println("You cannot bet more than you have");
            System.out.println("How much would you like to bet?");
            amount = sc.nextInt();
        }
        return amount;
    }
    
}
