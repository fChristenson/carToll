package se.fidde.cartoll.jar.repository.owner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.constants.JarStringConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class OwnerRepositoryTest {

	private OwnerRepository ownerRepository;
	private Owner owner;

	@Before
	public void setUp() throws Exception {
		ownerRepository = new MockOwnerRepositoryImpl();
		owner = MockObjectFactory.getEasyMockOwner();
	}

	@After
	public void tearDown() throws Exception {
		owner = null;
	}

	@Test
	public void testGetOwnerRepository() {
		assertNotNull("Create OwnerRepository works", ownerRepository);
	}

	@Test
	public void testGetOwner() throws Exception {
		Owner result = ownerRepository.getOwner(owner.getId());
		assertNotNull("Getting owner works", result);
	}

	@Test
	public void testGetOwner_invalid() throws Exception {
		Owner owner = ownerRepository.getOwner(-1);
		assertNotNull("Getting owner works", owner);

		assertEquals("Owner is instance of EmptyOwner", owner.getFullName().getFirstName(),
				JarStringConstants.EMPTY_NAME.toString());
	}

	@Test
	public void testAddOwner() throws Exception {
		long actual = ownerRepository.addOwner(owner);
		assertNotEquals("Owner can be added", IntegerConstants.EMPTY_ID.getInt(), actual);
	}

	@Test
	public void testRemoveOwner() throws Exception {
		ownerRepository.addOwner(owner);
		boolean actual = ownerRepository.removeOwner(owner.getId());

		assertTrue("Owner is removed", actual);
	}

	@Test
	public void testRemoveOwner_invalid() throws Exception {
		boolean removeOwner = ownerRepository.removeOwner(IntegerConstants.EMPTY_ID.getInt());
		assertFalse("Owner is not removed", removeOwner);
	}

}
