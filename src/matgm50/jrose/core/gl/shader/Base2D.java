package matgm50.jrose.core.gl.shader;

import matgm50.jrose.core.gl.Color;
import matgm50.jrose.core.gl.Shader;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Base2D extends Shader {

    private int proMatLoc;
    private int vieMatLoc;
    private int modMatLoc;
    private int colorLoc;

    public Base2D() { super("base2d"); }

    @Override
    public void init() {

        super.init();

        proMatLoc = getUniform("pro_mat");
        vieMatLoc = getUniform("vie_mat");
        modMatLoc = getUniform("mod_mat");
        colorLoc = getUniform("color");

        bind();

        setProjectionMatrix(new Matrix4f());
        setViewMatrix(new Matrix4f());
        setModelMatrix(new Matrix4f());

    }

    public void setColor(float r, float g, float b) { setColor(new Vector3f(r, g, b)); }

    public void setColor(Color color) { setColor(color.getVector()); }

    public void setColor(Vector3f color) { uploadVec3f(colorLoc, color);}

    public void setProjectionMatrix(Matrix4f projectionMatrix) { uploadMat4f(proMatLoc, projectionMatrix); }

    public void setViewMatrix(Matrix4f viewMatrix) { uploadMat4f(vieMatLoc, viewMatrix); }

    public void setModelMatrix(Matrix4f modelMatrix) { uploadMat4f(modMatLoc, modelMatrix); }

}
