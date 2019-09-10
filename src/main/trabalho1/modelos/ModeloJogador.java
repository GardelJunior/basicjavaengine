package main.trabalho1.modelos;

import engine.core.Mesh;
import engine.core.Vertex;
import engine.math.Vector2f;
import engine.math.Vector3f;
import engine.math.Vector4f;
import engine.utils.FileLoader;

public class ModeloJogador extends Mesh {
	public ModeloJogador() {
		final float size = 60;
		addVertices(
				new Vertex[] {
						new Vertex(new Vector3f(-size, size,0), new Vector4f(1,1,1,1), new Vector2f(0,1)),
						new Vertex(new Vector3f( size, size,0), new Vector4f(1,1,1,1), new Vector2f(1,1)),
						new Vertex(new Vector3f( size,-size,0), new Vector4f(1,1,1,1), new Vector2f(1,0)),
						new Vertex(new Vector3f(-size,-size,0), new Vector4f(1,1,1,1), new Vector2f(0,0)),
				},
				new int[] {	0,1,2,
							2,3,0 }
		);
		setTexture(FileLoader.readTexture("/textures/ship.png").getTextureID());
	}
}
