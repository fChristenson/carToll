package se.fidde.cartoll.jar.domain.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class TruckTest {

	private Truck truck;

	@Before
	public void setUp() throws Exception {
		truck = MockObjectFactory.getEasyMockTruck();
	}

	@After
	public void tearDown() throws Exception {
		truck = null;
	}

	@Test
	public void testGetTruck() {
		assertNotNull("Truck is not null", truck);
	}

	@Test
	public void testGetWeight() throws Exception {
		Weight expected = MockObjectFactory.getEasyMockWeight();
		Weight actual = truck.getWeight();

		assertEquals("Truck weight is correct", expected.getAmount(), actual.getAmount(), 0);
		assertEquals("Truck weight unit is correct", expected.getUnit(), actual.getUnit());
	}

}
