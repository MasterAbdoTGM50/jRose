package matgm50.jrose.demo;

import matgm50.jrose.core.Game;
import matgm50.jrose.core.Scene;
import matgm50.jrose.core.display.Resolution;
import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.gl.mesh.Rect;
import matgm50.jrose.core.kiss.Animation;
import matgm50.jrose.core.res.Resources;
import org.joml.Matrix4f;

public class Countdown extends Scene {

    Animation animation = new Animation(9,
            Resources.loadTexture("demo/1.png"),
            Resources.loadTexture("demo/2.png"),
            Resources.loadTexture("demo/3.png"));

    Rect rect = new Rect(64, 64);

    public Countdown(Game game) { super(game); }

    @Override
    public void init() {

        game.display.setTitle("jRose2D: Countdown Demo");
        game.display.switchResolution(new Resolution(600, 600));

        rect.init();

    }

    @Override
    public void update(double delta) {

        animation.update(delta);

        Graphics.Shaders.base2D.bind();

        Graphics.Shaders.base2D.setColor(Graphics.Colors.WHITE);
        Graphics.Shaders.base2D.setMVPMatrix(Graphics.proMat, new Matrix4f(), new Matrix4f());

        animation.activeTexture.bind();
        rect.draw();

    }

    @Override
    public void deinit() {

    }

    @Override
    public void onScreenResize(int width, int height) {

    }
}
