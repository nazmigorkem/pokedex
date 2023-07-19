package Shapes;

public class Circle extends Shape {

    private int radius;

    public Circle(int radius, String color) {
        this.radius = radius;
        super.setColor(color);
    }

    @Override
    public void draw() {
        System.out.printf("Draws %s colored circle with %d cm radius", super.getColor(), radius);
    }

    @Override
    public double getArea() {
        return radius * radius * Math.PI;
    }

    @Override
    public String getDrawableInfo() {
        return String.format("This is a %s colored circle with %d cm radius. It's area is %.2f cm^2.", super.getColor(), radius, this.getArea());
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
