package main.trabalho1.entidades;

import engine.math.AABB;
import engine.math.Vector3f;

public class InimigoIxpertinho extends EntidadeViva {

	public InimigoIxpertinho(Vector3f posicao) {
		super(1, posicao, new AABB(posicao.getX() - .05f, posicao.getY() - 0.1f, .05f, .1f));
	}
	
	public void update() {
		
	}
	
}
