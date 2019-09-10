package main.trabalho1.entidades;

import engine.core.RenderableObject;
import engine.math.AABB;
import engine.math.Vector3f;

public class Entidade extends RenderableObject{
	protected AABB boundBox;
	
	public Entidade(Vector3f posicao, AABB boundBox) {
		super(posicao);
		this.boundBox = boundBox;
	}

	public AABB getBoundBox() {
		return boundBox;
	}

	public void setBoundBox(AABB boundBox) {
		this.boundBox = boundBox;
	}
	
	public void update() {}
}
