package matgm50.jrose.core.display;

import matgm50.jrose.core.input.IInputHandler;
import matgm50.jrose.core.res.Resource;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display extends Resource {

    private long windowID = 0;
    private IDisplayHandler displayHandler = null;
    private IInputHandler inputHandler = null;

    private String title = "jRose Core Game";
    private Resolution resolution = new Resolution(800, 600);
    private boolean resizable = false;
    private boolean fullscreen = false;

    private GLFWFramebufferSizeCallback resizeCallback = new ResizeCallback();

    private GLFWKeyCallback keyCallback = new KeyCallback();
    private GLFWCharCallback charCallback = new CharCallback();
    private GLFWMouseButtonCallback mouseCallback = new MouseCallback();
    private GLFWCursorPosCallback positionCallback = new PositionCallback();
    private GLFWScrollCallback scrollCallback = new ScrollCallback();

    @Override
    public void init() {

        glfwInit();

        int resizable = this.resizable ? GLFW_TRUE : GLFW_FALSE;
        long fullscreen = this.fullscreen ? glfwGetPrimaryMonitor() : NULL;

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, resizable);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        windowID = glfwCreateWindow(resolution.getWidth(), resolution.getHeight(), title, fullscreen, NULL);

        GLFWVidMode monitorProps = glfwGetVideoMode(glfwGetPrimaryMonitor());
        int xPos = (monitorProps.width() - resolution.getWidth()) / 2;
        int yPos =  (monitorProps.height() - resolution.getHeight()) / 2;

        glfwSetWindowPos(windowID, xPos, yPos);

        glfwSetFramebufferSizeCallback(windowID, resizeCallback);

        glfwSetKeyCallback(windowID, keyCallback);
        glfwSetCharCallback(windowID, charCallback);
        glfwSetMouseButtonCallback(windowID, mouseCallback);
        glfwSetCursorPosCallback(windowID, positionCallback);
        glfwSetScrollCallback(windowID, scrollCallback);

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
        resizeCallback.free();
        keyCallback.free();
        charCallback.free();
        mouseCallback.free();
        positionCallback.free();
        scrollCallback.free();

    }

    public String getTitle() { return title; }

    public void setTitle(String title) {

        this.title = title;

        if (windowID != 0) {

            glfwSetWindowTitle(windowID, getTitle());

        }

    }

    public void setDisplayHandler(IDisplayHandler displayHandler) { this.displayHandler = displayHandler; }

    public void setInputHandler(IInputHandler inputHandler) { this.inputHandler = inputHandler; }

    public int getWidth() { return resolution.getWidth(); }

    public int getHeight() { return resolution.getHeight(); }

    public void switchResolution(Resolution newRes) {

        this.resolution = newRes;
        glfwSetWindowSize(windowID, resolution.getWidth(), resolution.getHeight());

    }

    public void setResizable(boolean resizable) { this.resizable = resizable; }

    public void setFullscreen(boolean fullscreen) { this.fullscreen = fullscreen; }

    public boolean shouldClose() { return glfwWindowShouldClose(windowID); }

    private class ResizeCallback extends GLFWFramebufferSizeCallback {

        @Override
        public void invoke(long window, int width, int height) {

            if(displayHandler != null) { displayHandler.onScreenResize(width, height); }

        }

    }

    private class KeyCallback extends GLFWKeyCallback {

        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {

            if(inputHandler != null) {

                switch(action) {

                    case GLFW_PRESS: inputHandler.onKeyPress(key); break;
                    case GLFW_RELEASE: inputHandler.onKeyRelease(key); break;

                }

            }

        }

    }

    private class CharCallback extends GLFWCharCallback {

        @Override
        public void invoke(long window, int codepoint) {

            if(inputHandler != null) { inputHandler.onUnicodeCharPress((char)codepoint); }

        }

    }

    private class MouseCallback extends GLFWMouseButtonCallback {

        @Override
        public void invoke(long window, int button, int action, int mods) {

            if(inputHandler != null) {

                switch(action) {

                    case GLFW_PRESS: inputHandler.onMousePress(button); break;
                    case GLFW_RELEASE: inputHandler.onMouseRelease(button); break;

                }

            }

        }

    }

    private class PositionCallback extends GLFWCursorPosCallback {

        @Override
        public void invoke(long window, double xpos, double ypos) {

            if(inputHandler != null) { inputHandler.onMouseMove(xpos, resolution.getHeight() - ypos); }

        }

    }

    private class ScrollCallback extends GLFWScrollCallback {

        @Override
        public void invoke(long window, double xoffset, double yoffset) {

            if(inputHandler != null) { inputHandler.onMouseScroll((int)yoffset); }

        }

    }



}
