package engine.math;

public class Vector2f {
	private float x,y;
	
	public Vector2f() {
		this(0,0);
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float length() {
		return Mathf.sqrt(x * x + y * y);
	}
	
	public Vector2f normalize() {
		float len = length();
		x /= len;
		y /= len;
		return this;
	}
	
	public Vector2f add(Vector2f vec, boolean self) {
		if(self) {
			x += vec.getX();
			y += vec.getY();
			return this;
		}else {
			return new Vector2f(x + vec.getX(), y + vec.y);
		}
	}
	
	public Vector2f sub(Vector2f vec, boolean self) {
		if(self) {
			x -= vec.getX();
			y -= vec.getY();
			return this;
		}else {
			return new Vector2f(x - vec.getX(), y - vec.y);
		}
	}
	
	public Vector2f mul(float scalar, boolean self) {
		if(self) {
			x *= scalar;
			y *= scalar;
			return this;
		}else {
			return new Vector2f(x * scalar, y * scalar);
		}
	}
	
	public float dot(Vector2f vec) {
		return x * vec.getX() + y * vec.getY();
	}
	
	public Vector2f copy() {
		return new Vector2f(x,y);
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
}
