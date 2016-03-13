package matgm50.jrose.core.display;

import matgm50.jrose.core.res.Resource;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display extends Resource {

    private long windowID;
    private IDisplayHandler displayHandler = null;

    private String title = "jRose Core Game";
    private Resolution resolution = new Resolution(800, 600);
    private boolean resizable = false;
    private boolean fullscreen = false;

    private GLFWFramebufferSizeCallback resizeCallback = new GLFWFramebufferSizeCallback() {

        @Override
        public void invoke(long window, int width, int height) {

            if(displayHandler != null) { displayHandler.onScreenResize(width, height); }

        }

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

    public void setDisplayHandler(IDisplayHandler displayHandler) { this.displayHandler = displayHandler; }

    public int getWidth() { return resolution.getWidth(); }

    public int getHeight() { return resolution.getHeight(); }

    public void switchResolution(Resolution newRes) {

        this.resolution = newRes;
        glfwSetWindowSize(windowID, resolution.getWidth(), resolution.getHeight());

    }

    public void setResizable(boolean resizable) { this.resizable = resizable; }

    public void setFullscreen(boolean fullscreen) { this.fullscreen = fullscreen; }

    public boolean shouldClose() { return glfwWindowShouldClose(windowID) == GLFW_TRUE; }

}
