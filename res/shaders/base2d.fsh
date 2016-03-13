#version 150

uniform vec4 color;
uniform sampler2D tex;

in vec2 uv;

out vec4 out_color;

void main() {

    out_color = texture(tex, uv) * color;

}