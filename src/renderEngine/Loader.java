package renderEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import models.RawModel;

/*
 * Kelas ini akan menangani proses muat 3d model kedalam 
 * memori dengan menyimpan data posisi model dalam VAO 
 */
public class Loader {
	
	/*
	 * Memoi manajement dimana kita akan menghapus data yang telah kita buat 
	 * di memori ketika engine kita tutup
	 */
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	/*
	 * Sebuah metode yang akan memebawa posisi dari model vertex
	 * dan memiuat data tersebut kedalam sebuah VAO dan lalu
	 * mengembalikan informasi tentang VAO sebagai sebuah RawModel objek
	 */
	public RawModel loadToVAO(float[] positions,float[] textureCoords, int[] indices) {
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataAttributeList(0,3, positions); //
		storeDataAttributeList(1,2, textureCoords); //Koordinat tekstur di dalam VAO
		unbindVAO();
		return new RawModel (vaoID, indices.length);
	}
	
	/*
	 * Berfungsi untuk memuad tekstur kedalam OpenGL yang nantinya bisa kita gunakan.
	 * Method ini akan membawa nama file dari tekstur yang ingin kita gunakan,
	 * memuatnya kedalam memori dan mengembalikan ID dari tekstur tersebut 
	 * sehigga kita bisa mengakses tekstur dan menggunakannya kapanpun kita inginkan
	 */
	public int loadTexture(String fileName) {
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	//Menghapus data di memori ketiak engine di tutup
	public void cleanUp() {
		for(int vao:vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for(int texture:textures) {
			GL11.glDeleteTextures(texture);
		}
	}
	
	// Membuat VAO
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();	// Mengembalikan n vertex arrray objek dalam arrays
		vaos.add(vaoID); // Berguna ketika nantinya kita akan menghapus VAO
		GL30.glBindVertexArray(vaoID);	//
		return vaoID;
	}
	
	/**
	 * Menyimpan data kedalam daftar attribut
	 * @param attributeNumber
	 * @param data
	 */
	private void storeDataAttributeList(int attributeNumber,int coordinateSize, float[] data) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID); //Segera seteleh kita membat VBO maka IDnya akan disimpa kedalam VBOLISt
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Setelah VAO digunakan kita harus meng-unbind(me-nonaktifkan) VAO tersebut
	 */
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	/**
	 * Method disini berjalan memuat indeks buffer yang akan digunakan
	 * untuk efisiensi pembuatan Verteks seperti yang sudah dijelaskan
	 * Verteks berulang akan dibuatkan indeknya yang dengan indeks itu
	 * sebuah objek dibuat.
	 */
	private void bindIndicesBuffer(int[] indices) {
		// Membuat VBO Kosong
		int vboID = GL15.glGenBuffers();
		// Fungsi untuk mendelete data vboID di dalam memori ketika Game nantinya di tutup
		vbos.add(vboID);
		// Digunakan sebagai Indeks Buffer dan lalu kita letakan ID dari VBA
		// Yang ingin kita Bind(ikat)
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	/**
	 * Menyimpan indeks kedalam VBA dan seperti data lainnya untuk bisa tersimpan
	 * kedalam sebuah float buffer pertama-tama indeks harus di simpan dahulu
	 * kedalam sebuah Int Buffer.
	 */
	private IntBuffer storeDataInIntBuffer(int[] data) {
		// Membuat sebuah int buffer kosong
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		// Meletakan Data kedalam Buffer
		buffer.put(data);
		//Agar buffer siap untuk digunakan maka diperlukan flip
		buffer.flip();
		return buffer;
	}
	
	
	/**
	 * Data harus di simpan di dalam sebuah VBO sebagai sebuah float buffer
	 * Method ini berfungsi untuk  mengubah float array dari sebuah data
	 * kedalam float buffer
	 */
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		/*
		 * Membuat sebuah float buffer kososng
		 * Dengan memangiing BufferUtils
		 */
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length); 
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

}
