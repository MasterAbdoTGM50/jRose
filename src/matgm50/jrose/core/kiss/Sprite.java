package matgm50.jrose.core.kiss;

import matgm50.jrose.core.gl.Color;
import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.gl.Texture;
import matgm50.jrose.core.gl.mesh.Rect;
import matgm50.jrose.core.res.Resource;
import org.joml.Matrix4f;

public class Sprite extends Resource {

    private Texture texture;
    private Rect rect;
    private Matrix4f modMat = new Matrix4f();

    public Color color = Graphics.Colors.WHITE;

    public Sprite(Texture texture) {

        this.texture = texture;
        if(!texture.isInitialized()) { texture.init(); }

        this.rect = new Rect(texture.getWidth(), texture.getHeight());
        rect.init();

    }

    public void draw(float x, float y) {

        draw(x, y, rect.getWidth(), rect.getHeight());

    }

    public void draw(float x, float y, int width, int height) {

        Graphics.Shaders.base2D.bind();

        modMat.identity();
        modMat.translate(x, y, 0);
        float xScaleFactor = ((float)width / (float)rect.getWidth());
        float yScaleFactor = ((float)height / (float)rect.getHeight());
        modMat.scale(xScaleFactor, yScaleFactor, 1);

        Graphics.Shaders.base2D.setColor(color);
        Graphics.Shaders.base2D.setMVPMatrix(Graphics.proMat, new Matrix4f(), modMat);

        texture.bind();
        rect.draw();

    }

    @Override
    public void init() {}

    @Override
    public void deinit() { rect.deinit(); }

}
