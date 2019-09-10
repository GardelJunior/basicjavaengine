package main.trabalho1.modelos;

import engine.core.Mesh;
import engine.core.Vertex;
import engine.math.Vector3f;
import engine.math.Vector4f;

public class ModeloInimigo extends Mesh {
	public ModeloInimigo() {
		addVertices(
				new Vertex[] {
						new Vertex(new Vector3f(-0.05f,0.1f,0), new Vector4f(1,0,0,1)),
						new Vertex(new Vector3f( 0.05f,0.1f,0), new Vector4f(1,0,0,1)),
						new Vertex(new Vector3f( 0f, -0.1f,0), new Vector4f(1,0,0,1)),
				},
				new int[] {0,1,2}
		);
	}
}
