package testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import poison.interfaces.Poison;
import poison.types.Base64Poison;
import poison.types.HexPoison;
import poison.types.IncrementalPoison;
import poison.utils.AngelOfDeath;
import utils.ModelReader;

public class TestAPI {
	
	private static String dataToInsertIntoTweets = "D:/Programming/Projects/TwitterCodec/poisons/poisondata.txt";
	
	public static void main(String[] args) throws Exception {
		
		List<Poison> poisons = new ArrayList<Poison>();
		//poisons.add(new Base64Poison());
		poisons.add(new HexPoison());
		poisons.add(new IncrementalPoison());
		
		AngelOfDeath deathAngel = new AngelOfDeath();
		deathAngel.poisonTemplatesUsing(dataToInsertIntoTweets);
		deathAngel.usingTheFollowingPoisons(poisons);
		deathAngel.templates(ModelReader.getTemplates());
		deathAngel.poison();
		
	}
}
