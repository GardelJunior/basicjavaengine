#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 color;

out vec3 pass_color;

void main(){
	pass_color = color;
	gl_Position = vec4(position,1.0);
}