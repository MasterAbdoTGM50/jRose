package matgm50.jrose.core;

import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.input.Input;
import matgm50.jrose.core.util.Resource;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.stb.STBImage;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Game {

    private boolean running = true;
    private double currFrameTime, lastFrameTime;

    public Display display = new Display();
    public Graphics graphics = new Graphics();
    public Input input = new Input();

    private Scene activeScene;

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

        display.init();
        graphics.init();
        graphics.updateProjection(display.getWidth(), display.getHeight());
        input.init();
        STBImage.stbi_set_flip_vertically_on_load(1);

        if (activeScene != null)
            activeScene.init();

    }

    private void loop() {

        lastFrameTime = currFrameTime;
        currFrameTime = glfwGetTime();

        input.updateInputStates();
        graphics.clearScreen();

        if (activeScene != null)
            activeScene.update(getDelta());

        display.update();

    }

    private void onScreenResize(int width, int height) {

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

    public void setActiveScene(Scene scene) { this.activeScene = scene; }

    public class Display extends Resource {

        private long windowID;

        private String title = "jRose Core Game";
        private Resolution resolution = new Resolution(800, 600);
        private boolean resizable = false;
        private boolean fullscreen = false;

        private GLFWFramebufferSizeCallback resizeCallback = new GLFWFramebufferSizeCallback() {

            @Override
            public void invoke(long window, int width, int height) { onScreenResize(width, height); }

        };

        @Override
        public void init() {

            glfwInit();

            int resizable = this.resizable ? GLFW_TRUE : GLFW_FALSE;
            long fullscreen = this.fullscreen ? glfwGetPrimaryMonitor() : NULL;

            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
            glfwWindowHint(GLFW_RESIZABLE, resizable);
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

            windowID = glfwCreateWindow(resolution.getWidth(), resolution.getHeight(), title, fullscreen, NULL);

            GLFWVidMode monitorProps = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(windowID, (monitorProps.width() - resolution.getWidth()) / 2,
                    (monitorProps.height() - resolution.getHeight()) / 2);

            glfwSetFramebufferSizeCallback(windowID, resizeCallback);

            glfwSwapInterval(1);

            glfwMakeContextCurrent(windowID);

            GL.createCapabilities();

            glfwShowWindow(windowID);

        }

        public void update() {

            glfwSwapBuffers(windowID);
            glfwPollEvents();

        }

        @Override
        public void deinit() {

            glfwDestroyWindow(windowID);
            glfwTerminate();
            resizeCallback.release();

        }

        public String getTitle() { return title; }

        public void setTitle(String title) {

            this.title = title;

            if (glfwGetCurrentContext() != 0) {

                glfwSetWindowTitle(windowID, getTitle());

            }

        }

        public int getWidth() { return resolution.getWidth(); }

        public void setWidth(int width) { resize(width, resolution.getHeight()); }

        public int getHeight() { return resolution.getHeight(); }

        public void setHeight(int height) { resize(resolution.getWidth(), height); }

        public void resize(int width, int height) {

            resolution.setWidth(width);
            resolution.setHeight(height);

            if (windowID != 0) {

                glfwSetWindowSize(windowID, getWidth(), getHeight());

            }

        }

        public void setResizable(boolean resizable) { this.resizable = resizable; }

        public void setFullscreen(boolean fullscreen) { this.fullscreen = fullscreen; }

        public boolean shouldClose() { return glfwWindowShouldClose(windowID) == GLFW_TRUE; }

        public class Resolution {

            private int width;
            private int height;

            public Resolution(int width, int height) {

                this.width = width;
                this.height = height;

            }

            public int getWidth() { return width; }

            public void setWidth(int width) { this.width = width; }

            public int getHeight() { return height; }

            public void setHeight(int height) { this.height = height; }

        }

    }

}
