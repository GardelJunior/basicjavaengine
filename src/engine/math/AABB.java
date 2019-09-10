package engine.math;

public class AABB {
	private float x,y;
	private float width,height;
	
	public AABB(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isColliding(AABB other) {
		return x >= other.getX() && x + width <= other.x + other.getWidth()
			&& y >= other.getY() && y + height <= other.y + other.getHeight();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	public void add(Vector3f vector) {
		this.x += vector.getX();
		this.y += vector.getY();
	}
	
	public void sub(Vector3f vector) {
		this.x -= vector.getX();
		this.y -= vector.getY();
	}
}
