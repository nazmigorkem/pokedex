package Shapes;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(2, "Red");
        Pen pen = new Pen();
        pen.draw(circle);
        pen.printShape(circle);
    }
}
