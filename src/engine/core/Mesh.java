package engine.core;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

import engine.utils.DataUtils;

public class Mesh {
	/* VAO (Vertex Array Object) é uma array de buffers que é alocada na memória da GPU */
	private int vao;
	/* VBO (Vertex Buffer Object) é um buffer de vértices que está sendo colocado no VAO */
	private int vbo;
	/* CBO (Color Buffer Object) é um buffer de cores que está sendo colocado no VAO */
	private int cbo;
	/* TBO (Texture Buffer Object) é um buffer de coordenadas de texturas que está sendo colocado no VAO */
	private int tbo;
	/* IBO (Index Buffer Object) é um buffer de índices que diz a ordem de renderização dos vértices */
	private int ibo;
	/* Texture ID */
	private int texture = 0;
	/* Contador de vértices na malha */
	private int vertex_count; 
	
	public Mesh() {
		vao = glGenVertexArrays();
		vbo = glGenBuffers();
		cbo = glGenBuffers();
		ibo = glGenBuffers();
		tbo = glGenBuffers();
	}
	
	/**
	 * Adiciona os vértices na malha.
	 * @param vertices
	 * @param indices
	 */
	public void addVertices(Vertex[] vertices, int[] indices) {
		this.vertex_count = indices.length;
		boolean hasColor = vertices[0].getColor() != null;
		boolean hasTexture = vertices[0].getTexCoord() != null;
		glBindVertexArray(vao);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, DataUtils.createVertexBuffer(vertices), GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * 4, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		if(hasColor) {
			glBindBuffer(GL_ARRAY_BUFFER, cbo);
			glBufferData(GL_ARRAY_BUFFER, DataUtils.createColorBuffer(vertices), GL_STATIC_DRAW);
			glVertexAttribPointer(1, 4, GL_FLOAT, false, 4 * 4, 0);
			glBindBuffer(GL_ARRAY_BUFFER, 0);
		}
		
		if(hasTexture) {
			glBindBuffer(GL_ARRAY_BUFFER, tbo);
			glBufferData(GL_ARRAY_BUFFER, DataUtils.createTextureBuffer(vertices), GL_STATIC_DRAW);
			glVertexAttribPointer(2, 2, GL_FLOAT, false, 2 * 4, 0);
			glBindBuffer(GL_ARRAY_BUFFER, 0);
		}
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, DataUtils.createIndexBuffer(indices), GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glBindVertexArray(0);
	}
	
	/**
	 * Renderiza a malha
	 */
	public void render() {
		glDrawElements(GL_TRIANGLES, vertex_count, GL_UNSIGNED_INT, 0);
	}
	
	/**
	 * Prepara a renderização
	 */
	public void preRender() {
		if(getTexture()!=0)
			glEnable(GL_TEXTURE_2D);
		else
			glDisable(GL_TEXTURE_2D);
		
		glBindTexture(GL_TEXTURE_2D, texture);
		glBindVertexArray(vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
	}
	
	/**
	 * Finaliza os passos da renderização
	 */
	public void posRender() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	/**
	 * Renderiza a malha usando pontos
	 */
	public void renderPoints() {
		glBindVertexArray(vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glDrawElements(GL_POINTS, vertex_count, GL_UNSIGNED_INT, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	/**
	 * Limpa a malha de todos os dados, após ser executado, essa malha deve ser descartada.
	 */
	public void destroy() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		glDeleteBuffers(vbo);
		glDeleteBuffers(ibo);
		glDeleteBuffers(cbo);
		glDeleteBuffers(tbo);
		glDeleteVertexArrays(vao);
	}

	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}
}
