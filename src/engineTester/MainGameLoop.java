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
				-0.5f, 0.5f, 0, 	//V0
				-0.5f, -0.5f, 0, 	//V1
				0.5f, -0.5f, 0, 	//V2
				0.5f, 0.5f, 0, 		//V3
		};
		
		int[] indices = {
				0,1,3,	// Segitiga kir atas
				3,1,2	// Segitiga Kanan Bawah
		};
		
		// Memuat verteks diatas kedalam model dan loader
		RawModel model = loader.loadToVAO(vertices, indices);
		
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
