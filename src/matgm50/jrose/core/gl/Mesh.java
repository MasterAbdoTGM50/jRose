package matgm50.jrose.core.gl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class Mesh extends GLResource {

    private int vaoID;
    private int verticesID;
    private int uvsID;
    private int indicesID;

    public Mesh() {}

    @Override
    public void init() {

        if(initialized) { return; }

        vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);

        verticesID = GL15.glGenBuffers();
        uvsID = GL15.glGenBuffers();
        indicesID = GL15.glGenBuffers();

        uploadVertices(getVertices());
        uploadUVs(getUVs());
        uploadIndices(getIndices());

        initialized = true;

    }

    @Override
    public void bind() {

        if(!initialized) { return; }

        GL30.glBindVertexArray(vaoID);

    }

    @Override
    public void unbind() {

        if(!initialized) { return; }

        GL30.glBindVertexArray(0);

    }

    @Override
    public void deinit() {

        unbind();

        GL30.glDeleteVertexArrays(vaoID);
        GL15.glDeleteBuffers(verticesID);
        GL15.glDeleteBuffers(uvsID);
        GL15.glDeleteBuffers(indicesID);

        initialized = false;

    }

    protected void uploadVertices(float[] vertices) {

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, verticesID);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
        buffer.put(vertices).flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(Shader.POSITION_LOC, 2, GL11.GL_FLOAT, false, 0, 0);
        GL20.glEnableVertexAttribArray(Shader.POSITION_LOC);

    }

    protected void uploadUVs(float[] uvs) {

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, uvsID);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(uvs.length);
        buffer.put(uvs).flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(Shader.UV_LOC, 2, GL11.GL_FLOAT, false, 0, 0);
        GL20.glEnableVertexAttribArray(Shader.UV_LOC);

    }

    protected void uploadIndices(int[] indices) {

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesID);
        IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices).flip();
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

    }

    protected abstract float[] getVertices();

    protected abstract float[] getUVs();

    protected abstract int[] getIndices();

    public abstract void draw();

}
