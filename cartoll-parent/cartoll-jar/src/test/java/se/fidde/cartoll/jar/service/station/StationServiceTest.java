package se.fidde.cartoll.jar.service.station;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.repository.station.StationRepository;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class StationServiceTest {

	private StationServiceImpl stationService;
	private StationRepository mockRepo;
	private Station station;
	private long id;

	@Before
	public void setUp() throws Exception {
		stationService = new StationServiceImpl();
		station = MockObjectFactory.getEasyMockStation();
	}

	@After
	public void tearDown() throws Exception {
		station = null;
	}

	@Test
	public void testGetStationService() {
		assertNotNull("Station service is not null", stationService);
	}

	@Test
	public void testGetStation() throws Exception {
		mockRepo = EasyMock.createMock(StationRepository.class);

		EasyMock.expect(mockRepo.getStation(id)).andReturn(MockObjectFactory.getEasyMockStation());
		EasyMock.replay(mockRepo);

		stationService.setRepository(mockRepo);
		stationService.getStation(id);

		EasyMock.verify(mockRepo);
	}

	@Test
	public void testGetAllStations() throws Exception {
		mockRepo = EasyMock.createMock(StationRepository.class);

		EasyMock.expect(mockRepo.getAllStations()).andReturn(new HashSet<Station>());
		EasyMock.replay(mockRepo);

		stationService.setRepository(mockRepo);
		stationService.getAllStations();

		EasyMock.verify(mockRepo);
	}

	@Test
	public void testAddStation() throws Exception {
		mockRepo = EasyMock.createMock(StationRepository.class);

		EasyMock.expect(mockRepo.addStation(station)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(mockRepo);

		stationService.setRepository(mockRepo);
		stationService.addStation(station);

		EasyMock.verify(mockRepo);
	}

	@Test
	public void testAddStation_invalid() throws Exception {
		try {
			stationService.addStation(null);
			fail();

		} catch (Exception e) {
			assertTrue("Can not add station", e instanceof NullPointerException);
		}
	}

	@Test
	public void testRemoveStation() throws Exception {
		mockRepo = EasyMock.createMock(StationRepository.class);

		EasyMock.expect(mockRepo.removeStation(id)).andReturn(true);
		EasyMock.replay(mockRepo);

		stationService.setRepository(mockRepo);
		stationService.removeStation(id);

		EasyMock.verify(mockRepo);
	}

	@Test
	public void testRemoveStation_invalid() throws Exception {
		mockRepo = EasyMock.createMock(StationRepository.class);

		EasyMock.expect(mockRepo.removeStation(id)).andReturn(true);
		EasyMock.replay(mockRepo);

		stationService.setRepository(mockRepo);
		stationService.removeStation(id);

		EasyMock.verify(mockRepo);
	}

	@Test
	public void testUpdateStation() throws Exception {
		mockRepo = EasyMock.createMock(StationRepository.class);

		EasyMock.expect(mockRepo.updateStation(station)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(mockRepo);

		stationService.setRepository(mockRepo);
		stationService.updateStation(station);

		EasyMock.verify(mockRepo);
	}

	@Test
	public void testUpdateStation_invalid() throws Exception {
		try {
			stationService.updateStation(null);
			fail();

		} catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}

	}
}
