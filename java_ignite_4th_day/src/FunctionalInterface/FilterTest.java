package FunctionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterTest {
    public static void main(String[] args) {
        ListFilter<String> filterA = (String string) -> string.equals("a");
        ListFilter<String> filterB = (String string) -> string.equals("b");
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        filterList(list, filterA);
        filterList(list, filterB);
    }

    public static <T> void filterList(List<T> list, ListFilter<T> listFilter) {
        for (T object: list) {
            System.out.println(listFilter.satisfiesCondition(object));
        }
    }
}
