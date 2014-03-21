package se.fidde.cartoll.jar.domain.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.util.constants.MockStringConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class CarTest {

	private Car car;

	@Before
	public void setUp() {
		car = MockObjectFactory.getEasyMockCar();
	}

	@After
	public void tearDown() {
		car = null;
	}

	@Test
	public void testGetCar() {
		assertNotNull("Car is not null", car);
	}

	@Test
	public void testGetOwner() throws Exception {
		assertNotNull("Owner is not null", car.getOwner());
	}

	@Test
	public void testGetRegNumber() throws Exception {
		assertEquals("RegNumber is correct", MockStringConstants.MOCK_REG_NUMBER.toString(), car.getRegNumber());
	}

	@Test
	public void testGetCarType() throws Exception {
		assertEquals("CarType is correct", VehicleTypes.CAR, car.getType());
	}

}
