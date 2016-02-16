package matgm50.jrose.core.gl;

import matgm50.jrose.core.util.FileUtils;
import matgm50.jrose.core.util.Lib;
import org.joml.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

public class Shader extends GLResource{

    private final String name;

    private int shaderID;
    private int vertShaderID;
    private int fragShaderID;

    protected Shader(String name) { this.name = name; }

    @Override
    public void init() {

        if(initialized) { return; }

        String vshSrc = FileUtils.loadFileAsString(Lib.SHADERS_LOC + name + ".vsh");
        String fshSrc = FileUtils.loadFileAsString(Lib.SHADERS_LOC + name + ".fsh");

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

        GL20.glDeleteProgram(shaderID);
        GL20.glDeleteShader(vertShaderID);
        GL20.glDeleteShader(fragShaderID);

    }

    public int getUniform(String name) {

        return GL20.glGetUniformLocation(shaderID, name);

    }

    public int getAttribute(String name) {

        return GL20.glGetAttribLocation(shaderID, name);

    }

    public void uploadVec2f(int location, Vector2f vector) {

        GL20.glUniform2f(location, vector.x, vector.y);

    }

    public void uploadVec3f(int location, Vector3f vector) {

        GL20.glUniform3f(location, vector.x, vector.y, vector.z);

    }

    public void uploadVec4f(int location, Vector4f vector) {

        GL20.glUniform4f(location, vector.x, vector.y, vector.z, vector.w);

    }

    public void uploadMat3f(int location, Matrix3f matrix) {

        GL20.glUniformMatrix3fv(location, false, matrix.get(BufferUtils.createFloatBuffer(9)));

    }

    public void uploadMat4f(int location, Matrix4f matrix) {

        GL20.glUniformMatrix4fv(location, false, matrix.get(BufferUtils.createFloatBuffer(16)));

    }

}
