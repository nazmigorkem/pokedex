package Shapes;

public class Pen {
    public void draw(Drawable drawable) {
        System.out.println(drawable.getDrawableInfo());
    }

    public void printShape(Shape shape) {
        shape.draw();
    }
}
