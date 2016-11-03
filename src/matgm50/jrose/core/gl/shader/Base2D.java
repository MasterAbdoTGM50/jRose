package matgm50.jrose.core.gl.shader;

import matgm50.jrose.core.gl.Color;
import matgm50.jrose.core.gl.ShaderDescriptor;
import matgm50.jrose.core.gl.Shader;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class Base2D extends ShaderDescriptor {

    private int texEnLoc;
    private int mvpMatLoc;
    private int colorLoc;

    public Base2D(Shader shader) {

        super(shader);

        texEnLoc = shader.getUniform("tex_enabled");
        mvpMatLoc = shader.getUniform("mvp_mat");
        colorLoc = shader.getUniform("color");

        shader.bind();

        setMVPMatrix(new Matrix4f());

    }

    @Override
    public void init() {}

    @Override
    public void deinit() {}

    @Override
    public void bind() { shader.bind(); }

    @Override
    public void unbind() { shader.unbind(); }

    public void setColor(float r, float g, float b, float a) { setColor(new Vector4f(r, g, b, a)); }

    public void setColor(Color color) { setColor(color.getVector()); }

    public void setColor(Vector4f color) { shader.uploadVec4f(colorLoc, color); }

    public void enableTextures() { shader.uploadBoolean(texEnLoc, true); }

    public void disableTextures() { shader.uploadBoolean(texEnLoc, false); }

    public void setMVPMatrix(Matrix4f proMat, Matrix4f vieMat, Matrix4f modMat) {

        Matrix4f mvpMat = new Matrix4f();
        mvpMat.mul(proMat);
        mvpMat.mul(vieMat);
        mvpMat.mul(modMat);

        setMVPMatrix(mvpMat);

    }

    public void setMVPMatrix(Matrix4f projectionMatrix) { shader.uploadMat4f(mvpMatLoc, projectionMatrix); }

}
