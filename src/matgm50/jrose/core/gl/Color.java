package matgm50.jrose.core.gl;

import org.joml.Vector3f;

public class Color {

    public float r = 1.0f;
    public float g = 1.0f;
    public float b = 1.0f;

    public Color() { this(1.0f, 1.0f, 1.0f); }

    public Color(float r, float g, float b) {

        this.r = r;
        this.g = g;
        this.b = b;

    }

    public Vector3f getVector() {

        return new Vector3f(r, g, b);

    }

}
