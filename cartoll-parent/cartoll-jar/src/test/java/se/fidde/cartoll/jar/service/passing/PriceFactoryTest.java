package se.fidde.cartoll.jar.service.passing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.vehicle.EnviromentFriendly;
import se.fidde.cartoll.jar.domain.vehicle.Taxi;
import se.fidde.cartoll.jar.domain.vehicle.Truck;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;
import se.fidde.cartoll.jar.util.factories.PriceFactory;
import se.fidde.cartoll.jar.util.factories.PriceFactoryImpl;

public class PriceFactoryTest {

	private PriceFactory priceFactory = new PriceFactoryImpl();
	private Date date;
	private Passing passing;

	@Before
	public void setUp() throws Exception {
		date = MockObjectFactory.getMockDate(IntegerConstants.LOW_COST_TIME.getInt());
		passing = new Passing(MockObjectFactory.getEasyMockCar(), MockObjectFactory.getEasyMockStation(), date);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCost_car() throws Exception {
		int expected = IntegerConstants.CAR_COST_LOW.getInt();
		Currency price = priceFactory.getPriceFor(passing.getVehicle(), passing.getDate());
		passing.setCost(price);

		assertEquals("Cost for car is correct", expected, passing.getCost().getAmount().intValue(), 0);
	}

	@Test
	public void testGetCost_car_invalid() throws Exception {
		try {
			new Passing(null, null, null);
			fail();

		} catch (Exception e) {
			assertTrue("Exception is nullpointerexception", e instanceof NullPointerException);
		}
	}

	@Test
	public void testGetCost_Truck() throws Exception {
		Truck truck = MockObjectFactory.getEasyMockTruck();
		passing = new Passing(truck, MockObjectFactory.getEasyMockStation(), date);

		Currency price = priceFactory.getPriceFor(passing.getVehicle(), passing.getDate());
		passing.setCost(price);

		double weightFee = IntegerConstants.TRUCK_WEIGHT_FEE.getInt();
		int weightLimitForFee = IntegerConstants.TRUCK_WEIGHTLIMIT.getInt();

		weightFee = (weightFee * (truck.getWeight().getAmount() % weightLimitForFee));
		double expected = IntegerConstants.TRUCK_COST_LOW.getDouble() + weightFee;

		assertEquals("Cost for truck is correct", expected, passing.getCost().getAmount().intValue(), 0);
	}

	@Test
	public void testGetCost_Taxi() throws Exception {
		Taxi taxi = MockObjectFactory.getEasyMockTaxi(EnviromentFriendly.TRUE);
		passing = new Passing(taxi, MockObjectFactory.getEasyMockStation(), date);

		Currency price = priceFactory.getPriceFor(passing.getVehicle(), passing.getDate());
		passing.setCost(price);
		int expected = IntegerConstants.TAXI_COST_LOW.getInt();

		assertEquals("Cost for taxi is correct", expected, passing.getCost().getAmount().intValue(), 0);
	}

	@Test
	public void testGetCost_Taxi_notEnviromentFriendly() throws Exception {
		Taxi taxi = MockObjectFactory.getEasyMockTaxi(EnviromentFriendly.FALSE);
		passing = new Passing(taxi, MockObjectFactory.getEasyMockStation(), date);

		Currency price = priceFactory.getPriceFor(passing.getVehicle(), passing.getDate());
		passing.setCost(price);
		int expected = IntegerConstants.TAXI_COST_LOW_WITH_SPECIALCOST.getInt();

		assertEquals("Cost for non enviroment friendly taxi is correct", expected, passing.getCost().getAmount()
				.intValue(), 0);
	}

	@Test
	public void testGetCost_Taxi_notEnviromentFriendly_atFreePriceLevel() throws Exception {
		Taxi taxi = MockObjectFactory.getEasyMockTaxi(EnviromentFriendly.FALSE);
		date = new Date(IntegerConstants.FREE_PRICE_LEVEL.getInt());
		passing = new Passing(taxi, MockObjectFactory.getEasyMockStation(), date);

		Currency price = priceFactory.getPriceFor(passing.getVehicle(), passing.getDate());
		passing.setCost(price);
		double expected = IntegerConstants.TAXI_COST_FREE.getDouble();

		assertEquals("Cost for non enviroment friendly taxi at free privelevel is correct", expected, passing.getCost()
				.getAmount().intValue(), 0);
	}
}
