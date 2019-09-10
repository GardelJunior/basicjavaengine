package engine.sound;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;

public class SoundManager {
	private static long context;
	private static long device;
	
	public static void init() {
		device = ALC10.alcOpenDevice((ByteBuffer)null);
		if(device == 0) {
			throw new IllegalStateException("Não foi possível iniciar a engine de audio.");
		}
		ALCCapabilities deviceCaps = ALC.createCapabilities(device);
		context = ALC10.alcCreateContext(device, (IntBuffer)null);
		ALC10.alcMakeContextCurrent(context);
		AL.createCapabilities(deviceCaps);
	}
	
	public static void terminate() {
		ALC10.alcDestroyContext(context);
		ALC10.alcCloseDevice(device);
	}
}
