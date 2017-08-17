package textures;

/*
 * Kelas ModelTextur ini akan merepresentasikan sebuah tekstur
 * yang bisa digunakan untuk memberi tekstur pada model kita nantinya.
 * Saat ini ModelTextur hanya membutuhkan texturID selanjutnya kita akan
 *  beberapa hal pada class ini tapi untuk sekarang kita haya akan 
 *  menggunakan sebuah tekstur yang sederhana. Yang kita perlukan adalah ID
 *  dari tekstur dan kita hanya menambahkannya saja.
 */

public class ModelTexture {
	
	private int textureID;
	
	public ModelTexture(int id) {
		this.textureID = id;
	}
	
	public int getID() {
		return this.textureID;
	}

}
