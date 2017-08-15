package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		// OpenGL expects vertices to be defined counter clock by default
		float[] vertices = {
				// Left bottom triagle
				-0.5f, 0.5f, 0f, 
				-0.5f, -0.5f, 0f, 
				0.5f, -0.5f, 0f, 
				// Right top triagle
				0.5f, -0.5f, 0f, 
				0.5f, 0.5f, 0f, 
				-0.5f, 0.5f, 0f
		};
		
		// Memuat verteks diatas kedalam model dan loader
		RawModel model = loader.loadToVAO(vertices);
		
		while (!Display.isCloseRequested()) {
			renderer.prepare();
			//game logic
			renderer.render(model);
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
