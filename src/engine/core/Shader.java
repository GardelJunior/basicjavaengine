package engine.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import engine.math.Matrix4f;
import engine.math.Vector3f;

public class Shader {
	private int program;
	private HashMap<String,Integer> uniforms;
	
	public Shader() {
		program = glCreateProgram();
		uniforms = new HashMap<String,Integer>();
		if(program==0) {
			System.err.println("Não foi possível alocar o shader no construtor");
			System.exit(1);
		}
	}
	
	public void addUniform(String name) {
		int uni = glGetUniformLocation(program, name);
		if(uni == 0xFFFFFFFF) {
			System.err.println("Não foi possível encontrar o uniform informado: " + name);
			System.exit(1);
		}
		uniforms.put(name, uni);
	}
	
	public void bind() {
		glUseProgram(program);
	}
	
	public void compileShader() {
		glLinkProgram(program);
		if(glGetProgrami(program, GL_LINK_STATUS)!=GL_TRUE) {
			System.err.println("Erro no link do shader");
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
		glValidateProgram(program);
	}
	
	public void addFragmentShader(String text) {
		addShader(text, GL_FRAGMENT_SHADER);
	}
	
	public void addVertexShader(String text) {
		addShader(text, GL_VERTEX_SHADER);
	}
	
	private void addShader(String text, int type) {
		int shader = glCreateShader(type);
		if(shader==0) {
			System.err.println("Não foi possível alocar o shader");
			System.exit(1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) != GL_TRUE) {
			System.err.println("Erro ao compilar o shader");
			System.err.println(glGetShaderInfoLog(shader));
			System.exit(1);
		}
		
		glAttachShader(program, shader);
	}
	
	public void setUniformi(String name, int value) {
		glUniform1i(uniforms.get(name), value);
	}
	
	public void setUniformf(String name, float value) {
		glUniform1f(uniforms.get(name), value);
	}
	
	public void setUniform(String name, Vector3f vector) {
		glUniform3f(uniforms.get(name), vector.getX(), vector.getY(), vector.getZ());
	}
	
	public void setUniformMatrix4f(String name, Matrix4f value) {
		try(MemoryStack ms = MemoryStack.stackPush()){
			FloatBuffer fb = MemoryUtil.memAllocFloat(16);
			for(int i = 0 ; i < 16 ; i++) 
				fb.put(value.getValue(i/4, i%4));
			fb.flip();
			glUniformMatrix4fv(uniforms.get(name), true, fb);
			MemoryUtil.memFree(fb);
		}
	}
}
