package main.trabalho1;

import engine.core.Input;
import engine.core.Renderer;
import engine.core.Shader;
import engine.core.Timer;
import engine.core.Window;
import engine.math.Matrix4f;
import engine.math.Vector3f;
import engine.math.Vector4f;
import engine.utils.FileLoader;
import main.trabalho1.entidades.InimigoIxpertinho;
import main.trabalho1.entidades.Jogador;
import main.trabalho1.entidades.Laser;
import main.trabalho1.modelos.ModeloInimigo;
import main.trabalho1.modelos.ModeloJogador;
import main.trabalho1.modelos.ModeloLaser;

public class InvasoresEspaciais {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;
	
	private Jogador jogador;
	
	private Shader shaderPrincipal;
	private Renderer renderer;
	
	public InvasoresEspaciais() {
		jogador = new Jogador(new Vector3f(WIDTH/2+25,(2.5f*HEIGHT)/4,0));
		renderer = Renderer.getInstance();
		
		renderer.registerMesh(Jogador.class, new ModeloJogador());
		renderer.registerMesh(InimigoIxpertinho.class, new ModeloInimigo());
		renderer.registerMesh(Laser.class, new ModeloLaser(new Vector4f(1, 0, 0,1)));
		
		shaderPrincipal = new Shader();
		shaderPrincipal.addVertexShader(FileLoader.readFileAsString("/shaders/invasor.vertex.glsl"));
		shaderPrincipal.addFragmentShader(FileLoader.readFileAsString("/shaders/invasor.fragment.glsl"));
		shaderPrincipal.compileShader();
		
		shaderPrincipal.addUniform("transform");
		shaderPrincipal.addUniform("projection");
		shaderPrincipal.bind();
		shaderPrincipal.setUniformMatrix4f("projection", new Matrix4f().setOrtho(0, WIDTH, HEIGHT, 0, -0.01f, -0.01f));
		renderer.addEntity(jogador);
		
		Input.enableKey(Input.KEY_LEFT,Input.KEY_RIGHT,Input.KEY_SPACE);
		Input.enableJoyStick(0);
	}
	
	public void update() {
		renderer.update();
	}
	
	public void render() {
		shaderPrincipal.bind();
		shaderPrincipal.setUniformMatrix4f("projection", new Matrix4f().setOrtho(0, 800, 600, 0, -0.01f, 0.01f));
		renderer.render(shaderPrincipal);
	}
	
	public void destroy() {
		renderer.destroy();
	}
	
	public static void main(String[] args) {
		Window.createWindow(WIDTH, HEIGHT, "Invasores Espaciais");
		
		InvasoresEspaciais jogo = new InvasoresEspaciais();
		
		Timer.configUpdatePerSecond(60);
		Timer.updateTimer();
		
		while(!Window.shouldClose()) {
			Timer.processCycle();
			if(Timer.shouldUpdate()) {
				Window.updateEvents();
				jogo.update();
				Input.update();
			}
			Window.clearColor();
			Timer.incrementFPS();
			jogo.render();
			Window.swapBuffers();
			Timer.printDebugInfo();
		}
		jogo.destroy();
		Window.terminate();
	}
}
