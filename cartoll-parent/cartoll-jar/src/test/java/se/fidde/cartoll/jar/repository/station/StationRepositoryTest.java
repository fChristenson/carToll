package se.fidde.cartoll.jar.repository.station;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.constants.JarStringConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class StationRepositoryTest {

	private StationRepository stationRepository;
	private Station station;
	private long id;

	@Before
	public void setUp() throws Exception {
		stationRepository = new MockStationRepositoryImpl();
		station = MockObjectFactory.getEasyMockStation();
		id = station.getId();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetStation() throws IOException, SQLException {
		Station station = stationRepository.getStation(id);
		assertNotNull("Can get station", station);
	}

	@Test
	public void testGetStation_invalid() throws Exception {
		Station actual = stationRepository.getStation(id);

		assertNotNull("Can get station", actual);
		assertEquals("Station instanceof EmptyStation", actual.getName(), JarStringConstants.EMPTY_NAME.toString());
	}

	@Test
	public void testRemoveStation() throws Exception {
		stationRepository.addStation(station);
		boolean actual = stationRepository.removeStation(station.getId());

		assertTrue("Station is removed", actual);
	}

	@Test
	public void testRemoveStation_invalid() throws Exception {
		boolean actual = stationRepository.removeStation(IntegerConstants.EMPTY_ID.getInt());
		assertFalse("Station is not removed", actual);
	}

	@Test
	public void testAddStation() throws Exception {
		long addStation = stationRepository.addStation(station);
		assertTrue("can add station", addStation != IntegerConstants.EMPTY_ID.getInt());
	}

	@Test
	public void testAddStation_invalid() throws Exception {
		try {
			stationRepository.addStation(null);
			fail();

		} catch (Exception e) {
			assertTrue("add station fail", e instanceof NullPointerException);
		}
	}

	@Test
	public void testUpdateStation() throws Exception {
		long updateStation = stationRepository.updateStation(station);
		assertTrue("can update station", updateStation != IntegerConstants.EMPTY_ID.getInt());
	}

	@Test
	public void testUpdateStation_invalid() throws Exception {
		try {
			stationRepository.updateStation(null);

		} catch (Exception e) {
			assertTrue("update fail", e instanceof NullPointerException);
		}
	}
}
