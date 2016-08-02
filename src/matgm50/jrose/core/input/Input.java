package matgm50.jrose.core.input;

import static org.lwjgl.glfw.GLFW.*;

public class Input implements IInputHandler {

    public static final class State {

        public static final byte IGNORED = 0;
        public static final byte PRESSED = 1;
        public static final byte HELD = 2;
        public static final byte RELEASED = 3;

    }

    private double mouseX, mouseY;
    private int mouseScroll;

    private byte[] mice = new byte[GLFW_MOUSE_BUTTON_LAST + 1];
    private byte[] keys = new byte[GLFW_KEY_LAST + 1];

    private boolean recording = false;
    private String recoredText;

    public void update() {

        mouseScroll = 0;
        updateStates(mice);
        updateStates(keys);

    }

    private void updateStates(byte[] states) {

        for(int i = 0; i < states.length; i++) {

            if(states[i] == State.PRESSED) { states[i] = State.HELD; }
            if(states[i] == State.RELEASED) { states[i] = State.IGNORED; }

        }

    }

    public boolean isKeyJustPressed(int key) { return keys[key] == State.PRESSED; }

    public boolean isKeyPressed(int key) { return isKeyJustPressed(key) || keys[key] == State.HELD; }

    public boolean isKeyJustReleased(int key) { return keys[key] == State.RELEASED; }

    public boolean isKeyReleased(int key) { return isKeyJustReleased(key) || keys[key] == State.IGNORED; }

    public boolean isMouseJustPressed(int mouse) { return mice[mouse] == State.PRESSED; }

    public boolean isMousePressed(int mouse) { return isMouseJustPressed(mouse) || mice[mouse] == State.HELD; }

    public boolean isMouseJustReleased(int mouse) { return mice[mouse] == State.RELEASED; }

    public boolean isMouseReleased(int mouse) { return isMouseJustReleased(mouse) || mice[mouse] == State.IGNORED; }

    public double getMouseX() { return mouseX; }

    public double getMouseY() { return mouseY; }

    public int getMouseScroll() { return mouseScroll; }

    public boolean isRecording() { return recording; }

    public void startRecording() {

        recording = true;
        recoredText = "";

    }

    public void stopRecording() { recording = false; }

    public String getRecoredText() { return recoredText; }

    @Override
    public void onKeyPress(int key) { if(key != GLFW_KEY_UNKNOWN) { keys[key] = State.PRESSED; } }

    @Override
    public void onKeyRelease(int key) { if(key != GLFW_KEY_UNKNOWN) { keys[key] = State.RELEASED; } }

    @Override
    public void onMousePress(int mouse) { mice[mouse] = State.PRESSED; }

    @Override
    public void onMouseRelease(int mouse) { mice[mouse] = State.RELEASED; }

    @Override
    public void onMouseMove(double x, double y) {

        mouseX = x;
        mouseY = y;

    }

    @Override
    public void onMouseScroll(int scroll) { mouseScroll = scroll; }

    @Override
    public void onUnicodeCharPress(char text) { if(recording) { recoredText += text; } }

}
