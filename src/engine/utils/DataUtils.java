package engine.utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import engine.core.Vertex;

public class DataUtils {
	
	public static FloatBuffer createVertexBuffer(Vertex[] vertices) {
		FloatBuffer fb = org.lwjgl.BufferUtils.createFloatBuffer(vertices.length * 3);
		for(Vertex v : vertices) {
			fb.put(v.getPosition().getX());
			fb.put(v.getPosition().getY());
			fb.put(v.getPosition().getZ());
		}
		fb.flip();
		return fb;
	}
	
	public static FloatBuffer createColorBuffer(Vertex[] vertices) {
		FloatBuffer fb = org.lwjgl.BufferUtils.createFloatBuffer(vertices.length * 4);
		for(Vertex v : vertices) {
			fb.put(v.getColor().getX());
			fb.put(v.getColor().getY());
			fb.put(v.getColor().getZ());
			fb.put(v.getColor().getW());
		}
		fb.flip();
		return fb;
	}
	
	public static FloatBuffer createTextureBuffer(Vertex[] vertices) {
		FloatBuffer fb = org.lwjgl.BufferUtils.createFloatBuffer(vertices.length * 2);
		for(Vertex v : vertices) {
			fb.put(v.getTexCoord().getX());
			fb.put(v.getTexCoord().getY());
		}
		fb.flip();
		return fb;
	}
	
	public static IntBuffer createIndexBuffer(int[] indices) {
		IntBuffer ib = BufferUtils.createIntBuffer(indices.length);
		ib.put(indices).flip();
		return ib;
	}
}
