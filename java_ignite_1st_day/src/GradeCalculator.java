import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        System.out.println("Enter grades, give input 101 to exit:");
        Scanner scanner = new Scanner(System.in);
        double input;
        double sum = 0;
        double inputCounter = 0;
        while (true) {
            try {
                input = scanner.nextDouble();
                if (input == 101f) {
                    break;
                }
                if (input < 0 || input > 100) {
                    System.out.println("Invalid grade.");
                    continue;
                }
                sum += input;
                inputCounter++;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Invalid argument type.");
            }
        }
        System.out.println(sum / inputCounter);
    }
}
