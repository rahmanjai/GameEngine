/**
 * Pada dasarnya kelas entitas ini akan jadi seperti sebuah instan
 * dari tekstur model. Jadi ini akan berisi sebuah model bertekstur
 * juga posisi rotasi dan skala dari model yang akan hendak kita render
 * dialam dunia 3D. Dan entitas akan mengijinkan kita untuk memiliki 
 * banyak entitas sekaligus yang semuanya menggunakan model tekstur yang
 * sama. Yang artinya kita mampu untuk merender beberapa model tekstur
 * beberapa kali tapi kita hanya perlu memuat model sekali.
 */

package entities;

import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class Entity {
	
	private TexturedModel model;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	
	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
	
	/**
	 * Ini akan mampu memindah atau menggerakan entitas di dalam lingkungan
	 * dan method ini akan dibawa dakam sebuah x y dan z nilai dari seberapa
	 * jauh kita akan memindah sebuah entitas. Yang kita lakukan disini
	 * adlah menambah posisi entitas asaat ini dengan nilai dari x, y dan z.
	 * @param dx
	 * @param dy
	 * @param dz
	 */
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x+=dx;
		this.position.y+=dy;
		this.position.z+=dz;
	}
	
	/**
	 * Merotasi entitas dengan jumlah tertentu, kita akan memerlukan
	 * DX, DY dan DZ yang ketiganya merepresentasikan derazat rotasi
	 * yang kata inginkan disekitar sumbu dan juga kita hanya akan
	 * menambah rotasi saat ini dengan nilai yang telah kita bawa
	 * didalam mthod.
	 * @param dx
	 * @param dy
	 * @param dz
	 */
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	
	

}
