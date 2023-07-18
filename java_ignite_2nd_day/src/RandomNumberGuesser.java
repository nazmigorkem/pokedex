import java.util.InputMismatchException;
import java.util.Scanner;

public class RandomNumberGuesser {
    public static void main(String[] args) {
        int randomNumber = (int)(Math.random() * 100);
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            try {
                int readValue = scanner.nextInt();
                if (randomNumber == readValue) {
                    System.out.println("You have found the number.");
                    break;
                } else if (i != 4) {
                    if (randomNumber > readValue) {
                        System.out.println("Your guess was lower.");
                    } else {
                        System.out.println("Your guess was higher.");
                    }
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid argument type.");
                break;
            }
        }

        System.out.printf("The number was %d", randomNumber);
    }
}