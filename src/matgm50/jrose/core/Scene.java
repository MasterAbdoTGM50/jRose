package matgm50.jrose.core;

public abstract class Scene {

    protected Game game;

    public Scene(Game game) {

        this.game = game;

    }

    public abstract void init();

    public abstract void update(double delta);

    public abstract void deinit();

    public abstract void onScreenResize(int width, int height);

}
