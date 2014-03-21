package se.fidde.cartoll.jar.domain.owner;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class AddressTest {

	private Address address;

	@Before
	public void setUp() throws Exception {
		address = MockObjectFactory.getEasyMockAdress();
	}

	@After
	public void tearDown() throws Exception {
		address = null;
	}

	@Test
	public void testGetAdress() {
		assertNotNull("Adress is not null", address);
	}
}
