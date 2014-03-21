package se.fidde.cartoll.jar.domain.price;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import se.fidde.cartoll.jar.util.constants.RelativeUrl;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde Strategy for car price calculation
 */
public class CarPrice {

	private Logger log = Logger.getLogger(CarPrice.class);
	protected PriceLevelTypes priceLevel;
	protected Map<PriceLevelTypes, BigInteger> priceMap;
	protected Properties properties;

	/**
	 * @param priceLevel
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CarPrice(PriceLevelTypes priceLevel) throws FileNotFoundException, IOException {
		ValidationTools.isNull(priceLevel);

		this.priceLevel = priceLevel;
		loadProperties(RelativeUrl.CAR_PROPERTIES.toString());
		priceMap = getPrices();
	}

	/**
	 * @return price for car passing
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Currency getPrice() throws FileNotFoundException, IOException {
		log.debug("getting price for: " + priceLevel);
		BigInteger price = priceMap.get(priceLevel);

		return new Currency(price, CurrencyTypes.SEK);
	}

	protected final void loadProperties(String url) throws FileNotFoundException, IOException {
		ValidationTools.assertNotNull(url);

		properties = new Properties();
		log.debug("loading properties from: " + url);
		InputStream stream = getClass().getResourceAsStream(url);
		properties.load(stream);
	}

	protected final Map<PriceLevelTypes, BigInteger> getPrices() {
		Map<PriceLevelTypes, BigInteger> result = new ConcurrentHashMap<PriceLevelTypes, BigInteger>();

		for (PriceLevelTypes priceLevel : PriceLevelTypes.values()) {
			log.debug("parsing: " + priceLevel);
			parsePrices(result, priceLevel);
		}

		return result;
	}

	private void parsePrices(Map<PriceLevelTypes, BigInteger> result, PriceLevelTypes priceLevel) {
		ValidationTools.assertNotNull(result, priceLevel);

		log.debug("getting price for: " + priceLevel);
		String cost = properties.getProperty(priceLevel.toString());
		log.debug("price is: " + cost);

		if (cost == null)
			throw new NullPointerException(priceLevel + " was not found in properties file!");
		result.put(priceLevel, new BigInteger(cost));
	}

	protected BigInteger getExtraCost() throws FileNotFoundException, IOException {
		return new BigInteger("0");
	}

}
