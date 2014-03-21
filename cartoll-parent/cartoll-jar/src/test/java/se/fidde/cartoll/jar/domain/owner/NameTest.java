package se.fidde.cartoll.jar.domain.owner;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.domain.owner.Name;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class NameTest {

	private Name name;

	@Before
	public void setUp() throws Exception {
		name = MockObjectFactory.getEasyMockName();
	}

	@After
	public void tearDown() throws Exception {
		name = null;
	}

	@Test
	public void test() {
		assertNotNull("Name is not null", name);
	}

}
