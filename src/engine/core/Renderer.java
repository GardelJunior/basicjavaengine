package engine.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import engine.math.Matrix4f;

public class Renderer {
	private HashMap<Mesh, ArrayList<RenderableObject>> renderMapping;
	private HashMap<Class< ? extends RenderableObject >, Mesh> meshMapping;
	private HashMap<EnumOperation,List<RenderableObject>> operationList;
	
	private static Renderer renderer;
	
	public static Renderer getInstance() {
		if(renderer == null) renderer = new Renderer();
		return renderer;
	}
	
	private Renderer() {
		renderMapping = new HashMap<>();
		meshMapping = new HashMap<Class< ? extends RenderableObject >, Mesh>();
		operationList = new HashMap<EnumOperation,List<RenderableObject>>();
		Arrays.asList(EnumOperation.values()).forEach(op -> operationList.put(op, new ArrayList<>())); 
	}
	
	public void registerMesh(Class< ? extends RenderableObject > classe, Mesh model) {
		meshMapping.put(classe, model);
	}
	
	public void addEntity(RenderableObject ro) {
		operationList.get(EnumOperation.ADD).add(ro);
	}
	
	public void removeEntity(RenderableObject ro) {
		operationList.get(EnumOperation.REMOVE).add(ro);
	}
	
	public void render(Shader shader) {
		for(Entry<Mesh, ArrayList<RenderableObject>> dados : renderMapping.entrySet()) {
			ArrayList<RenderableObject> renderables = dados.getValue();
			Mesh malha = dados.getKey();
			malha.preRender();
			for(int i = 0 ; i < renderables.size() ; i++) {
				shader.setUniformMatrix4f("transform", new Matrix4f().setTranslation(renderables.get(i).getPosition()));
				malha.render();
			}
			malha.posRender();
		}
	}
	
	public void update() {
		for(ArrayList<RenderableObject> renderables : renderMapping.values()) {
			for(int i = 0 ; i < renderables.size() ; i++) {
				renderables.get(i).update();
			}
		}
		
		if(!operationList.get(EnumOperation.ADD).isEmpty()) {
			for(RenderableObject r : operationList.get(EnumOperation.ADD)) {
				Mesh mesh = meshMapping.get(r.getClass());
				if(mesh != null) {
					if(!renderMapping.containsKey(mesh)) 
						renderMapping.put(mesh, new ArrayList<RenderableObject>());
					renderMapping.get(mesh).add(r);
				}
			}
			operationList.get(EnumOperation.ADD).clear();
		}
		if(!operationList.get(EnumOperation.REMOVE).isEmpty()) {
			for(RenderableObject r : operationList.get(EnumOperation.REMOVE)) {
				renderMapping.get(meshMapping.get(r.getClass())).remove(r);
			}
			operationList.get(EnumOperation.REMOVE).clear();
		}
	}
	
	public void destroy() {
		for(Mesh malha : renderMapping.keySet()) {
			malha.destroy();
		}
		renderMapping.clear();
		meshMapping.clear();
	}
}
