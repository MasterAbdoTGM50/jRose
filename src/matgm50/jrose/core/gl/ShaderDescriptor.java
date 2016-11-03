package matgm50.jrose.core.gl;

public abstract class ShaderDescriptor extends GLResource {

    protected Shader shader;

    public ShaderDescriptor(Shader shader) { this.shader = shader; }

}
