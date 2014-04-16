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
			poisonClasses.add(NewPoisonType.class);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void addClass(Class<?> clazz){
		poisonClasses.add(clazz);
	}

	public List<Class<?>> getPoisonClasses() {
		return poisonClasses;
	}

	public void setPoisonClasses(List<Class<?>> poisonClasses) {
		this.poisonClasses = poisonClasses;
	}
	
}
