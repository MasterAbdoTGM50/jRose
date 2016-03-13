package matgm50.jrose.core.res;

public abstract class Resource {

    protected boolean initialized = false;

    public abstract void init();

    public abstract void deinit();

    public boolean isInitialized() { return initialized; }

}
