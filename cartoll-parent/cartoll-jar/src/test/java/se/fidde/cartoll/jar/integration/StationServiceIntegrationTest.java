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

import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.service.station.StationService;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/db.xml" })
public class StationServiceIntegrationTest {

	@Autowired
	StationService stationService;
	private Station station;

	@Before
	public void setUp() throws Exception {
		station = MockObjectFactory.getMockStation();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetStation() {
		assertNotNull("StationService not null", stationService);
	}

	@Test
	public void testStationService_CRUD() throws Exception {
		long id = stationService.addStation(station);
		assertNotEquals("add station", id, -1);

		Station station = stationService.getStation(id);
		assertEquals("get station", id, station.getId());

		Collection<Station> stations = stationService.getAllStations();
		assertTrue("get all stations", stations.size() > 0);

		id = stationService.updateStation(station);
		assertEquals("update station", id, station.getId());

		boolean actual = stationService.removeStation(id);
		assertTrue(actual);
	}

}
