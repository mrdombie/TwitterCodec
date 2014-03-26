package poison.types;

import java.util.ArrayList;
import java.util.List;

public class PoisonClassList {

	@SuppressWarnings("rawtypes")
	private List<Class<?>> poisonClasses;
	
	public PoisonClassList() {
		setupBaseClasses();
	}
	
	private void setupBaseClasses(){
		try {
			poisonClasses = new ArrayList<Class<?>>();
			poisonClasses.add(Base64Poison.class);
/*			poisonClasses.add(HashTagPoison.class);
			poisonClasses.add(HexPoison.class);
			poisonClasses.add(IncrementalPoison.class);
			poisonClasses.add(QRPoison.class);
*/		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void addClass(Class<Base64Poison> clazz){
		poisonClasses.add(clazz);
	}

	public List<Class<?>> getPoisonClasses() {
		return poisonClasses;
	}

	public void setPoisonClasses(List<Class<?>> poisonClasses) {
		this.poisonClasses = poisonClasses;
	}
	
}
