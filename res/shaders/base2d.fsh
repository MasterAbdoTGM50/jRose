#version 150

uniform vec3 color;
uniform sampler2D tex;

in vec2 uv;

out vec4 out_color;

void main() {

    out_color = texture(tex, uv) * vec4(color, 1.0);

}