package testing;

import java.io.IOException;

import org.junit.Test;

import poison.utils.PoisonDataGrabber;

public class TestPoison {

	@Test
	public void testPoisonGrabber() throws IOException{
		PoisonDataGrabber pd = new PoisonDataGrabber();
		System.out.println(pd.getDates());
		System.out.println(pd.getLocations());
		System.out.println(pd.getTimes());
		System.out.println(pd.getNames());	
	}
	
}
