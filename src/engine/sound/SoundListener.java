package engine.sound;

import static org.lwjgl.openal.AL10.*;

import engine.math.Vector3f;

public class SoundListener {

    public SoundListener() {
        this(new Vector3f(0, 0, 0));
    }

    public SoundListener(Vector3f position) {
        alListener3f(AL_POSITION, position.getX(), position.getY(), position.getZ());
        alListener3f(AL_VELOCITY, 0, 0, 0);
    }
    
    public void setSpeed(Vector3f speed) {
        alListener3f(AL_VELOCITY, speed.getX(), speed.getY(), speed.getZ());
    }

    public void setPosition(Vector3f position) {
        alListener3f(AL_POSITION, position.getX(), position.getY(), position.getZ());
    }
    
    public void setOrientation(Vector3f at, Vector3f up) {
        float[] data = new float[6];
        data[0] = at.getX();
        data[1] = at.getY();
        data[2] = at.getZ();
        data[3] = up.getX();
        data[4] = up.getY();
        data[5] = up.getZ();
        alListenerfv(AL_ORIENTATION, data);
    }
}
