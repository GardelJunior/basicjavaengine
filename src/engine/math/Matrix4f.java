package engine.math;

public class Matrix4f {
	private float[][] values;
	
	public Matrix4f() {
		values = new float[4][4];
	}
	
	public Matrix4f setTranslation(Vector3f position) {
		values[0][0] = 1;	values[0][1] = 0;	values[0][2] = 0;	values[0][3] = position.getX();
		values[1][0] = 0;	values[1][1] = 1;	values[1][2] = 0;	values[1][3] = position.getY();
		values[2][0] = 0;	values[2][1] = 0;	values[2][2] = 1;	values[2][3] = position.getZ();
		values[3][0] = 0;	values[3][1] = 0;	values[3][2] = 0;	values[3][3] = 1;
		return this;
	}

	public Matrix4f setOrtho(float l, float r, float b, float t, float n, float f) {
		values[0][0] = 2/(r-l)	;	values[0][1] = 0		;	values[0][2] = 0		;	values[0][3] = -(r+l)/(r-l);
		values[1][0] = 0		;	values[1][1] = 2/(t-b)	;	values[1][2] = 0		;	values[1][3] = -(t+b)/(t-b);
		values[2][0] = 0		;	values[2][1] = 0		;	values[2][2] = 2/(f-n)	;	values[2][3] = -(f+n)/(f-n);
		values[3][0] = 0		;	values[3][1] = 0		;	values[3][2] = 0		;	values[3][3] = 1;
		return this;
	}
	
	public float getValue(int l, int c) {
		return values[l][c];
	}

	public float[][] getValues() {
		return values;
	}

	public void setValues(float[][] values) {
		this.values = values;
	}
}
