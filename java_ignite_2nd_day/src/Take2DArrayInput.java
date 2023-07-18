import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Take2DArrayInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int sizeOfTheArray = scanner.nextInt();
        int[][] cities = takeCityInput(sizeOfTheArray, scanner);
        int totalArea = getTotalArea(cities);

        System.out.println(Arrays.deepToString(cities));
        System.out.println(totalArea);
    }

    private static int getTotalArea(int[][] cities) {
        int totalArea = 0;
        for (int[] city : cities) {
            totalArea += city[1];
        }
        return totalArea;
    }

    private static int[][] takeCityInput(int sizeOfTheArray, Scanner scanner) {
        int[][] cities = new int[sizeOfTheArray][2];
        for (int i = 0; i < cities.length; i++) {
            for (int j = 0; j < 2; j++) {
                switch (j) {
                    case 0 -> System.out.println("Enter the city code: ");
                    case 1 -> System.out.println("Enter the area of the city:");
                }
                try {
                    cities[i][j] = scanner.nextInt();
                } catch (InputMismatchException exception) {
                    System.out.println("Invalid input. Provide integer.");
                    j--;
                }
            }
        }
        return cities;
    }
}
