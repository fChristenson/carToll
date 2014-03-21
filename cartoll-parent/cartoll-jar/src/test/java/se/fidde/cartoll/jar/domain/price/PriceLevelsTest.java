package se.fidde.cartoll.jar.domain.price;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class PriceLevelsTest {

	private LocalPriceLoaderImpl priceLevels;
	private Date date;

	@Before
	public void setUp() throws Exception {
		priceLevels = new LocalPriceLoaderImpl();
		date = MockObjectFactory.getMockDate(IntegerConstants.FREE_PRICE_LEVEL.getInt());
	}

	@After
	public void tearDown() throws Exception {
		priceLevels = null;
		date = null;
	}

	@Test
	public void testGetPriceLevels() {
		assertNotNull("PriceLevels are not null", priceLevels);
	}

	@Test
	public void testGetPriceLevelFor_FREE() throws Exception {
		PriceLevelTypes priceLevel = priceLevels.getPriceLevelFor(date);
		assertEquals(priceLevel, PriceLevelTypes.FREE);
	}

	@Test
	public void testGetPriceLevelFor_LOW() throws Exception {
		date = MockObjectFactory.getMockDate(IntegerConstants.LOW_COST_TIME.getInt());
		PriceLevelTypes priceLevel = priceLevels.getPriceLevelFor(date);

		assertEquals(priceLevel, PriceLevelTypes.LOW);
	}

	@Test
	public void testGetPriceLevelFor_MEDIUM() throws Exception {
		date = MockObjectFactory.getMockDate(IntegerConstants.MEDIUM_COST_TIME.getInt());
		PriceLevelTypes priceLevel = priceLevels.getPriceLevelFor(date);

		assertEquals(priceLevel, PriceLevelTypes.MEDIUM);
	}

	@Test
	public void testGetPriceLevelFor_HIGH() throws Exception {
		date = MockObjectFactory.getMockDate(IntegerConstants.HIGH_COST_TIME.getInt());
		PriceLevelTypes priceLevel = priceLevels.getPriceLevelFor(date);

		assertEquals(priceLevel, PriceLevelTypes.HIGH);
	}

}
