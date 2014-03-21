package se.fidde.cartoll.jar.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.service.vehicle.VehicleService;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/db.xml" })
public class VehicleServiceIntegrationTest {

	@Autowired
	private VehicleService vehicleService;
	private Vehicle vehicle;

	@Before
	public void setUp() throws Exception {
		vehicle = MockObjectFactory.getMockCar();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetVehicleService() {
		assertNotNull("Can get service", vehicleService);
	}

	@Test
	public void testVehicleService_CRUD() throws Exception {
		long actual = vehicleService.addVehicle(vehicle);
		assertNotEquals("add vehicle", IntegerConstants.EMPTY_ID.getInt(), actual);

		Vehicle vehicle = vehicleService.getVehicle(actual);
		assertEquals("can get vehicle", actual, vehicle.getId());

		long updateVehicle = vehicleService.updateVehicle(vehicle);
		assertEquals("can update vehicle", actual, updateVehicle);

		Collection<Vehicle> allVehicles = vehicleService.getAllVehicles();
		assertNotEquals("can get all vehicles", 0, allVehicles.size());

		boolean removeVehicle = vehicleService.removeVehicle(vehicle.getId());
		assertTrue("can remove vehicle", removeVehicle);
	}

}
