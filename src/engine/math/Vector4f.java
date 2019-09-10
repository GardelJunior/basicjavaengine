package engine.math;

public class Vector4f {
private float x,y,z,w;
	
	public Vector4f() {
		this(0,0,0,0);
	}
	
	public Vector4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public float length() {
		return Mathf.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public Vector4f normalize() {
		float len = length();
		x /= len;
		y /= len;
		z /= len;
		w /= len;
		return this;
	}
	
	public Vector4f add(Vector4f vec, boolean self) {
		if(self) {
			x += vec.getX();
			y += vec.getY();
			z += vec.getZ();
			w += vec.getW();
			return this;
		}else {
			return new Vector4f(x + vec.getX(), y + vec.getY(), z + vec.getZ(), w + vec.getW());
		}
	}
	
	public Vector4f sub(Vector4f vec, boolean self) {
		if(self) {
			x -= vec.getX();
			y -= vec.getY();
			z -= vec.getZ();
			w -= vec.getW();
			return this;
		}else {
			return new Vector4f(x - vec.getX(), y - vec.getY(), z - vec.getZ(), w - vec.getW());
		}
	}
	
	public Vector4f mul(float scalar, boolean self) {
		if(self) {
			x *= scalar;
			y *= scalar;
			z *= scalar;
			w *= scalar;
			return this;
		}else {
			return new Vector4f(x * scalar, y * scalar, z * scalar, w * scalar);
		}
	}
	
	public float dot(Vector4f vec) {
		return x * vec.getX() + y * vec.getY() + z * vec.getZ() + w * vec.getW();
	}
	
	public Vector4f copy() {
		return new Vector4f(x,y,z,w);
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
	
	public float getW() {
		return w;
	}
	
	public void setW(float w) {
		this.w = w;
	}
}
