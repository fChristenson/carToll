package se.fidde.cartoll.jar.domain.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.util.constants.MockStringConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class VehicleIdTest {

	private VehicleMockData carId;

	@Before
	public void setUp() throws Exception {
		carId = MockObjectFactory.getEasyMockVehicleId(VehicleTypes.CAR);
	}

	@After
	public void tearDown() throws Exception {
		carId = null;
	}

	@Test
	public void testGetCarId() {
		assertNotNull(carId);
	}

	@Test
	public void testGetRegNumber() throws Exception {
		assertEquals(MockStringConstants.MOCK_REG_NUMBER.toString(), carId.getRegNumber());
	}

	@Test
	public void testGetType() throws Exception {
		assertEquals(VehicleTypes.CAR, carId.getType());
	}

	@Test
	public void testGetOwner() throws Exception {
		assertNotNull(carId.getOwner());
	}
}
