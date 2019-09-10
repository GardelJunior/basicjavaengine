package main.trabalho1.entidades;

import engine.math.AABB;
import engine.math.Vector3f;

public class EntidadeViva extends Entidade {

	protected int vida;
	
	public EntidadeViva(int vida, Vector3f posicao, AABB boundBox) {
		super(posicao,boundBox);
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
}
