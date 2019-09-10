package engine.math;

public class Mathf {
	public static final float PI = 3.14159265359f;
	public static final float Deg2Rad = PI / 180.0f;
	public static final float Rad2Deg = 180.0f / PI;
	
	public static float sqrt(float value) {
		return (float) Math.sqrt(value);
	}
	
	public static float abs(float val) {
		return (val<0)? -val : val;
	}
	
	public static float clamp(float val, float valMin, float valMax) {
		if(val < valMin) {
			return valMin;
		}else if(val > valMax) {
			return valMax;
		}else {
			return val;
		}
	}
}
