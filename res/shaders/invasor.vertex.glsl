#version 330

layout (location = 0) in vec3 v_position;
layout (location = 1) in vec4 v_color;
layout (location = 2) in vec2 v_texCoords;

uniform mat4 projection;
uniform mat4 transform;

out vec4 pass_color;
out vec2 pass_texCoords;

void main(){
	pass_color = v_color;
	pass_texCoords = v_texCoords;
	gl_Position = projection * transform * vec4(v_position,1.0);
}