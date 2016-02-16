package matgm50.jrose.core.gl.mesh;

import matgm50.jrose.core.gl.Mesh;
import org.lwjgl.opengl.GL11;

public class Rect extends Mesh {

    private int width, height;

    public Rect(int width, int height) {

        this.width = width;
        this.height = height;

    }

    public void resize(int width, int height) {

        this.width = width;
        this.height = height;
        uploadVertices(getVertices());

    }

    @Override
    protected float[] getVertices() {

        return new float[] {
                0.0f, 0.0f, 0.0f, 0.0f,
                width, 0.0f, 1.0f, 0.0f,
                0.0f, height, 0.0f, 1.0f,
                width, height, 1.0f, 1.0f,
        };

    }

    @Override
    protected int[] getIndices() {

        return new int[] {
                0, 1, 2,
                2, 1, 3
        };

    }

    @Override
    public void draw() {

        bind();
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);

    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

}
