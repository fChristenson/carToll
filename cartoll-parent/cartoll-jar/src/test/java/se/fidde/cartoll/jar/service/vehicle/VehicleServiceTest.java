package se.fidde.cartoll.jar.service.vehicle;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.domain.vehicle.VehicleTypes;
import se.fidde.cartoll.jar.repository.vehicle.VehicleRepository;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.constants.JarStringConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;
import se.fidde.cartoll.jar.util.factories.VehicleFactory;

public class VehicleServiceTest {

	private VehicleServiceImpl vehicleService;
	private Vehicle vehicle;

	@Before
	public void setUp() throws Exception {
		vehicleService = new VehicleServiceImpl();
		vehicle = VehicleFactory.createCar(JarStringConstants.EMPTY_REG_NUMBER.toString(), VehicleTypes.CAR,
				MockObjectFactory.getMockOwner());
	}

	@After
	public void tearDown() throws Exception {
		vehicleService = null;
	}

	@Test
	public void testGetVehicle() {
		assertNotNull("Can get VehicleService", vehicleService);
	}

	@Test
	public void testAddVehicle() throws Exception {
		VehicleRepository vehicleRepository = EasyMock.createMock(VehicleRepository.class);
		EasyMock.expect(vehicleRepository.addVehicle(vehicle)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(vehicleRepository);

		vehicleService.setRepository(vehicleRepository);
		vehicleService.addVehicle(vehicle);

		EasyMock.verify(vehicleRepository);
	}

	@Test
	public void testAddVehicle_invalid() throws Exception {
		VehicleRepository vehicleRepository = EasyMock.createMock(VehicleRepository.class);
		EasyMock.expect(vehicleRepository.addVehicle(null)).andThrow(new NullPointerException());
		EasyMock.replay(vehicleRepository);

		vehicleService.setRepository(vehicleRepository);
		try {
			vehicleService.addVehicle(null);

		} catch (Exception e) {
			EasyMock.verify(vehicleRepository);
		}
	}

	@Test
	public void testGetVehicleByRegNumber() throws Exception {
		VehicleRepository vehicleRepository = EasyMock.createMock(VehicleRepository.class);
		EasyMock.expect(vehicleRepository.getVehicle(IntegerConstants.EMPTY_ID.getInt())).andReturn(vehicle);
		EasyMock.replay(vehicleRepository);

		vehicleService.setRepository(vehicleRepository);
		vehicleService.getVehicle(IntegerConstants.EMPTY_ID.getInt());

		EasyMock.verify(vehicleRepository);
	}

	@Test
	public void testGetVehicleByRegNumber_invalid() throws Exception {
		try {
			vehicleService.getVehicle(IntegerConstants.EMPTY_ID.getInt());
			fail();

		} catch (Exception e) {
			assertTrue("Get vehicle fails", e instanceof NullPointerException);
		}
	}

	@Test
	public void testRemoveVehicle() throws Exception {
		VehicleRepository vehicleRepository = EasyMock.createMock(VehicleRepository.class);
		EasyMock.expect(vehicleRepository.removeVehicle(IntegerConstants.EMPTY_ID.getInt())).andReturn(true);
		EasyMock.replay(vehicleRepository);

		vehicleService.setRepository(vehicleRepository);
		vehicleService.removeVehicle(IntegerConstants.EMPTY_ID.getInt());

		EasyMock.verify(vehicleRepository);
	}

	@Test
	public void testRemoveVehicle_invalid() throws Exception {
		VehicleRepository vehicleRepository = EasyMock.createMock(VehicleRepository.class);
		EasyMock.expect(vehicleRepository.removeVehicle(IntegerConstants.EMPTY_ID.getInt())).andReturn(false);
		EasyMock.replay(vehicleRepository);

		vehicleService.setRepository(vehicleRepository);
		vehicleService.removeVehicle(IntegerConstants.EMPTY_ID.getInt());

		EasyMock.verify(vehicleRepository);
	}

	@Test
	public void testUpdateVehicle() throws Exception {
		VehicleRepository vehicleRepository = EasyMock.createMock(VehicleRepository.class);
		EasyMock.expect(vehicleRepository.updateVehicle(vehicle)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(vehicleRepository);

		vehicleService.setRepository(vehicleRepository);
		vehicleService.updateVehicle(vehicle);

		EasyMock.verify(vehicleRepository);
	}

	@Test
	public void testUpdateVehicle_invalid() throws Exception {
		VehicleRepository vehicleRepository = EasyMock.createMock(VehicleRepository.class);
		EasyMock.expect(vehicleRepository.updateVehicle(null)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(vehicleRepository);

		vehicleService.setRepository(vehicleRepository);
		vehicleService.updateVehicle(null);

		EasyMock.verify(vehicleRepository);
	}
}
