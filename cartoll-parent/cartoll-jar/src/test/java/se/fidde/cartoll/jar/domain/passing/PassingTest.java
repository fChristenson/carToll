package se.fidde.cartoll.jar.domain.passing;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class PassingTest {

	private Passing passing;
	private Date date;

	@Before
	public void setUp() throws Exception {
		date = MockObjectFactory.getMockDate(IntegerConstants.LOW_COST_TIME.getInt());
		passing = new Passing(MockObjectFactory.getEasyMockCar(), MockObjectFactory.getEasyMockStation(), date);
	}

	@After
	public void tearDown() throws Exception {
		date = null;
		passing = null;
	}

	@Test
	public void testGetPassing() {
		assertNotNull("Passing is not null", passing);
	}

	@Test
	public void testGetCar() throws Exception {
		Vehicle vehicle = passing.getVehicle();
		assertNotNull("Car is not null", vehicle);
	}

	@Test
	public void testGetDate() throws Exception {
		Date date = passing.getDate();
		assertNotNull("Date is not null", date);
	}

	@Test
	public void testGetStation() throws Exception {
		Station station = passing.getStation();
		assertNotNull("Station is not null", station);
	}

	@Test
	public void testGetCost() throws Exception {
		Currency cost = passing.getCost();
		assertNotNull("Cost is not null", cost);
	}
}
