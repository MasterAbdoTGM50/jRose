package matgm50.jrose.core.res;

import java.io.InputStream;

public class Location {

    private String root;

    public Location(String dir) { this.root = dir; }

    public InputStream getResource(String dir) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        return (loader.getResource(root + dir) != null) ? loader.getResourceAsStream(root + dir) : null;

    }

}
