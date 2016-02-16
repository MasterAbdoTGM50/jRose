package matgm50.jrose.core.kiss;

import matgm50.jrose.core.gl.Color;
import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.gl.Texture;
import matgm50.jrose.core.gl.mesh.Rect;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class SimpleSprite {

    Rect rect;
    Texture tex;
    Matrix4f modMat = new Matrix4f();
    Matrix4f mvpMat = new Matrix4f();

    public int width, height;
    public Color color = Graphics.Colors.WHITE;
    public Vector2f pos = new Vector2f();

    public SimpleSprite(String textureDir, int width, int height) {

        this.width = width;
        this.height = height;

        tex = new Texture(textureDir);
        rect = new Rect(this.width, this.height);

    }

    public void draw() {

        if (!rect.isInitialized()) { rect.init(); }
        if (!tex.isInitialized()) { tex.init(); }

        Graphics.Shaders.base2D.bind();

        modMat.setTranslation(pos.x, pos.y, 0);

        Graphics.proMat.mul(modMat, mvpMat);
        Graphics.Shaders.base2D.setColor(color);
        Graphics.Shaders.base2D.setMVPMatrix(mvpMat);
        tex.bind();
        rect.draw();

    }

    public void kill() {

        rect.deinit();
        tex.deinit();

    }

}
