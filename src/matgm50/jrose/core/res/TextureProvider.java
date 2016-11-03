package matgm50.jrose.core.res;

import matgm50.jrose.core.gl.Texture;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class TextureProvider extends ResourceProvider<Texture> {

    public TextureProvider(String root) { super(root); }

    @Override
    public Texture createFromRaw(Raw raw) {

        ByteBuffer buffer = raw.asByteBuffer();
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer c = BufferUtils.createIntBuffer(1);

        STBImage.stbi_set_flip_vertically_on_load(1);
        ByteBuffer image = STBImage.stbi_load_from_memory(buffer, w, h, c, 4);

        return new Texture(image, w.get(), h.get());

    }

}
