package matgm50.jrose.core.input;

public interface IInputHandler {

    void onKeyPress(int key);

    void onKeyRelease(int key);

    void onMousePress(int mouse);

    void onMouseRelease(int mouse);

    void onMouseMove(double x, double y);

    void onMouseScroll(int scroll);

    void onUnicodeCharPress(char text);

}
