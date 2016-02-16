package matgm50.jrose.core.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    public static String loadFileAsString(String fileDir) {

        String fileAsString = "";

        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileDir));
            String line;

            while ((line = reader.readLine()) != null) {

                fileAsString += line + "\n";

            }

            reader.close();

        } catch (IOException e) {e.printStackTrace();}

        return fileAsString;

    }

}
