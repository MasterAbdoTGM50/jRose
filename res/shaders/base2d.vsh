#version 150

uniform mat4 pro_mat;
uniform mat4 vie_mat;
uniform mat4 mod_mat;

in vec2 position;
in vec2 in_uv;

out vec2 uv;

void main() {

    uv = in_uv;
    gl_Position = pro_mat * vie_mat * mod_mat * vec4(position, 0.0, 1.0);

}