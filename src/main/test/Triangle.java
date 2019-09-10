package main.test;

import engine.core.Mesh;
import engine.core.Shader;
import engine.core.Vertex;
import engine.core.Window;
import engine.math.Vector3f;
import engine.math.Vector4f;
import engine.utils.FileLoader;

public class Triangle {
	
	private Mesh model; /* Malha de vértices e cores */
	private Shader shader; /* Shader principal */
	
	public Triangle() {
		/* Instancia a Malha */
		model = new Mesh();
		
		/* Cria tres vértices (Posição do Vértice (x,y,z), Cor do Vértice (r,g,b) ) */
		Vertex a = new Vertex(new Vector3f(-0.5f,-0.5f,0), new Vector4f(1,0,0,1));
		Vertex b = new Vertex(new Vector3f( 0.0f, 0.5f,0), new Vector4f(0,1,0,1));
		Vertex c = new Vertex(new Vector3f( 0.5f,-0.5f,0), new Vector4f(0,0,1,1));
		
		/* Adiciona os vértices na malha */
		model.addVertices(new Vertex[] { a,b,c },new int[] {0,1,2});
		
		/* Instancia um novo shader */
		shader = new Shader();
		
		/* Junta os shaders de vértices e o de fragmento ao shader principal e compila eles */
		shader.addVertexShader(FileLoader.readFileAsString("/shaders/default.vertex.glsl"));
		shader.addFragmentShader(FileLoader.readFileAsString("/shaders/default.fragment.glsl"));
		shader.compileShader();
		
		/* Construção completa */
	}
	
	/* Update serve para atualizar a lógica de algo, movimento, colisões, etc... */
	public void update() {
		
	}
	
	/* Render serve para desenhar algo */
	public void render() {
		/* Ativa o shader antes de renderizar a malha */
		shader.bind();
		
		/* Renderiza a malha */
		model.render();
	}
	
	public static void main(String[] args) {
		/* Inicia o OpenGL e cria uma Janela */
		Window.createWindow(800, 600, "Triangulo com tres cores");
		
		/* Instancia um objeto renderizável */
		Triangle triangulo = new Triangle();

		while(!Window.shouldClose()) {
			
			/* Updates */
			Window.updateEvents();
			triangulo.update();
			
			/* Renders */
			Window.clearColor();
			triangulo.render();
			
			/* Troca de buffers */
			Window.swapBuffers();
		}
		/* Quando sair do loop, destroi janela, remove o contexto do opengl, e finaliza-o */
		Window.terminate();
	}
}
