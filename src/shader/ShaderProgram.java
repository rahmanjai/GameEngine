/*
 * Ini merupak sebuah kelas Shader yang berpungsi untuk mengatur
 * warna, posis, ataupun efek lainnya.
 * 
 * Meupakan sebuah kelas abstrak yang berarti kelas ini mengandung
 * satu atau lebih method abstrak. Method abstrak adalah sebiah method
 * yang dideklarasikan namun tidak mengandung implementasi.
 */

package shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class ShaderProgram {
	
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	/**
	 * Sebuah kostruktor yang akan membawa vertex file file path
	 * dan untuk fragment file akan di digunakan oleh method ini. Kita hanya
	 * membuatnya untuk mendapatkan vertexshaderID dengan memuat vertex file
	 * dan kita sudah memiliki GL_VERTEX_SHADER
	 */
	public ShaderProgram(String vertexFile, String fragmentFile) {
		vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
	}
	
	public void start() {
		GL20.glUseProgram(programID);
	}
	
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	public void cleanUp() {
		stop();
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteProgram(programID);
	}
	
	protected abstract void bindAttributes();
	
	protected void bindAttribute(int attribute, String variableName) {
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}
	
	/**
	 *  Untuk meload shader source code file seperti dua file
	 *  txt yang bernama fragmentShader dan vertexShader dengan ini
	 *  medthod akan mengambil nama file dari source code file dan
	 *  juga sebuha int yang mengidikasikan apakah itu sebuah vertex
	 *  atau fragmen shader.
	 */
	private static int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) !=null) {
				shaderSource.append(line).append("\n");
			}
			reader.close();
		}catch (IOException e) {
			System.err.println("Tidak bisa membaca file!");
			e.printStackTrace();
			System.exit(-1);
		}
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS)==GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Tidak bisa mengcompile shader.");
			System.exit(-1);
		}
		return shaderID;
	}

}
