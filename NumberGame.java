import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int totalRounds = 0;
        int totalWins = 0;

        System.out.println("🎮 Welcome to the Number Guessing Game!");
        
        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = rand.nextInt(100) + 1;
            int maxAttempts = 7;
            int attempts = 0;
            boolean guessedCorrectly = false;

            totalRounds++;
            System.out.println("\n🔢 I have selected a number between 1 and 100.");
            System.out.println("💡 You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) {
                System.out.print("👉 Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("🎉 Correct! You guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    totalWins++;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("📉 Too low!");
                } else {
                    System.out.println("📈 Too high!");
                }

                System.out.println("⏳ Attempts left: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've used all attempts. The number was: " + numberToGuess);
            }

            System.out.print("\n🔁 Do you want to play another round? (yes/no): ");
            String response = sc.next().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("\n📊 Game Over!");
        System.out.println("🔢 Total Rounds Played: " + totalRounds);
        System.out.println("🏆 Total Rounds Won: " + totalWins);
        System.out.println("🎯 Accuracy: " + ((totalWins * 100) / totalRounds) + "%");

        sc.close();
    }
}
