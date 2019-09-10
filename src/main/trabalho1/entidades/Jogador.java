package main.trabalho1.entidades;

import engine.core.Input;
import engine.core.Renderer;
import engine.core.Timer;
import engine.math.AABB;
import engine.math.Mathf;
import engine.math.Vector3f;
import main.trabalho1.InvasoresEspaciais;

public class Jogador extends EntidadeViva{

	private float delayArma = 0;
	private Vector3f velocity;
	
	public Jogador(Vector3f posicao) {
		super(1, posicao,new AABB(posicao.getX() - 60, posicao.getY() - 60, 120f, 120f));
		velocity = new Vector3f(0,0,0);
	}

	@Override
	public void update() {
		if(Input.getKeyPressed(Input.KEY_LEFT) || Input.getJoystickAxis(0, Input.JOY_AXIS_L_X) <= -0.2f) {
			velocity.setX(Mathf.clamp(velocity.getX() - 1.2f, -18f, 18f));
		} else if(Input.getKeyPressed(Input.KEY_RIGHT) || Input.getJoystickAxis(0,Input.JOY_AXIS_L_X) >= 0.2f) {
			velocity.setX(Mathf.clamp(velocity.getX() + 1.2f, -18f, 18f));
		}
		
		velocity.mul(0.85f, true); // Fricção da Nave, deixa o movimento suave.
		
		position.add(velocity, true);
		boundBox.add(velocity);
		
		if(Input.getKeyPressed(Input.KEY_SPACE) || Input.getJoystickPressed(0, Input.JOY_CROSS)) {
			if(delayArma <= 0) {
				delayArma += 20 * Timer.getDelta();
				Vector3f laserVelocity = new Vector3f(velocity.getX()*0.05f,-10f,0);
				Renderer.getInstance().addEntity(new Laser(getPosition().add(new Vector3f(0, -25f, 0), false),laserVelocity));
			}
		}
		
		if(delayArma > 0) delayArma -= Timer.getDelta();
	}
}
