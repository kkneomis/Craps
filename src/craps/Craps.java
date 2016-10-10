/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craps;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author simeonkakpovi
 */
public class Craps {
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> events = new ArrayList<>();
        int pot = 0; //this is how much money they have
        
        int atStake = getBet();
        
        while(atStake > 0){
            int start = firstRoll(); //the player rolls die and get an outcome
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
                    //if they didnt win or lose
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
            atStake = getBet();
        }//end while
        
        events.stream().forEach((event) -> {
            System.out.println(event);
        });
        
    }
    
    
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
            } else{
                System.out.println("You rolled a " + newRoll + " POINT is " + POINT);
            }
        }
        
        return result; //this should never occur
    }
    
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
    
    public static int getRolls(){
        //retuns the sum of two rols
        int die1 = roll();
        int die2 = roll();
        
        int sum = die1 + die2;
        return sum; 
    }
    
    
    public static int roll(){
    	int dienumber;
        Random rnd = new Random();
        
        dienumber =  1 + rnd.nextInt(5);
        return dienumber;
    }
    
    
    public static int getBet(){
        System.out.println("How much would you like to bet?");
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        return amount;
    }
    
}
