package engine.core;

public class Timer {
	public static final double SECOND = 1000_000_000d;
	private static double delta;
	private static long elapsed;
	private static long lastNano;
	public static int frame = 0; /* Intervalo [0 ... 1000.000.000] em 1 segundo */
	
	private static int fps,ups;
	
	public static void configUpdatePerSecond(double ups) {
		delta = 1000_000_000.0f / 60.0f;
	}
	
	public static void updateTimer() {
		lastNano = getTime();
	}
	
	public static long processCycle() {
		elapsed = getTime() - lastNano;  
		return elapsed;
	}
	
	public static boolean shouldUpdate() {
		if(elapsed > delta) {
			lastNano += delta;
			frame += delta;
			ups++;
			return true;
		}
		return false;
	}
	
	public static long getTime() {
		return System.nanoTime();
	}
	
	public static double getDelta() {
		return delta;
	}
	
	public static void incrementFPS() {
		fps++;
	}
	
	public static void printDebugInfo() {
		if(frame >= SECOND) {
			frame = 0;
			System.out.println("FPS: " + fps + " | UPS:" + ups);
			fps = ups = 0;
		}
	}
}