package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	// Variable untuk menentukan ukuran layar
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 640;
	private static final int FPS_CAP = 120;
	
	//Membuat sebuah display di layar
	public static void createDisplay() {
		
		ContextAttribs attribs = new ContextAttribs(3,2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("Game Engine Ku");
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Memberi tahu openGL dimana letak render Game
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		
	}
	
	// melakukan update display atau tampilan dilayar
	public static void updateDisplay() {
		
		//singkronisasi game untuk menjalankan FPS dengan terus menerus
		Display.sync(FPS_CAP);
		Display.update();
		
	}
	
	//menutup tampilan
	public static void closeDisplay() {
		
		Display.destroy();
		
	}

}
