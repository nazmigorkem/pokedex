package Shapes;

public abstract class Shape implements Drawable {
    private String color;
    public abstract double getArea();
    public abstract void draw();

    @Override
    public String getDrawableInfo() {
        return "This is just a shape. No further information.";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
