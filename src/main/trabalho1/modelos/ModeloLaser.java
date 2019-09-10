package main.trabalho1.modelos;

import engine.core.Mesh;
import engine.core.Vertex;
import engine.math.Vector2f;
import engine.math.Vector3f;
import engine.math.Vector4f;
import engine.utils.FileLoader;

public class ModeloLaser extends Mesh {
	public ModeloLaser(Vector4f color) {
		addVertices(
				new Vertex[] {
						new Vertex(new Vector3f(-3f,10f,0), color,new Vector2f(0,1)),
						new Vertex(new Vector3f( 3f,10f,0), color,new Vector2f(1,1)),
						new Vertex(new Vector3f( 3f,-10f,0),color,new Vector2f(1,0)),
						new Vertex(new Vector3f(-3f,-10f,0),color,new Vector2f(0,0)),
				},
				new int[] {0,1,2,2,3,0}
		);
		setTexture(FileLoader.readTexture("/textures/laser.png").getTextureID());
	}
}
