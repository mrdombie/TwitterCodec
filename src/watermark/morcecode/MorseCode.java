package watermark.morcecode;

public enum MorseCode implements Decryption {

	HEXDECRYPT(1){
		@Override
		public String decrypt() {
			return null;		
		}
	},
	
	BINARYDECRYPT(2){
		@Override
		public String decrypt() {
			return null;
		}
	};
	
	protected int code;
	
	MorseCode(int code){
		this.code = code;
	}
	
	public int getDecryption(){
		return this.code;
	}
	
	public static String getNameByCode(int code) {
		for (MorseCode e : MorseCode.values()) {
			if (code == e.getDecryption()){
				return e.name();
			}	
		}
		return null;
	}
}
