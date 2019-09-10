package engine.core;

import engine.math.Vector2f;
import engine.math.Vector3f;
import engine.math.Vector4f;

public class Vertex {
	public static final int VERTEX_SIZE = 6;
	
	private Vector3f position;
	private Vector4f color;
	private Vector2f texCoord; 
	
	public Vertex() {}
	
	public Vertex(Vector3f position) {
		this.position = position;
	}
	
	public Vertex(Vector3f position, Vector2f texCoord) {
		this.position = position;
		this.texCoord = texCoord;
	}
	
	public Vertex(Vector3f position, Vector4f color) {
		this.position = position;
		this.color = color;
	}
	
	public Vertex(Vector3f position, Vector4f color, Vector2f texCoord) {
		this.position = position;
		this.color = color;
		this.texCoord = texCoord;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector2f getTexCoord() {
		return texCoord;
	}

	public void setTexCoord(Vector2f texCoord) {
		this.texCoord = texCoord;
	}

	public Vector4f getColor() {
		return color;
	}

	public void setColor(Vector4f color) {
		this.color = color;
	}
}
