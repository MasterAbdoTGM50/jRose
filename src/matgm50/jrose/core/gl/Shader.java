package matgm50.jrose.core.gl;

import matgm50.jrose.core.res.Raw;
import matgm50.jrose.core.res.Resources;
import matgm50.jrose.core.util.Lib;
import matgm50.jrose.core.util.StringUtils;
import org.joml.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader extends GLResource{

    private final String name;

    private int shaderID;
    private int vertShaderID;
    private int fragShaderID;

    Raw raw;

    protected Shader(String name) { this.name = name; }

    @Override
    public void init() {

        if(initialized) { return; }

        String src = (raw = new Raw(Resources.getResource(Lib.SHADERS_LOC + name + ".vfsh"))).asString();
        String vshSrc = StringUtils.getStrBetweenTags(src, "<Vertex Shader>");
        String fshSrc = StringUtils.getStrBetweenTags(src, "<Fragment Shader>");

        shaderID = GL20.glCreateProgram();
        vertShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        fragShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);

        GL20.glShaderSource(vertShaderID, vshSrc);
        GL20.glCompileShader(vertShaderID);

        GL20.glShaderSource(fragShaderID, fshSrc);
        GL20.glCompileShader(fragShaderID);

        GL20.glAttachShader(shaderID, vertShaderID);
        GL20.glAttachShader(shaderID, fragShaderID);

        GL20.glLinkProgram(shaderID);

        initialized = true;

    }

    public void bind() { GL20.glUseProgram(shaderID); }

    public void unbind() { GL20.glUseProgram(0); }

    public void deinit() {

        raw.deinit();

        GL20.glDeleteProgram(shaderID);
        GL20.glDeleteShader(vertShaderID);
        GL20.glDeleteShader(fragShaderID);

        initialized = false;

    }

    public int getUniform(String name) {

        return GL20.glGetUniformLocation(shaderID, name);

    }

    public int getAttribute(String name) {

        return GL20.glGetAttribLocation(shaderID, name);

    }

    public void uploadBoolean(int location, boolean bool) {

        uploadInt(location, (bool) ? GL11.GL_TRUE : GL11.GL_FALSE);

    }

    public void uploadInt(int location, int i) {

        GL20.glUniform1i(location, i);

    }

    public void uploadVec2f(int location, Vector2f vector) {

        GL20.glUniform2fv(location, vector.get(BufferUtils.createFloatBuffer(2)));

    }

    public void uploadVec3f(int location, Vector3f vector) {

        GL20.glUniform3fv(location, vector.get(BufferUtils.createFloatBuffer(3)));

    }

    public void uploadVec4f(int location, Vector4f vector) {

        GL20.glUniform4fv(location, vector.get(BufferUtils.createFloatBuffer(4)));

    }

    public void uploadMat3f(int location, Matrix3f matrix) {

        GL20.glUniformMatrix3fv(location, false, matrix.get(BufferUtils.createFloatBuffer(9)));

    }

    public void uploadMat4f(int location, Matrix4f matrix) {

        GL20.glUniformMatrix4fv(location, false, matrix.get(BufferUtils.createFloatBuffer(16)));

    }

}
