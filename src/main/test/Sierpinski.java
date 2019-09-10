package main.test;

import java.util.Random;
import java.util.stream.IntStream;

import engine.core.Mesh;
import engine.core.Shader;
import engine.core.Vertex;
import engine.core.Window;
import engine.math.Vector3f;
import engine.utils.FileLoader;

public class Sierpinski {
	
	public static void main(String[] args) {
		Window.createWindow(800, 600, "Sierpinski");
		
		int num_pts = 15000;
		Vertex pts[] = new Vertex[num_pts];
		
		Vector3f vertices[] = new Vector3f[] {
				new Vector3f(-1.0f, -1.0f,0), 
				new Vector3f(0.0f, 1.0f,0), 
				new Vector3f(1.0f, -1.0f,0)
		};
		
		pts[0] = new Vertex(new Vector3f(0.25f, 0.50f,0));
		
		Random rand = new Random();
		
		for (int i = 1; i < num_pts; ++i){
			int j = rand.nextInt(3);
			pts[i] = new Vertex(pts[i-1].getPosition().add(vertices[j], false).mul(0.5f, true));
		}
		
		Mesh malha = new Mesh();
		malha.addVertices(pts, IntStream.range(0, num_pts).toArray());
		
		Shader sierpinskiShader = new Shader();
		sierpinskiShader.addVertexShader(FileLoader.readFileAsString("/shaders/sierpinski.vertex.glsl"));
		sierpinskiShader.addFragmentShader(FileLoader.readFileAsString("/shaders/sierpinski.fragment.glsl"));
		sierpinskiShader.compileShader();
		
		while(!Window.shouldClose()) {
			Window.updateEvents();
			Window.clearColor();
			sierpinskiShader.bind();
			malha.renderPoints();
			Window.swapBuffers();
		}
		
		Window.terminate();
	}
}
