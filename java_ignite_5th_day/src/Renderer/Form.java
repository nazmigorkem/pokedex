package Renderer;

public class Form implements Renderable {
    class FormRenderer implements Renderer {
        @Override
        public void render() {
            System.out.println("Form rendered.");
        }
    }

    @Override
    public Renderer getRenderer() {
        return new FormRenderer();
    }
}
