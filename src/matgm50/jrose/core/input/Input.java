package matgm50.jrose.core.input;

import org.joml.Vector2i;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    public static final String LEFT_MOUSE = "LMB";
    public static final String MIDDLE_MOUSE = "MMB";
    public static final String RIGHT_MOUSE = "RMB";

    private Map<String, InputState> inputStates = new HashMap<>();

    public void init() {

        registerInputState(new Mouse(LEFT_MOUSE, GLFW_MOUSE_BUTTON_LEFT));
        registerInputState(new Mouse(MIDDLE_MOUSE, GLFW_MOUSE_BUTTON_MIDDLE));
        registerInputState(new Mouse(RIGHT_MOUSE, GLFW_MOUSE_BUTTON_RIGHT));

    }

    public void updateInputStates() {

        int action = 0;

        for(InputState inputState : inputStates.values()) {

            switch (inputState.getType()) {

                case MOUSE:
                    action = glfwGetMouseButton(glfwGetCurrentContext(), inputState.getCode());
                    break;
                case KEY:
                    action = glfwGetKey(glfwGetCurrentContext(), inputState.getCode());
                    break;

            }

            getInputState(inputState.getName()).setState(getStateFromAction(inputState, action));

        }

    }

    public InputState.State getStateFromAction(InputState state, int action) {

        switch (action) {

            case GLFW_PRESS:
                if (state.isIgnored() || state.isReleased()) { return InputState.State.PRESSED; }
                if (state.isPressed() || state.isHeld()) { return InputState.State.HELD; }
                break;
            case GLFW_RELEASE:
                if (state.isPressed() || state.isHeld()) { return InputState.State.RELEASED; }
                if (state.isReleased()) { return InputState.State.IGNORED; }
                break;

        }

        return InputState.State.IGNORED;

    }

    public Vector2i getMousePos() {

        DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);

        glfwGetCursorPos(glfwGetCurrentContext(), x, y);
        glfwGetFramebufferSize(glfwGetCurrentContext(), null, h);

        return new Vector2i((int)x.get(), (h.get() - (int)y.get()));

    }

    public Mouse getMouse(String name) { return (Mouse)getInputState(name); }

    public Key getKey(String name) { return (Key)getInputState(name); }

    public InputState getInputState(String name) { return inputStates.get(name); }

    public void registerInputState(InputState inputState) { inputStates.put(inputState.getName(), inputState); }

}
