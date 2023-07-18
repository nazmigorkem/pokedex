package Shapes;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(3, 4, "Red");
        System.out.println(rectangle);
        rectangle.setColor("Blue");
        System.out.println(rectangle);
    }
}
