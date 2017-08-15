package renderEngine;

/*
 * Klas ini Merepresentasikan sebuah 3D model yang diletakan di
 * Memory
 */
public class RawModel {
	
	private int vaoID; //untuk mengetahui ID VAO
	private int vertexCount; // untuk mengetahui jumlah verteks dalam 3d model
	
	public RawModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
	

}
