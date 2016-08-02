package matgm50.jrose.core;

import matgm50.jrose.core.display.Display;
import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.input.Input;

public abstract class Scene implements IScene {

    protected Game game;
    public Display display;
    public Graphics graphics;
    public Input input;

    public Scene(Game game) {

        this.game = game;
        this.display = game.display;
        this.graphics = game.graphics;
        this.input = game.input;

    }

    public abstract void init();

    public abstract void update(double delta);

    public abstract void deinit();

    public abstract void onScreenResize(int width, int height);

}
