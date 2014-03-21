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

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.service.owner.OwnerService;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/db.xml" })
public class OwnerServiceIntegrationTest {

	@Autowired
	private OwnerService ownerService;
	private Owner owner;

	@Before
	public void setUp() throws Exception {
		owner = MockObjectFactory.getMockOwner();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetOwnerService() {
		assertNotNull("Can get service", ownerService);
	}

	@Test
	public void testOwnerService_CRUD() throws Exception {
		long actual = ownerService.addOwner(owner);
		assertNotEquals("add owner", IntegerConstants.EMPTY_ID.getInt(), actual);

		Owner owner2 = ownerService.getOwner(actual);
		assertEquals("can get owner", actual, owner2.getId());

		Collection<Owner> allOwners = ownerService.getAllOwners();
		assertNotEquals("can get all owners", 0, allOwners.size());

		long updateOwner = ownerService.updateOwner(owner);
		assertEquals("can update owner", actual, updateOwner);

		boolean removeOwner = ownerService.removeOwner(actual);
		assertTrue("can remove owner", removeOwner);
	}

}
