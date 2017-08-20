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
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {
	
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	/**
	 * Sebuah kostruktor yang akan membawa vertex file file path dan untuk
	 * fragment file akan di digunakan oleh method ini. Kita membuatnya
	 * hanya untuk mendapatkan vertexshaderID dengan memuat vertex file
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
		getAllUniformLocation();
	}
	
	protected abstract void getAllUniformLocation();
	
	protected int getUniformLocation (String uniformName) {
		return GL20.glGetUniformLocation(programID, uniformName);
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
	
	/*
	 * Beberapa methon yang akan meload nilai ke lokasi uniform ini.
	 */
	
	/**
	 * Membawa lokasi dan nilai dari variabel uniform dan harus dalam tipe float
	 * @param location
	 * @param value
	 */
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	/**
	 * Memuat vector kedalam uniform, jika kita memiliki variabel yang
	 * sangat seragam di dalam kode shader kita bisa memuat vector kedalamnya.
	 * Dengan memanggil method ini akan membawa lokasi dari e efek variabel uniform
	 * dan vector yang ingin kita muat kedalamnya.
	 * @param location
	 * @param vector
	 */
	protected void loadVector(int location, Vector3f vector) {
		GL20.glUniform3f(location, vector.x, vector.y, vector.z);
	}

	/**
	 * Memuat boolean ke dalam kode shader
	 * @param location
	 * @param value
	 */
	protected void loadBooleand(int location, boolean value) {
		float toLoad = 0;
		if(value) {
			toLoad = 1;
		}
		GL20.glUniform1f(location, toLoad);
	}
	
	/**
	 * Jika ingin memuat sebuah matriks kedalam kode shader, dalam sebuah
	 * matematika untuk variabel Uniform/Seragam kita memerlukan lokasi
	 * dari variabel uniform dan matrik yang ingin kita muat kedalamnya.
	 * @param location
	 * @param matrix
	 */
	protected void loadMatrix(int location, Matrix4f matrix) {
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
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
