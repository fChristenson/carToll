package se.fidde.cartoll.jar.domain.price;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;

public class CurrencyTest {

	private Currency currency;

	@Before
	public void setUp() {
		currency = new Currency(new BigInteger("1"), CurrencyTypes.SEK);
	}

	@After
	public void tearDown() {
		currency = null;
	}

	@Test
	public void testGetCurrency() {
		assertNotNull("Currency is not null", currency);

		double amount = currency.getAmount().intValue();
		assertTrue("Amount is correct", amount == IntegerConstants.MOCK_CURRENCY.getInt());

		CurrencyTypes type = currency.getType();
		assertEquals("Type of currency is correct", type, CurrencyTypes.SEK);
	}

	@Test
	public void testGetCurrency_invalid() {

		double amount = currency.getAmount().intValue();
		assertFalse("Amount is not correct", amount == IntegerConstants.MOCK_CURRENCY.getInt() + 1);
	}

	@Test
	public void testAddCurrency() throws Exception {
		Currency actual = currency.addCost(MockObjectFactory.getEasyMockCurrency());
		assertTrue("Adding Currency works", actual.getAmount().intValue() > 0);

	}

	@Test
	public void testAddCurrency_invalid() throws Exception {
		try {
			currency.addCost(new Currency(new BigInteger("1"), CurrencyTypes.DOLLAR));
			fail();

		} catch (Exception e) {
			assertTrue("Adding Currency of wrong type does not work", e instanceof IllegalArgumentException);
		}

		try {
			currency.addCost(new Currency(new BigInteger("-1"), CurrencyTypes.SEK));
			fail();

		} catch (Exception e) {
			assertTrue("Adding Currency of negative value does not work", e instanceof IllegalArgumentException);
		}

	}
}
