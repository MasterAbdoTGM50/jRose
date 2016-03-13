package matgm50.jrose.core.gl;

import matgm50.jrose.core.util.FileUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Texture extends GLResource {

    private int textureID;

    private final String dir;
    private int width, height;

    public Texture(String dir) {

        this.dir = dir;

    }

    @Override
    public void init() {

        if(initialized) { return; }

        ByteBuffer image = FileUtils.loadFileAsByteBuffer(dir);
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer c = BufferUtils.createIntBuffer(1);

        ByteBuffer buffer = STBImage.stbi_load_from_memory(image, w, h, c, 4);

        width = w.get();
        height = h.get();

        textureID = GL11.glGenTextures();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0,
                GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

        STBImage.stbi_image_free(buffer);

        initialized = true;

    }

    @Override
    public void bind() { GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); }

    @Override
    public void unbind() { GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0); }

    @Override
    public void deinit() {

        unbind();
        GL11.glDeleteTextures(textureID);

        initialized = false;

    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

}
