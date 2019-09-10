package engine.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import engine.core.Texture;

public class FileLoader {
	public static String readFileAsString(String fileName) {
		StringBuilder builder = new StringBuilder();
		try {
			File file = new File(FileLoader.class.getResource(fileName).getFile());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append("\n");
			}
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static Texture readTexture(String filepath) {
		File f = new File(FileLoader.class.getResource(filepath).getFile());
		int tex = 0;
		BufferedImage img = null;
		try {
			img = ImageIO.read(f);
			int pixels[] = new int[img.getWidth() * img.getHeight()]; 
			img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());
			ByteBuffer pbuffer = BufferUtils.createByteBuffer(pixels.length * 4);
			for(int i = 0 ; i < pixels.length ; i++) {
				byte a = (byte) (pixels[i] >> 24);
				byte r = (byte) ((pixels[i] >> 16) & 0xff);
				byte g = (byte) ((pixels[i] >> 8) & 0xffff);
				byte b = (byte) ((pixels[i]) & 0xffffff);
				pbuffer.put(r);
				pbuffer.put(g);
				pbuffer.put(b);
				pbuffer.put(a);
			}
			pbuffer.flip();
			tex = GL11.glGenTextures();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, img.getWidth(), img.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE,pbuffer);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Texture(img.getWidth(), img.getHeight(), tex);
	}
}
