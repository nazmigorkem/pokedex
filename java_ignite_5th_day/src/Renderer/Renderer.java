package Renderer;

import java.awt.*;

public interface Renderer {
    public void render();
    class ButtonRenderer implements Renderer {

        @Override
        public void render() {
            System.out.println("Button rendered.");
        }
    }
}
