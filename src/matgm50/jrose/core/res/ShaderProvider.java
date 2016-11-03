package matgm50.jrose.core.res;

import matgm50.jrose.core.gl.Shader;
import matgm50.jrose.core.util.StringUtils;

public class ShaderProvider extends ResourceProvider<Shader> {

    public ShaderProvider(String root) { super(root); }

    @Override
    public Shader createFromRaw(Raw raw) {

        String src = raw.asString();
        String vshSrc = StringUtils.getStrBetweenTags(src, "Vertex Shader");
        String fshSrc = StringUtils.getStrBetweenTags(src, "Fragment Shader");

        return new Shader(vshSrc, fshSrc);

    }

}
