package matgm50.jrose.core.gl;


import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Texture extends GLResource {

    private int textureID;

    private ByteBuffer image;
    private int width, height;

    public Texture(ByteBuffer image, int width, int height) {

        this.image = image;
        this.width = width;
        this.height = height;

    }

    public Texture(int id, int width, int height) {

        this.textureID = id;
        this.width = width;
        this.height = height;
        this.image = BufferUtils.createByteBuffer(1);
        initialized = true;

    }

    @Override
    public void init() {

        if(initialized) { return; }

        textureID = GL11.glGenTextures();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0,
                GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);

        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

        initialized = true;

    }

    @Override
    public void bind() {

        if(!initialized) { return; }
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

    }

    @Override
    public void unbind() {

        if(!initialized) { return; }
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

    }

    @Override
    public void deinit() {

        unbind();

        //STBImage.stbi_image_free(image);

        GL11.glDeleteTextures(textureID);

        initialized = false;

    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

}
