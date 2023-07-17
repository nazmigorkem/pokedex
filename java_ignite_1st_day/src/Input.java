import java.util.Scanner;

public class Input {
    public static void main(String[] args) {
        System.out.println("Enter a double value:");
        Scanner scanner = new Scanner(System.in);
        double input = scanner.nextDouble();
        System.out.printf("You entered %.2f",  input);
    }
}