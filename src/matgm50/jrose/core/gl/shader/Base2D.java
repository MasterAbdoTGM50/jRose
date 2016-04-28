package matgm50.jrose.core.gl.shader;

import matgm50.jrose.core.gl.Color;
import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.gl.Shader;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Base2D extends Shader {

    private int texEnLoc;
    private int mvpMatLoc;
    private int colorLoc;

    public Base2D() { super("base2d"); }

    @Override
    public void init() {

        super.init();

        texEnLoc = getUniform("tex_enabled");
        mvpMatLoc = getUniform("mvp_mat");
        colorLoc = getUniform("color");

        bind();

        setMVPMatrix(new Matrix4f());

    }

    public void setColor(float r, float g, float b, float a) { setColor(new Vector4f(r, g, b, a)); }

    public void setColor(Color color) { setColor(color.getVector()); }

    public void setColor(Vector4f color) { uploadVec4f(colorLoc, color);}

    public void enableTextures() { uploadBoolean(texEnLoc, true); }

    public void disableTextures() { uploadBoolean(texEnLoc, false);}

    public void setMVPMatrix(Matrix4f proMat, Matrix4f vieMat, Matrix4f modMat) {

        Matrix4f mvpMat = new Matrix4f();
        mvpMat.mul(proMat);
        mvpMat.mul(vieMat);
        mvpMat.mul(modMat);

        setMVPMatrix(mvpMat);

    }

    public void setMVPMatrix(Matrix4f projectionMatrix) { uploadMat4f(mvpMatLoc, projectionMatrix); }

}
