/*
 * Kelas yang akan melakukan proses rendering dari data VAO
 * Github
 */

package renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import models.RawModel;
import models.TexturedModel;

public class Renderer {
	
	/*
	 * Method yang berfungsi mempersiapkan OpenGL untuk melekukan rendering
	 * Yang mana method ini di panggi setiap frame
	 * Melakukan proses cleanign di Layar.
	 */
	public void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);		
	}
	
	public void render (TexturedModel texturedModel) {
		RawModel model = texturedModel.getRawModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
		// Sebelum Optimalisasi Verteks
		// GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
		// Seteleh Optimalisasi
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}

}
