package se.fidde.cartoll.jar.repository.passing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class PassingRepositoryTest {

	private PassingRepository passingRepository;
	private long id;
	private Passing passing;

	@Before
	public void setUp() throws Exception {
		passingRepository = new MockPassingRepositoryImpl();
		passing = MockObjectFactory.getEasyMockPassing(MockObjectFactory.getMockDate(1));
		id = passing.getId();
	}

	@After
	public void tearDown() throws Exception {
		passing = null;
		passingRepository = null;
	}

	@Test
	public void testGetPassingsRepository() {
		assertNotNull("PassingsRepository not null", passingRepository);
	}

	@Test
	public void testAddPassing() throws Exception {
		long addPassing = passingRepository.addPassing(passing);
		assertNotEquals("Passing added", addPassing, IntegerConstants.EMPTY_ID.getInt());
	}

	@Test
	public void testAddPassing_invalid() throws Exception {
		try {
			passingRepository.addPassing(null);
			fail();

		} catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}

	@Test
	public void testGetPassingById() throws Exception {
		Passing passing = passingRepository.getPassing(id);

		assertNotNull("Can get passing", passing);
		assertNotEquals("Passing is correct passing", passing, Passing.getEmptyPassing());
	}

	@Test
	public void testGetPassingById_invalid() throws Exception {
		Passing passing = passingRepository.getPassing(-1);
		assertNotNull("Get passing does not work", passing);
	}

	@Test
	public void testRemovePassing() throws Exception {
		passingRepository.addPassing(passing);
		boolean removePassing = passingRepository.removePassing(id);

		assertTrue("Passing removed", removePassing);
	}

	@Test
	public void testRemovePassing_invalid() throws Exception {
		boolean removePassing = passingRepository.removePassing(-1);
		assertFalse("Remove passing fail", removePassing);
	}

	@Test
	public void testGetTotalProfit() throws Exception {
		try {
			passingRepository.getTotalProfit();

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetTotalPassings() throws Exception {
		try {
			passingRepository.getTotalPassings();

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetTotalProfitFor_station() throws Exception {
		try {
			passingRepository.getTotalProfitForStation(MockObjectFactory.getEasyMockStation());

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetTotalPassingsFor_station() throws Exception {
		try {
			passingRepository.getTotalPassingsForStation(MockObjectFactory.getEasyMockStation());

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetTotalProfitFor_vehicle() throws Exception {
		try {
			passingRepository.getTotalProfitForVehicle(MockObjectFactory.getEasyMockCar());

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetTotalPassingsFor_vehicle() throws Exception {
		try {
			passingRepository.getTotalPassingsForVehicle(MockObjectFactory.getEasyMockCar());

		} catch (Exception e) {
			fail();
		}
	}
}
