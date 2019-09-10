#version 330

in vec4 pass_color;
in vec2 pass_texCoords;

out vec4 out_color;

uniform sampler2D tex;

void main(){
	vec4 tColor = texture2D(tex,pass_texCoords);
	out_color =  vec4(pass_color.r * tColor.r,pass_color.g * tColor.g,pass_color.b * tColor.b,pass_color.a * tColor.a);
}