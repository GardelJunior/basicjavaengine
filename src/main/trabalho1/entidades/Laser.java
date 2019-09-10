package main.trabalho1.entidades;

import engine.math.AABB;
import engine.math.Vector3f;

public class Laser extends EntidadeViva {

	protected Vector3f velocity;
	
	public Laser(Vector3f posicao, Vector3f velocity) {
		super(1, posicao, new AABB(posicao.getX() - 3, posicao.getY() - 10, 6, 20));
		this.velocity = velocity;
	}
	
	public void update() {
		position.add(velocity, true);
		if(position.getY()<-10) setDestroyed(true);
	}
	
}
