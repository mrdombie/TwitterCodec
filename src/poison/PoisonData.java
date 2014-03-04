package poison;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.ListReader;

public class PoisonData {

	private static final String POISONSOURCE = "D:\\Programming\\Projects\\TwitterCodec\\poisons\\poisondata.txt";

	private List<String> times;
	private List<String> dates;
	private List<String> names;
	private List<String> locations;

	public PoisonData() throws IOException {

		times = new ArrayList<String>();
		dates = new ArrayList<String>();
		names = new ArrayList<String>();
		locations = new ArrayList<String>();

		setupPoisonData(POISONSOURCE);
	}

	private void setupPoisonData(String location) throws IOException {

		ListReader reader = new ListReader();
		List<String> sortingList = reader.getList(location);
		String flag = null;

		for (String poisonString : sortingList) {

			if (poisonString.startsWith("[") && poisonString.endsWith("]")) {
				flag = setFlag(poisonString);
			}

			if (flag.equals("NAME")) {
				names.add(poisonString);
			} else if (flag.equals("LOCATIONS")) {
				locations.add(poisonString);
			} else if (flag.equals("TIMES")) {
				times.add(poisonString);
			} else if (flag.equals("DATES")) {
				dates.add(poisonString);
			}
		}
	}

	private String setFlag(String poisonValue) {

		if (poisonValue.equals("[NAME]")) {
			return "NAME";
		} else if (poisonValue.equals("[LOCATIONS]")) {
			return "LOCATIONS";
		} else if (poisonValue.equals("[TIMES]")) {
			return "TIMES";
		} else if (poisonValue.equals("[DATES]")) {
			return "DATES";
		}
		return null;
	}

	public List<String> getTimes() {
		return times;
	}

	public List<String> getDates() {
		return dates;
	}

	public List<String> getNames() {
		return names;
	}

	public List<String> getLocations() {
		return locations;
	}

}
