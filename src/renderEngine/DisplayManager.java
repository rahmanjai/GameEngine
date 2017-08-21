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
	private static final int FPS_CAP = 60;
	
	//Membuat sebuah display di layar
	public static void createDisplay() {
		
		/*
		 * Atribut ini bisa di gunakan untuk menunjukan pada Context Creation yang mana 
		 * OpenGL interface akan digunakan. Termasuk versi dari OpenGL, bidang layer mana 
		 * rendering memerlukan tempat dan juga pilihan debug dan forward mode kompatiblitas.
		 * (Baca ARB_create_context spec untuk lebih detail.</p>
		 */
		ContextAttribs attribs = new ContextAttribs(3,2)
				.withForwardCompatible(true) // Returns a new ContextAttribs instance with the CONTEXT_FORWARD_COMPATIBLE_BIT_ARB bit in CONTEXT_FLAGS_ARB set to the given value.
				.withProfileCore(true); // Returns a new ContextAttribs instance with the CONTEXT_CORE_PROFILE_BIT_ARB bit in CONTEXT_PROFILE_MASK_ARB set to the given value.
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT)); // Ukuran Display
			Display.create(new PixelFormat(), attribs); // PixelFormat adalah attribut dari pixel dimana warna dan kedalaman bit detentukan.
			Display.setTitle("Game Engine");
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
