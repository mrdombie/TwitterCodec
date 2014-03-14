package poison.types;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import poison.interfaces.Poison;
import poison.utils.AngelOfDeath;
import template.models.TweetTemplate;

public class QRPoison implements Poison{

	@Override
	public void applyPoison(AngelOfDeath deathAngel) {
		qrPoison(null);
	}
	
	public void qrPoison(AngelOfDeath deathAngel){
		QRCode.from(template.getBody()).to(ImageType.PNG).file();
	}

}
