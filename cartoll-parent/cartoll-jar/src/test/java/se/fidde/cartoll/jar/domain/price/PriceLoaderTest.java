package se.fidde.cartoll.jar.domain.price;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/db.xml" })
public class PriceLoaderTest {

	private PriceLoader priceLoader2;

	@Before
	public void setUp() throws Exception {
		priceLoader2 = new LocalPriceLoaderImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPriceLoader() {
		try {
			new DatabasePriceLoaderImpl();
			fail();

		} catch (Exception e) {
			assertTrue("fails if priceLevels table is not found", e instanceof SQLException);
		}

		assertNotNull("not null", priceLoader2);
	}
}
