package poison.enums;

public enum PoisonEnum {

	BAS(1),
	HAS(2),
	NEW(3),
	INC(4),
	QRP(5);
	
	private PoisonEnum(int poisonType) {
		this.poisonType = poisonType;
	}
	
	int poisonType;
	
	public int getPoisonType(){
		return poisonType;
	}
	
	public static String getNameByCode(int code) {
		for (PoisonEnum e : PoisonEnum.values()) {
			if (code == e.getPoisonType()){
				return e.name();
			}
		}
		return null;
	}

}
