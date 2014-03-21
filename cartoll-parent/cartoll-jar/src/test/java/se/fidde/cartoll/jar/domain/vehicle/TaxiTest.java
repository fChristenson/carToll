package se.fidde.cartoll.jar.domain.vehicle;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class TaxiTest {

	private Taxi taxi;

	@Before
	public void setUp() throws Exception {
		taxi = MockObjectFactory.getEasyMockTaxi(EnviromentFriendly.TRUE);
	}

	@After
	public void tearDown() throws Exception {
		taxi = null;
	}

	@Test
	public void testGetTaxi() {
		assertNotNull(taxi);
	}

	@Test
	public void testGetEnviromentFriendly() throws Exception {
		boolean actual = taxi.isEnviromentFriendly();
		assertTrue(actual);
	}

	@Test
	public void testGetEnviromentFriendly_invalid() throws Exception {
		boolean actual = taxi.isEnviromentFriendly();
		boolean expected = false;

		assertNotEquals(expected, actual);
	}

}
