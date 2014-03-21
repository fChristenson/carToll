package se.fidde.cartoll.jar.service.owner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.repository.owner.MockOwnerRepositoryImpl;
import se.fidde.cartoll.jar.repository.owner.OwnerRepository;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class OwnerServiceTest {

	private OwnerServiceImpl ownerService;
	private Owner owner;
	private long id;

	@Before
	public void setUp() throws Exception {
		ownerService = new OwnerServiceImpl();
		owner = MockObjectFactory.getEasyMockOwner();
		id = owner.getId();
	}

	@After
	public void tearDown() throws Exception {
		owner = null;
	}

	@Test
	public void testGetOwnerService() {
		assertNotNull("OwnerService is created", ownerService);
	}

	@Test
	public void testGetOwner() throws Exception {
		OwnerRepository ownerRepository = EasyMock.createMock(MockOwnerRepositoryImpl.class);
		EasyMock.expect(ownerRepository.getOwner(id)).andReturn(owner);
		EasyMock.replay(ownerRepository);

		ownerService.setRepository(ownerRepository);
		ownerService.getOwner(owner.getId());

		EasyMock.verify(ownerRepository);
	}

	@Test
	public void testGetOwner_invalid() throws Exception {
		OwnerRepository ownerRepository = EasyMock.createMock(MockOwnerRepositoryImpl.class);
		EasyMock.expect(ownerRepository.getOwner(id)).andReturn(owner);
		EasyMock.replay(ownerRepository);

		ownerService.setRepository(ownerRepository);
		ownerService.getOwner(owner.getId());

		EasyMock.verify(ownerRepository);
	}

	@Test
	public void testAddOwner() throws Exception {
		OwnerRepository ownerRepository = EasyMock.createMock(MockOwnerRepositoryImpl.class);
		EasyMock.expect(ownerRepository.addOwner(owner)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(ownerRepository);

		ownerService.setRepository(ownerRepository);
		ownerService.addOwner(owner);

		EasyMock.verify(ownerRepository);
	}

	@Test
	public void testAddOwner_invalid() throws Exception {
		try {
			ownerService.addOwner(null);
			fail();

		} catch (Exception e) {
			assertTrue("Can not add null", e instanceof NullPointerException);
		}
	}

	@Test
	public void testRemoveOwner() throws Exception {
		OwnerRepository ownerRepository = EasyMock.createMock(MockOwnerRepositoryImpl.class);
		EasyMock.expect(ownerRepository.removeOwner(id)).andReturn(true);
		EasyMock.replay(ownerRepository);

		ownerService.setRepository(ownerRepository);
		ownerService.removeOwner(id);

		EasyMock.verify(ownerRepository);
	}

	@Test
	public void testRemoveOwner_invalid() throws Exception {
		OwnerRepository ownerRepository = EasyMock.createMock(MockOwnerRepositoryImpl.class);
		EasyMock.expect(ownerRepository.removeOwner(id)).andReturn(false);
		EasyMock.replay(ownerRepository);

		ownerService.setRepository(ownerRepository);
		ownerService.removeOwner(id);

		EasyMock.verify(ownerRepository);
	}

	@Test
	public void testUpdateOwner() throws Exception {
		OwnerRepository ownerRepository = EasyMock.createMock(MockOwnerRepositoryImpl.class);
		EasyMock.expect(ownerRepository.updateOwner(owner)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(ownerRepository);
		ownerService.setRepository(ownerRepository);
		ownerService.updateOwner(owner);
		EasyMock.verify(ownerRepository);
	}

	@Test
	public void testUpdateOwner_invalid() throws Exception {
		OwnerRepository ownerRepository = EasyMock.createMock(MockOwnerRepositoryImpl.class);
		EasyMock.expect(ownerRepository.updateOwner(owner)).andReturn((long) IntegerConstants.EMPTY_ID.getInt());
		EasyMock.replay(ownerRepository);
		ownerService.setRepository(ownerRepository);
		ownerService.updateOwner(owner);
		EasyMock.verify(ownerRepository);
	}

	@Test
	public void testGetAllOwners() throws Exception {
		OwnerRepository ownerRepository = EasyMock.createMock(MockOwnerRepositoryImpl.class);
		EasyMock.expect(ownerRepository.getAllOwners()).andReturn(new HashSet<Owner>());
		EasyMock.replay(ownerRepository);
		ownerService.setRepository(ownerRepository);
		ownerService.getAllOwners();
		EasyMock.verify(ownerRepository);
	}
}
