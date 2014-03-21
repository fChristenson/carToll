package se.fidde.cartoll.jar.service.passing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.repository.passing.PassingRepository;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class PassingServiceTest {

	private PassingServiceImpl passingService;
	private long id;
	private Passing passing;
	private Station station;
	private Vehicle vehicle;

	@Before
	public void setUp() throws Exception {
		passingService = new PassingServiceImpl();
		vehicle = MockObjectFactory.getEasyMockCar();
		passing = MockObjectFactory.getEasyMockPassing(MockObjectFactory.getMockDate(1));
		id = passing.getId();
	}

	@After
	public void tearDown() throws Exception {
		passing = null;
	}

	@Test
	public void testGetPassingService() {
		assertNotNull("Can get passingService", passingService);
	}

	@Test
	public void testAddPassing() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.addPassing(passing)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.addPassing(passing);

		EasyMock.verify(passingRepository);
	}

	@Test
	public void testAddPassing_invalid() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.addPassing(null)).andStubThrow(new NullPointerException());
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		try {
			passingService.addPassing(null);

		} catch (Exception e) {
			EasyMock.verify(passingRepository);
		}

		try {
			passingService.addPassing(null);

		} catch (Exception e) {
			assertTrue("Add passing fails", e instanceof NullPointerException);
		}
	}

	@Test
	public void testGetPassingById() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.getPassing(id)).andReturn(passing);
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.getPassing(id);

		EasyMock.verify(passingRepository);
	}

	@Test
	public void testGetPassingById_invalid() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.getPassing(id)).andReturn(passing);
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.getPassing(id);

		EasyMock.verify(passingRepository);
	}

	@Test
	public void testRemovePassing() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.removePassing(id)).andReturn(true);
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.removePassing(id);

		EasyMock.verify(passingRepository);
	}

	@Test
	public void testRemovePassing_invalid() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.removePassing(id)).andReturn(false);
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.removePassing(id);

		EasyMock.verify(passingRepository);
	}

	@Test
	public void testUpdatePassing() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.updatePassing(passing)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.updatePassing(passing);

		EasyMock.verify(passingRepository);
	}

	@Test
	public void testUpdatePassing_invalid() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.updatePassing(passing)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.updatePassing(passing);

		EasyMock.verify(passingRepository);
	}

	@Test
	public void testGetTotalPassings() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.getTotalPassings()).andReturn(1);
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.getTotalPassings();

		EasyMock.verify(passingRepository);

	}

	@Test
	public void testGetTotalProfit() throws Exception {
		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.getTotalProfit()).andReturn(new Currency());
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.getTotalProfit();

		EasyMock.verify(passingRepository);

	}

	@Test
	public void testGetPassingsFor_station() throws Exception {
		station = MockObjectFactory.getEasyMockStation();

		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.getTotalProfitForStation(station)).andReturn(new Currency());
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.getTotalProfitForStation(station);

		EasyMock.verify(passingRepository);

	}

	@Test
	public void testGetTotalProfitFor_vehicle() throws Exception {
		station = MockObjectFactory.getEasyMockStation();

		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.getTotalProfitForVehicle(vehicle)).andReturn(new Currency());
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.getTotalProfitFor(vehicle);

		EasyMock.verify(passingRepository);

	}

	@Test
	public void testGetTotalPassingsFor_vehicle() throws Exception {
		station = MockObjectFactory.getEasyMockStation();

		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.getTotalPassingsForVehicle(vehicle)).andReturn(1);
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.getTotalPassingsFor(vehicle);

		EasyMock.verify(passingRepository);

	}

	@Test
	public void testGetAllPassingsForStation() throws Exception {
		station = MockObjectFactory.getEasyMockStation();

		PassingRepository passingRepository = EasyMock.createMock(PassingRepository.class);
		EasyMock.expect(passingRepository.getAllPassingsForStation(station)).andReturn(new HashSet<Passing>());
		EasyMock.replay(passingRepository);

		passingService.setRepository(passingRepository);
		passingService.getAllPassingsForStation(station);

		EasyMock.verify(passingRepository);
	}
}
