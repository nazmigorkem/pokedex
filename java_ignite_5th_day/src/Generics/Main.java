package Generics;

public class Main {
    public static void main(String[] args) {
        Box<Integer> integer = new Box<>();
        integer.setT(3);
        System.out.println(integer.getT());

        Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "banana");
        boolean isSame = Pair.Util.compare(p1, p2);
        System.out.println(isSame);
    }
}
