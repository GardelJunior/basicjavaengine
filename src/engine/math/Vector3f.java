package engine.math;

public class Vector3f {
	private float x,y,z;
	
	public Vector3f() {
		this(0,0,0);
	}
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length() {
		return Mathf.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3f normalize() {
		float len = length();
		x /= len;
		y /= len;
		z /= len;
		return this;
	}
	
	public Vector3f add(Vector3f vec, boolean self) {
		if(self) {
			x += vec.getX();
			y += vec.getY();
			z += vec.getZ();
			return this;
		}else {
			return new Vector3f(x + vec.getX(), y + vec.getY(), z + vec.getZ());
		}
	}
	
	public Vector3f sub(Vector3f vec, boolean self) {
		if(self) {
			x -= vec.getX();
			y -= vec.getY();
			z -= vec.getZ();
			return this;
		}else {
			return new Vector3f(x - vec.getX(), y - vec.getY(), z - vec.getZ());
		}
	}
	
	public Vector3f mul(float scalar, boolean self) {
		if(self) {
			x *= scalar;
			y *= scalar;
			z *= scalar;
			return this;
		}else {
			return new Vector3f(x * scalar, y * scalar, z * scalar);
		}
	}
	
	public float dot(Vector3f vec) {
		return x * vec.getX() + y * vec.getY() + z * vec.getZ();
	}
	
	public Vector3f copy() {
		return new Vector3f(x,y,z);
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
	
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
}
