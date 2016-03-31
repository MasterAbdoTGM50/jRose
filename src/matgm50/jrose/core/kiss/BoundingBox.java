package matgm50.jrose.core.kiss;

import org.joml.Vector2f;

public class BoundingBox {

    Vector2f pos = new Vector2f();
    int width = 1, height = 1;

    public BoundingBox(float posX, float posY, int width, int height) {

        this.pos.x = posX;
        this.pos.y = posY;
        this.width = width;
        this.height = height;

    }

    public void setPos(float x, float y) { this.pos = new Vector2f(x, y); }

    public void setPos(Vector2f pos) { this.pos = pos; }

    public boolean isColliding(BoundingBox box) {

        boolean collidingHorizontal = false;
        boolean collidingVertical = false;

        if(this.pos.x > box.pos.x && this.pos.x < box.pos.x + box.width) { collidingHorizontal = true; }
        if(this.pos.x + this.width > box.pos.x && this.pos.x + this.width < box.pos.x + box.width) {
            collidingHorizontal = true;
        }

        if(this.pos.y > box.pos.y && box.pos.y < box.pos.y + box.height) { collidingVertical = true; }
        if(this.pos.y + this.height > box.pos.y && this.pos.y + this.height  < box.pos.y + box.height) {
            collidingVertical = true;
        }

        return collidingHorizontal && collidingVertical;

    }

}
