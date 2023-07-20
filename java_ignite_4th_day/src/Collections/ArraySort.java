package Collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArraySort {
    public static void main(String[] args) {
        String[] strings = {"3", "1", "4"};
        List<String> stringList = Arrays.asList(strings);

        System.out.println(Arrays.toString(strings));
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));
        Collections.reverse(stringList);
        System.out.println(stringList);

    }
}
