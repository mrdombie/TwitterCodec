package testing;

import java.io.IOException;

import org.junit.Test;

import poison.PoisonData;

public class TestPoison {

	@Test
	public void testPoisonGrabber() throws IOException{
		PoisonData pd = new PoisonData();
		System.out.println(pd.getDates());
		System.out.println(pd.getLocations());
		System.out.println(pd.getTimes());
		System.out.println(pd.getNames());	
	}
	
}
