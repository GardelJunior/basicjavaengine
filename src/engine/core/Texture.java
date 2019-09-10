package engine.core;

import org.lwjgl.opengl.GL11;

public class Texture {
	private int width;
	private int height;
	private int textureID;
	
	public Texture(int width, int height, int textureID) {
		this.width = width;
		this.height = height;
		this.textureID = textureID;
	}
	
	public void bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	}
	
	public static void unbind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	public int getTextureID() {
		return textureID;
	}
}
