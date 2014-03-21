package se.fidde.cartoll.jar.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.service.passing.PassingService;
import se.fidde.cartoll.jar.service.station.StationService;
import se.fidde.cartoll.jar.service.vehicle.VehicleService;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/db.xml" })
public class PassingServiceIntegrationTest {

	@Autowired
	private PassingService passingService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private StationService stationService;
	private Passing passing;
	private long id;
	private Station station;
	private Vehicle vehicle;
	private Date date;

	@Before
	public void setUp() throws Exception {
		station = MockObjectFactory.getMockStation();
		long addStation = stationService.addStation(station);
		station = stationService.getStation(addStation);

		vehicle = MockObjectFactory.getMockCar();
		vehicleService.addVehicle(vehicle);
		vehicle = vehicleService.getVehicle(vehicle.getId());

		date = MockObjectFactory.getMockDate(IntegerConstants.HIGH_COST_TIME.getInt());
		passing = new Passing(vehicle, station, date);
	}

	@Test
	public void testGetPassingService() {
		assertNotNull("passingService not null", passingService);
	}

	@Test
	public void testPassingService_CRUD() throws Exception {
		long addPassing = passingService.addPassing(passing);
		assertNotEquals("Can add passing", addPassing, -1);

		long updatePassing = passingService.updatePassing(passing);
		assertNotEquals("can update", -1, updatePassing);

		passing = passingService.getPassing(id);
		assertEquals("Can get passing", id, passing.getId());

		Collection<Passing> allPassingsForStation = passingService.getAllPassingsForStation(station);
		assertTrue("can get all passings for station", allPassingsForStation.size() > 0);

		int totalPassings = passingService.getTotalPassings();
		assertTrue("can get total passings", totalPassings > 0);

		totalPassings = passingService.getTotalPassingsForStation(station);
		assertTrue("can get total passings for station", totalPassings > 0);

		int totalPassingsFor2 = passingService.getTotalPassingsFor(vehicle);
		assertTrue("can get total passings for vehicle", totalPassingsFor2 > 0);

		Currency totalProfit = passingService.getTotalProfit();
		assertTrue("can get total profit", totalProfit.getAmount().intValue() > 0);

		totalProfit = passingService.getTotalProfitForStation(station);
		assertTrue("can get total profit for station", totalProfit.getAmount().intValue() > 0);

		totalProfit = passingService.getTotalProfitFor(vehicle);
		assertTrue("can get total profit for vehicle", totalProfit.getAmount().intValue() > 0);

		boolean removePassing = passingService.removePassing(id);
		assertTrue("Can remove passing", removePassing);
	}
}
