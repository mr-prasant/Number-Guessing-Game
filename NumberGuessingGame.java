import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    private Scanner sc = new Scanner(System.in);

    private int score = 0;
    private int life = 5;
    private boolean quit = false;

    public void play() {
        System.out.println("Game is running...\nDeveloped by Prasant");
        message("START");
        start(); // staring the game
    }

    private void start() {
        String opt = sc.nextLine(); // wait for user input

        if (opt.equals("")) {
            message(); // print message

            Random ran = new Random();
            int random = ran.nextInt(100); // generate random

            while (life > 0 && !quit) {
                System.out.print("Guess a number: ");
                int guess = sc.nextInt();

                if (random == guess) {
                    System.out.println("\nHurray! Your have guessed the right number.\n");
                    score++;
                    message("REPLAY");
                    System.out.println(updateMsg());
                    if ("".equals(sc.nextLine()))
                        start();
                } else {
                    if (guess > random)
                        System.out.println("Just missed! Your number is greater.\n");
                    else
                        System.out.println("Just missed! Your number is Smaller.\n");
                    life--;
                    System.out.println(updateMsg());
                }

                if (life == 0) {
                    gameover();
                    System.out.println("You lose the game! The number is " + random + ".");
                    System.out.println("Wanna play again?\n");
                    message("START");
                    if ("".equals(sc.nextLine()))
                        start();
                }
            }
        } else if (opt.equals("*")) { // show options
            options();
        } else {
            System.out.println("Please enter a valid key!\n");
            message("START");
            start();
        }
    }

    private void options() {
        System.out.println(". -> Quit\n* -> Restart\n- -> Back");
        String opt = sc.nextLine();

        if (opt.equals("*")) { // RESTART
            System.out.println(updateMsg());
            gameover();
            System.out.println("The Game is Restarted Now!\n");
            message("START");
            start();
        } else if (opt.equals(".")) { // QUIT
            System.out.println(updateMsg());
            gameover();
            quit = true;
            System.out.println("The Game is Ended Now!\n");
        } else if (opt.equals("-")) { // BACK
            System.out.println("You are now back again.");
            start();
        } else {
            System.out.println("Please enter a valid key!");
            options();
        }
    }

    private String updateMsg() {
        String msg = "score: " + score() + " " + life();
        return msg;
    }

    private void message() {
        String msg = "Now you have entered in the GAME.\n" +
                updateMsg();
        System.out.println(msg);
        guessMsg();
    }

    private void message(String status) {
        System.out.println("Press ENTER key to " + status + " the game.");
        System.out.println("Enter * for more options");
    }

    private void guessMsg() {
        System.out.println("Guess a number between 0 to 100!");
    }

    private String score() {
        return (score < 10) ? "0" + score : score + "";
    }

    private String life() {
        String l = "";
        for (int i = 0; i < life; i++)
            l += "* ";

        return l;
    }
    
    private void gameover() {
        life = 5;
        score = 0;
    }
}