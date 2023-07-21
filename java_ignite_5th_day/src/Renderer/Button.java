package Renderer;

public class Button implements Renderable {
    public class ButtonRenderer implements Renderer {
        @Override
        public void render() {
            System.out.println("Button rendered.");
        }
    }

    @Override
    public Renderer getRenderer() {
        return new ButtonRenderer();
    }
}
