package matgm50.jrose.core.kiss;

import matgm50.jrose.core.gl.Color;
import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.gl.Texture;
import matgm50.jrose.core.gl.mesh.Rect;
import matgm50.jrose.core.res.Resources;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Entity {

    Vector2f pos = new Vector2f();
    Sprite sprite;

    boolean active = true;
    boolean visible = true;

    public int width, height;
    public BoundingBox bb;
    public Color color = Graphics.Colors.WHITE;

    public Entity(String textureDir, int width, int height) {

        this.width = width;
        this.height = height;

        bb = new BoundingBox(pos.x, pos.y, width, height);

        sprite = new Sprite(Resources.loadTexture(textureDir));

    }

    public void draw() {

        if(isVisible()) {

            sprite.color = color;
            sprite.draw(pos.x, pos.y, width, height);

        }

    }

    public void move(float x, float y) { setPos(this.pos.x + x, this.pos.y + y); }

    public void setPos(float x, float y) { setPos(new Vector2f(x, y)); }

    public void setPos(Vector2f pos) {

        this.pos = pos;
        this.bb.setPos(pos);

    }

    public float getX() { return this.pos.x; }
    public float getY() { return this.pos.y; }
    
    public boolean isColliding(Entity entity) { return this.bb.isColliding(entity.bb); }

    public void kill() { sprite.deinit(); }

    public boolean isActive() { return active; }

    public void activate() { this.active = true; }
    public void deactivate() { this.active = false; }

    public boolean isVisible() { return visible; }

    public void show() { this.visible = true; }
    public void hide() { this.visible = false; }

}
