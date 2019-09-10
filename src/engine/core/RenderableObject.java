package engine.core;

import engine.math.Vector3f;

public class RenderableObject {
	
	protected Vector3f position;
	private boolean isDestroyed;
	
	public RenderableObject(Vector3f position) {
		super();
		this.position = position;
	}

	public void update() {}

	public Vector3f getPosition() {
		return position;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
		Renderer.getInstance().removeEntity(this);
	}
}
