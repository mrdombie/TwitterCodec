package watermarker;

import watermarker.utils.Valuer;

public class Cypherblock {

	String hashTag;
	int size;
	
	public int getHashValue(String hashTag){
		return Valuer.getCodeValue(hashTag);
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	
}
