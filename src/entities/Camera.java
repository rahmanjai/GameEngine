/*
 * Representasi Virtual Kamera
 */

package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch; //Tinggi rendahnya kamera
	private float yaw; // Arah kiri dan kana kamera
	private float rool; //Tingkat kemiringan kamera pada suatu sisi
	
	public Camera() {}

	/**
	 * Memindahkan kamera setiap tombol di klik
	 * @return
	 */
	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z-=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x+=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x-=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z+=0.04f;
		}
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRool() {
		return rool;
	}
	
	
	
}
