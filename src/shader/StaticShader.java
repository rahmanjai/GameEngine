package shader;

import org.lwjgl.util.vector.Matrix4f;

/*
 *  class ini adalah bagian dari class Shader program yang mana
 *  berisi alamat dari vertex dan fragment shader berada.
 */

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/shader/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shader/fragmentShader.txt";
	
	private int location_transformationMatrix;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");		
		super.bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocation() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMarix(location_transformationMatrix, matrix);
	}

}
