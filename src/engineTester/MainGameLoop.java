package engineTester;

import org.lwjgl.opengl.Display;

import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shader.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer();
		
		// OpenGL expects vertices to be defined counter clock by default
		float[] vertices = {
				// Left bottom triagle
				-0.5f, 0.5f, 0, 	//V0
				-0.5f, -0.5f, 0, 	//V1
				0.5f, -0.5f, 0, 	//V2
				0.5f, 0.5f, 0, 		//V3
		};
		
		int[] indices = {
				0,1,3,	// Segitiga kir atas
				3,1,2	// Segitiga Kanan Bawah
		};
		
		float[] textureCoords = {
				0,0,	//V0
				0,1,	//V1
				1,1,	//V2
				1,0		//V3
		};
		
		// Memuat verteks diatas kedalam model dan loader
		RawModel model = loader.loadToVAO(vertices,textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("tile"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while (!Display.isCloseRequested()) {
			renderer.prepare();
			//game logic
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUp();
		shader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
