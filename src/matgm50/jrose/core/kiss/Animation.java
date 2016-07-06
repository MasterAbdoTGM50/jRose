package matgm50.jrose.core.kiss;

import matgm50.jrose.core.gl.Texture;

public class Animation {

    int time;
    Texture[] textures;
    double frameTime;
    double currTime;

    boolean paused = false;

    public int currFrame = 0;

    public Animation(int time, Texture... textures) {

        this.time = time;
        this.textures = textures;

        frameTime = ((float)time / (float)textures.length);

    }

    public void update(double delta) {

        if(!isPaused()) {

            currTime += delta;
            currFrame = (int)(currTime / frameTime);

            if(currFrame >= textures.length) { currFrame = 0; currTime = 0; }

            if(!getActiveTexture().isInitialized()) { getActiveTexture().init(); }

        }

    }

    public Texture getActiveTexture() { return textures[currFrame]; }

    public boolean isPaused() { return paused; }

    public void play() { paused = false; }

    public void pause() { paused = true; }

}
