package watermarker.keyIndex;

public enum KeyIndex {

	HEX(20),
	BINARY(25);
	
	int keyIndex;
	
	KeyIndex(int keyIndex){
		this.keyIndex = keyIndex;
	}
	
	public int getKeyName(){
		return keyIndex;
	}
	
}
