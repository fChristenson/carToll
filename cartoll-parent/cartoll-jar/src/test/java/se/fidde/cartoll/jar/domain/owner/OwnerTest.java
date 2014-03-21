package se.fidde.cartoll.jar.domain.owner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class OwnerTest {

	private Owner owner;

	@Before
	public void setUp() {
		owner = new Owner(MockObjectFactory.getEasyMockName(), MockObjectFactory.getEasyMockAdress());
	}

	@After
	public void tearDown() {
		owner = null;
	}

	@Test
	public void testGetOwner() {
		assertNotNull("Owner is not null", owner);
		assertNotNull("Owner name is not null", owner.getFullName());
		assertNotNull("Owner address is not null", owner.getFullAddress());

	}

	@Test
	public void testGetCars() {
		Set<Vehicle> cars = owner.getVehicles();
		assertTrue("Owner carList is empty", cars.size() == 0);
	}

}
