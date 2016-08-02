package matgm50.jrose.core;

import matgm50.jrose.core.display.Display;
import matgm50.jrose.core.display.IDisplayHandler;
import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.input.Input;
import org.lwjgl.stb.STBImage;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Game implements IDisplayHandler{

    private boolean running = true;
    private double currFrameTime, lastFrameTime;

    public Display display = new Display();
    public Graphics graphics = new Graphics();
    public Input input = new Input();

    private IScene activeScene;

    public void run() {

        try {

            init();

            while (isRunning()) {

                loop();

                if (display.shouldClose())
                    close();

            }

        } finally {

            deinit();

        }

    }

    private void init() {

        display.setDisplayHandler(this);
        display.setInputHandler(input);
        display.init();
        graphics.init();
        graphics.updateProjection(display.getWidth(), display.getHeight());
        STBImage.stbi_set_flip_vertically_on_load(1);

        if (activeScene != null)
            activeScene.init();

    }

    private void loop() {

        lastFrameTime = currFrameTime;
        currFrameTime = glfwGetTime();

        graphics.clearScreen();

        if (activeScene != null)
            activeScene.update(getDelta());

        input.update();
        display.update();

    }

    public void onScreenResize(int width, int height) {

        graphics.updateProjection(width, height);

        if (activeScene != null)
            activeScene.onScreenResize(width, height);

    }

    private void deinit() {

        if (activeScene != null)
            activeScene.deinit();

        graphics.deinit();
        display.deinit();

    }

    public boolean isRunning() { return running; }

    public double getDelta() { return currFrameTime - lastFrameTime; }

    public void close() { this.running = false; }

    public void setActiveScene(IScene scene) { this.activeScene = scene; }

}
