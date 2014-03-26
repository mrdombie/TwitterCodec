package poison.types;

import java.util.List;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import poison.interfaces.Poison;
import poison.utils.AngelOfDeath;
import template.models.TweetTemplate;

public class HexPoison implements Poison {

	public AngelOfDeath hexPoison(AngelOfDeath deathAngel, String word) {
		byte[] hexAsBytes = DatatypeConverter.parseHexBinary(deathAngel.getRandomTemplate().getBody());
		String output = DatatypeConverter.printHexBinary(hexAsBytes);
		return deathAngel;
	}

	@Override
	public void applyPoison(AngelOfDeath deathAngel, String word) {
		hexPoison(deathAngel, word);
	}

}
