package se.fidde.cartoll.jar.domain.price;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.log4j.Logger;

import se.fidde.cartoll.jar.domain.vehicle.Taxi;
import se.fidde.cartoll.jar.util.constants.JarStringConstants;
import se.fidde.cartoll.jar.util.constants.RelativeUrl;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde Strategy for calculating passing price for a taxi
 */
public class TaxiPrice extends CarPrice {

	private Taxi taxi;
	private Logger log = Logger.getLogger(TaxiPrice.class);

	/**
	 * Returns same calculation as CarPrice plus extra cost for Taxi. E.g a
	 * certified Taxi would not get any extra cost.
	 * 
	 * @param priceLevel
	 * @param taxi
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public TaxiPrice(PriceLevelTypes priceLevel, Taxi taxi) throws FileNotFoundException, IOException {
		super(priceLevel);
		ValidationTools.isNull(taxi);

		this.taxi = taxi;
		loadProperties(RelativeUrl.TAXI_PROPERTIES.toString());
		priceMap = getPrices();
	}

	@Override
	public Currency getPrice() throws FileNotFoundException, IOException {
		log.debug("getting pricelevel: " + priceLevel);
		BigInteger price = priceMap.get(priceLevel);

		log.debug("adding " + JarStringConstants.EXTRA_COST.toString() + " to price: " + price);
		price = price.add(getExtraCost());

		log.debug("returns: " + price + " as currency");
		return new Currency(price, CurrencyTypes.SEK);
	}

	@Override
	protected BigInteger getExtraCost() throws FileNotFoundException, IOException {
		if (taxi.isEnviromentFriendly() || priceLevel == PriceLevelTypes.FREE) {
			log.debug("returns 0");

			return new BigInteger("0");
		}

		log.debug("getting " + JarStringConstants.EXTRA_COST.toString());
		String extraCost = properties.getProperty(JarStringConstants.EXTRA_COST.toString());

		log.debug(JarStringConstants.EXTRA_COST.toString() + " was found to be " + extraCost);
		if (extraCost == null)
			throw new NullPointerException("extra cost not found in taxi properties file!");

		return new BigInteger(extraCost);
	}
}
