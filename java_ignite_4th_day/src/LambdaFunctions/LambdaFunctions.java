package LambdaFunctions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaFunctions {
    public static void main(String[] args) {
        Function<Integer, Integer> square = x -> x * x;
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        Calculator addition = (a, b) -> a + b;
        Calculator subtraction = (a, b) -> a - b;

        System.out.println(addition.calculate(3, 4));
    }
}
