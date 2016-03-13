package matgm50.jrose.core.kiss;

import matgm50.jrose.core.gl.Texture;

public class Animation {

    int time;
    Texture[] textures;
    double timeFraction;

    Texture activeTexture;

    public Animation(int time, Texture... textures) {

        this.time = time;
        this.textures = textures;

        timeFraction = ((float)textures.length / (float)time);

    }

    public void update(double delta) {



    }

    public void draw() {

        if(activeTexture != null) {

            if(!activeTexture.isInitialized()) in*

        }

    }

}
