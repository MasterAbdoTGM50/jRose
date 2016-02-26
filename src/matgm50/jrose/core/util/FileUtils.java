package matgm50.jrose.core.util;

import org.lwjgl.BufferUtils;

import java.io.*;
import java.nio.ByteBuffer;

public class FileUtils {

    public static ByteBuffer loadFileAsByteBuffer(String dir) {

        int byt;
        BufferedInputStream stream = new BufferedInputStream(getResource(dir));
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        try {

            while((byt = stream.read()) != -1) {

                bytes.write(byt);

            }

            ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.size());
            buffer.put(bytes.toByteArray());
            buffer.flip();

            stream.close();

            return buffer;

        } catch (IOException e) {

            e.printStackTrace();
        }

        return BufferUtils.createByteBuffer(1);

    }

    public static String loadFileAsString(String dir) {

        String fileAsString = "";

        try {

            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(getResource(dir)));

            while ((line = reader.readLine()) != null) {

                fileAsString += line + "\n";

            }

            reader.close();

        } catch (IOException e) {e.printStackTrace();}

        return fileAsString;

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
