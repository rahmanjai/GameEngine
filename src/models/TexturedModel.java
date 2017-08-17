package models;

import textures.ModelTexture;

/*
 * Merupakan sebuah kelas yang menampung Model dan Tekstur
 * Sehingga menjadi sebuah medel yang bertekstur
 */

public class TexturedModel {
	
	private RawModel rawModel;
	private ModelTexture texture;
	
	public TexturedModel (RawModel model, ModelTexture texture) {
		this.rawModel = model;
		this.texture = texture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}

}
