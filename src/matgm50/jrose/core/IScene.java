package matgm50.jrose.core;

import matgm50.jrose.core.display.IDisplayHandler;

public interface IScene extends IDisplayHandler {

    void init();

    void update(double delta);

    void deinit();

    void onScreenResize(int width, int height);

}
