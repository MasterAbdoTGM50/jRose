package matgm50.jrose.core.kiss;

import matgm50.jrose.core.gl.Texture;

public class Animation {

    int time;
    Texture[] textures;
    double timeFraction;

    public double currTime;
    public int currFrame;
    public Texture activeTexture;

    public Animation(int time, Texture... textures) {

        this.time = time;
        this.textures = textures;

        timeFraction = ((float)time / (float)textures.length);

        activeTexture = this.textures[0];

    }

    public void update(double delta) {

        currTime += delta;
        currFrame = (int)(currTime/timeFraction);

        if(currFrame >= textures.length) {currFrame = 0; currTime = 0; }

        activeTexture = textures[currFrame];

        if(!activeTexture.isInitialized()) { activeTexture.init(); }

    }

}
