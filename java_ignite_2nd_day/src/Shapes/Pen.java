package Shapes;

public class Pen {
    public void drawRectangle(Rectangle r) {
        System.out.println(r.getHeight() * r.getWidth());
    }

    public void drawCircle(Circle c) {
        System.out.println(c.getRadius() * c.getRadius() * Math.PI);
    }

    public void changeColorRectangle(String color, Rectangle r) {
        r.setColor(color);
        System.out.println(r);
    }

    public void changeColorCircle(String color, Circle c) {
        c.setColor(color);
        System.out.println(c);
    }
}
