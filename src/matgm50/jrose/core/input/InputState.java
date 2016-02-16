package matgm50.jrose.core.input;

public class InputState {

    protected State state = State.IGNORED;

    private String name;
    private Type type;
    private int code;

    public InputState(String name, Type type, int code) {

        this.name = name;
        this.type = type;
        this.code = code;

    }

    public boolean isIgnored() { return state == State.IGNORED; }

    public boolean isPressed() { return state == State.PRESSED; }

    public boolean isHeld() { return state == State.HELD; }

    public boolean isReleased() { return state == State.RELEASED; }

    public State getState() { return state; }

    public void setState(State state) { this.state = state; }

    public String getName() { return name; }

    public Type getType() { return type; }

    public int getCode() { return code; }

    public enum Type {

        MOUSE,
        KEY

    }

    public enum State {

        IGNORED,
        PRESSED,
        HELD,
        RELEASED

    }

}
