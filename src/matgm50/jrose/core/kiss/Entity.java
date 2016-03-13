package matgm50.jrose.core.kiss;

import matgm50.jrose.core.gl.Color;
import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.gl.Texture;
import matgm50.jrose.core.gl.mesh.Rect;
import matgm50.jrose.core.res.Resources;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Entity {

    Rect rect;
    Texture tex;
    Matrix4f modMat = new Matrix4f();

    public int width, height;
    public Color color = Graphics.Colors.WHITE;
    public Vector2f pos = new Vector2f();

    public Entity(String textureDir, int width, int height) {

        this.width = width;
        this.height = height;

        tex = Resources.loadTexture(textureDir);
        rect = new Rect(this.width, this.height);

    }

    public void draw() {

        if (!rect.isInitialized()) { rect.init(); }
        if (!tex.isInitialized()) { tex.init(); }

        Graphics.Shaders.base2D.bind();

        modMat.identity();
        modMat.setTranslation(pos.x, pos.y, 0);

        Graphics.Shaders.base2D.setColor(color);
        Graphics.Shaders.base2D.setMVPMatrix(Graphics.proMat, new Matrix4f(), modMat);

        tex.bind();
        rect.draw();

    }
    
    public boolean isColliding(Entity entity) {

        boolean collidingHorizontal = false;
        boolean collidingVertical = false;

        if(this.pos.x > entity.pos.x && this.pos.x < entity.pos.x + entity.width) { collidingHorizontal = true; }
        if(this.pos.x + this.width > entity.pos.x && this.pos.x + this.width < entity.pos.x + entity.width) {
            collidingHorizontal = true;
        }

        if(this.pos.y > entity.pos.y && this.pos.y < entity.pos.y + entity.height) { collidingVertical = true; }
        if(this.pos.y + this.height > entity.pos.y && this.pos.y + this.height  < entity.pos.y + entity.height) {
            collidingVertical = true;
        }

        return collidingHorizontal && collidingVertical;
        
    }

    public void kill() {}

}
