package Renderer;

public class RenderUtility {
    public void doRender(Renderable renderable) {
        renderable.getRenderer().render();
    }
}
