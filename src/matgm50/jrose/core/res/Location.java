package matgm50.jrose.core.res;

import java.io.InputStream;

public class Location {

    private String root;

    public Location(String root) { this.root = root; }

    public InputStream getResource(String dir) {

        return (getClass().getResource(root + dir) != null) ? getClass().getResourceAsStream(root + dir) : null;

    }

}
