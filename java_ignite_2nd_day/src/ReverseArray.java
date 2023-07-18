import java.util.Arrays;
import java.util.Random;

public class ReverseArray {
    public static void main(String[] args) {
        int[] generatedArray = ReverseArray.generateArray(new Random().nextInt(50));
        System.out.println(Arrays.toString(generatedArray));
        int[] reversedArray = takeReverse(generatedArray);
        System.out.println(Arrays.toString(reversedArray));
    }

    private static int[] generateArray(int size) {
        int[] resultArray = new int[size];
        for (int i = 0; i < size; i++) {
            resultArray[i] = new Random().nextInt(100);
        }
        return resultArray;
    }

    private static int[] takeReverse(int[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - 1 - i];
        }
        return newArray;
    }
}
