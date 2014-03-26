package watermarker.utils;

public class Valuer {

	protected String hashTag;
	
	public Valuer(String hashTag){
		this.hashTag = hashTag;
	}
	
	public static int getCodeValue(String hashTag){
		
		switch (hashTag.length()) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;
		default:
			break;
		}
		return 99999;
	}
	
}
