package se.fidde.cartoll.jar.domain.station;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.util.constants.MockStringConstants;

public class StationTest {

	private Station station;

	@Before
	public void setUp() throws Exception {
		station = new Station(MockStringConstants.MOCK_NAME.toString());
	}

	@After
	public void tearDown() throws Exception {
		station = null;
	}

	@Test
	public void testGetStation() {
		assertNotNull("Station is not null", station);
	}

	@Test
	public void testGetName() throws Exception {
		String string = station.getName();
		assertFalse("Station name is not empty", string.length() == 0);
	}

}
