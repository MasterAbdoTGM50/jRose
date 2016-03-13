package matgm50.jrose.core.gl;

import org.joml.Vector4f;

public class Color {

    public float r;
    public float g;
    public float b;
    public float a;

    public Color() { this(1.0f, 1.0f, 1.0f, 1.0f); }

    public Color(int r, int g, int b, int a) { this(r/255.0f, g/255.0f, b/255.0f, a/255.0f); }

    public Color(float r, float g, float b, float a) {

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;

    }

    public Color setAlpha(int a) { return setAlpha(a/255.0f); }

    public Color setAlpha(float a) {

        this.a = a;
        return this;

    }

    public Vector4f getVector() {

        return new Vector4f(r, g, b, a);

    }

}
