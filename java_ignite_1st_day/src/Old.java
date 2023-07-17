import java.util.Scanner;

public class Old {
    public static void main(String[] args) {
        System.out.println("Enter your age:");
        Scanner scanner = new Scanner(System.in);
        int userAge = scanner.nextInt();
        if (userAge > 50)
            System.out.println("You are old.");
        else
            System.out.println("You are young.");
    }
}
