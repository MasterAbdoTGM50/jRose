package matgm50.jrose.core.res;

import matgm50.jrose.core.gl.Texture;

import java.io.InputStream;
import java.util.HashMap;

public class Resources {

    private static HashMap<String, Texture> textures = new HashMap<>();

    public static Texture loadTexture(String dir) {

        if(textures.containsKey(dir)) {

            return textures.get(dir);

        } else {

            textures.put(dir, new Texture(dir));
            return textures.get(dir);

        }

    }

    public static InputStream getResource(String dir) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        if(loader.getResource("/" + dir) != null) {

            return loader.getResourceAsStream("/" + dir);

        } else {

            return loader.getResourceAsStream(dir);

        }

    }


}
