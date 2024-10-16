import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    // Function to get the computer's move
    public static char getComputerMove() {
        Random random = new Random();
        char[] moves = {'R', 'P', 'S'};
        return moves[random.nextInt(moves.length)];
    }

    // Function to determine the winner of a round
    public static String determineWinner(char playerMove, char computerMove) {
        if (playerMove == computerMove) {
            return "draw";
        } else if ((playerMove == 'R' && computerMove == 'S') ||
                (playerMove == 'S' && computerMove == 'P') ||
                (playerMove == 'P' && computerMove == 'R')) {
            return "player";
        } else {
            return "computer";
        }
    }


    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        int playerScore = 0;
        int computerScore = 0;

        while (playerScore < 3 && computerScore < 3) {
            System.out.print("Enter your move (R for Rock, P for Paper, S for Scissors): ");
            char playerMove = scanner.next().toUpperCase().charAt(0);

            if (playerMove != 'R' && playerMove != 'P' && playerMove != 'S') {
                System.out.println("Invalid move. Please enter R, P, or S.");
                continue;
            }

            char computerMove = getComputerMove();
            System.out.println("Computer's move was: " + computerMove);

            String winner = determineWinner(playerMove, computerMove);

            if (winner.equals("player")) {
                System.out.println("You win this round.");
                playerScore++;
            } else if (winner.equals("computer")) {
                System.out.println("You lost this round.");
                computerScore++;
            } else {
                System.out.println("It's a draw. Play another round.");
                continue;
            }

            System.out.println("\n*********************");
            System.out.println("Player " + playerScore + " - " + computerScore + " Computer");
            System.out.println("*********************\n");
        }

        if (playerScore == 3) {
            System.out.println("You win the game!");
        } else {
            System.out.println("Computer wins the game!");
        }

        scanner.close();
    }

    // Run the game
    public static void main(String[] args) {
        playGame();
    }
}
