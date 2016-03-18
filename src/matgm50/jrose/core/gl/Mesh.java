package matgm50.jrose.core.gl;

import matgm50.jrose.core.util.Lib;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class Mesh extends GLResource {

    private int vaoID;
    private int vboID;
    private int eboID;

    public Mesh() {}

    @Override
    public void init() {

        if(initialized) { return; }

        vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);

        vboID = GL15.glGenBuffers();
        eboID = GL15.glGenBuffers();

        uploadVertices(getVertices());

        uploadIndices(getIndices());

        initialized = true;

    }

    @Override
    public void bind() {

        GL30.glBindVertexArray(vaoID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);

    }

    @Override
    public void unbind() {

        GL30.glBindVertexArray(0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

    }

    @Override
    public void deinit() {

        unbind();

        GL30.glDeleteVertexArrays(vaoID);
        GL15.glDeleteBuffers(vboID);
        GL15.glDeleteBuffers(eboID);

        initialized = false;

    }

    protected void uploadVertices(float[] vertices) {

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
        buffer.put(vertices).flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

        int posAtr = Graphics.Shaders.base2D.getAttribute("position");
        GL20.glVertexAttribPointer(posAtr, 2, GL11.GL_FLOAT, false, 4 * Lib.SIZE_OF_FLOAT, 0);
        GL20.glEnableVertexAttribArray(posAtr);

        int uvAtr = Graphics.Shaders.base2D.getAttribute("in_uv");
        GL20.glVertexAttribPointer(uvAtr, 2, GL11.GL_FLOAT, false, 4 * Lib.SIZE_OF_FLOAT, 2 * Lib.SIZE_OF_FLOAT);
        GL20.glEnableVertexAttribArray(uvAtr);

    }

    protected void uploadIndices(int[] indices) {

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);
        IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices).flip();
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

    }

    protected abstract float[] getVertices();

    protected abstract int[] getIndices();

    public abstract void draw();

}
