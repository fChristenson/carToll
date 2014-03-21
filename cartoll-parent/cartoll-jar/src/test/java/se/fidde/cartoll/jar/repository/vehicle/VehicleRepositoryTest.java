package se.fidde.cartoll.jar.repository.vehicle;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.vehicle.Car;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class VehicleRepositoryTest {

	private VehicleRepository vehicleRepo;
	private Car car;

	@Before
	public void setUp() throws Exception {
		vehicleRepo = new MockVehicleRepositoryImpl();
		car = MockObjectFactory.getEasyMockCar();
	}

	@After
	public void tearDown() throws Exception {
		vehicleRepo = null;
		car = null;
	}

	@Test
	public void testGetCarRepo() {
		assertNotNull("Get repo works", vehicleRepo);
	}

	@Test
	public void testAddCar() throws Exception {
		long addCar = vehicleRepo.addVehicle(car);
		assertNotEquals("Can add car", IntegerConstants.EMPTY_ID.getInt(), addCar);
	}

	@Test
	public void testAddCar_invalid() throws Exception {
		try {
			vehicleRepo.addVehicle(null);
			fail();

		} catch (Exception e) {
			assertTrue("Add car fails", e instanceof NullPointerException);
		}
	}

	@Test
	public void testGetVehicle() throws Exception {
		long addVehicle = vehicleRepo.addVehicle(car);
		Vehicle vehicle = vehicleRepo.getVehicle(addVehicle);

		assertNotNull("Get car works", vehicle);
	}

}
