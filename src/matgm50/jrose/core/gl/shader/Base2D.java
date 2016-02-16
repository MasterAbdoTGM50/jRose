package matgm50.jrose.core.gl.shader;

import matgm50.jrose.core.gl.Color;
import matgm50.jrose.core.gl.Shader;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Base2D extends Shader {

    private int mvpMatLoc;
    private int colorLoc;

    public Base2D() { super("base2d"); }

    @Override
    public void init() {

        super.init();

        mvpMatLoc = getUniform("mvp_mat");
        colorLoc = getUniform("color");

    }

    public void setColor(float r, float g, float b) { setColor(new Vector3f(r, g, b)); }

    public void setColor(Color color) { setColor(color.getVector()); }

    public void setColor(Vector3f color) { uploadVec3f(colorLoc, color);}

    public void setMVPMatrix(Matrix4f mvpMatrix) { uploadMat4f(mvpMatLoc, mvpMatrix); }

}
