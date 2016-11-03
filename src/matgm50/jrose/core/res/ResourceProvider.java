package matgm50.jrose.core.res;

import java.io.InputStream;
import java.util.HashMap;

public abstract class ResourceProvider<T extends Resource> {

    private Location root;
    private HashMap<String, T> resources = new HashMap<>();

    public ResourceProvider(String root) {

        this.root = new Location(root);

    }

    public Raw getRaw(String dir) {

        InputStream stream;

        stream = root.getResource(dir);
        if(stream != null) { return new Raw(stream); }

        return null;

    }

    public T load(String dir) {

        T resource = resources.get(dir);
        if(resource == null) {

            Raw raw = getRaw(dir);
            if(raw != null) {

                resources.put(dir, createFromRaw(getRaw(dir)));
                resource = resources.get(dir);
                resource.init();

            }

        }

        return resource;

    }

    public abstract T createFromRaw(Raw raw);

    public void unloadAll() { resources.values().forEach(Resource::deinit); }

}
