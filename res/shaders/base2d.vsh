#version 150

uniform mat4 mvp_mat;

in vec2 position;
in vec2 in_uv;

out vec2 uv;

void main() {

    uv = in_uv;
    gl_Position = mvp_mat * vec4(position, 0.0, 1.0);

}