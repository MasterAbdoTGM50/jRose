package matgm50.jrose.core.res;

import org.lwjgl.BufferUtils;

import java.io.*;
import java.nio.ByteBuffer;

public class Raw extends Resource {

    private final byte[] bytes;

    public Raw(InputStream stream) { this(streamToBytes(stream)); }

    public Raw(byte[] bytes) { this.bytes = bytes; }

    @Override
    public void init() { }

    @Override
    public void deinit() { }

    public byte[] asByteArray() { return this.bytes; }

    public ByteBuffer asByteBuffer() {

        ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length);
        buffer.put(bytes);
        buffer.flip();

        return buffer;

    }

    public String asString() {

        String asString = "";

        try {

            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));

            while ((line = reader.readLine()) != null) {

                asString += line + "\n";

            }

            reader.close();

        } catch (IOException e) { e.printStackTrace(); }

        return asString;

    }

    private static byte[] streamToBytes(InputStream stream) {

        int byt;
        BufferedInputStream reader = new BufferedInputStream(stream);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        try {

            while((byt = reader.read()) != -1) {

                bytes.write(byt);

            }

            reader.close();

            return bytes.toByteArray();

        } catch (IOException e) { e.printStackTrace(); }

        return new byte[0];

    }

}
