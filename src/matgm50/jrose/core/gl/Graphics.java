package matgm50.jrose.core.gl;

import matgm50.jrose.core.res.Resource;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class Graphics extends Resource {

    public static Matrix4f proMat = new Matrix4f();
    public static Matrix4f vieMat = new Matrix4f();

    public Color clearColor = Colors.BLACK;

    @Override
    public void init() {

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

    }

    public void clearScreen() {

        GL11.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

    }

    public void updateProjection(int width, int height) {

        GL11.glViewport(0, 0, width, height);
        proMat.setOrtho2D(0, width, 0, height);

    }

    @Override
    public void deinit() {}

    public static class Colors {

        public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        public static final Color RED = new Color(1.0f, 0.0f, 0.0f, 1.0f);
        public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f, 1.0f);
        public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f, 1.0f);

    }

}