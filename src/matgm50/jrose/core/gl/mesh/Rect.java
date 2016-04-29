package matgm50.jrose.core.gl.mesh;

import matgm50.jrose.core.gl.Mesh;
import org.lwjgl.opengl.GL11;

public class Rect extends Mesh {

    private int width, height;
    private float uvX = 0.0f, uvY = 0.0f, uvWidth = 1.0f, uvHeight = 1.0f;

    public Rect(int width, int height) {

        this.width = width;
        this.height = height;

    }

    public Rect setRegion(float uvX, float uvY, float uvWidth, float uvHeight) {

        if(initialized) { return this; }

        this.uvX = uvX;
        this.uvY = uvY;
        this.uvWidth = uvWidth;
        this.uvHeight = uvHeight;

        return this;

    }

    @Override
    protected float[] getVertices() {

        return new float[] {
                0.0f, 0.0f,
                width, 0.0f,
                0.0f, height,
                width, height
        };

    }

    @Override
    protected float[] getUVs() {

        return new float[] {
                uvX, uvY,
                uvX + uvWidth, uvY,
                uvX, uvY + uvHeight,
                uvX + uvWidth, uvY + uvHeight
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
