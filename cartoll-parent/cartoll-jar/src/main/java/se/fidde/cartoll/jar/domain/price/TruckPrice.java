package se.fidde.cartoll.jar.domain.price;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.log4j.Logger;

import se.fidde.cartoll.jar.domain.vehicle.Truck;
import se.fidde.cartoll.jar.util.constants.JarStringConstants;
import se.fidde.cartoll.jar.util.constants.RelativeUrl;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde Strategy for calculating passing price for a truck
 */
public class TruckPrice extends CarPrice {

	private BigInteger extraCost;
	private double weightLimit;
	private Truck truck;
	private Logger log = Logger.getLogger(TruckPrice.class);

	/**
	 * Returns the same calculation as CarPrice plus extra cost for Trucks. E.g
	 * a truck with a weight less than the weightlimit would get no extra cost.
	 * 
	 * @param priceLevel
	 * @param truck
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public TruckPrice(PriceLevelTypes priceLevel, Truck truck) throws FileNotFoundException, IOException {
		super(priceLevel);
		ValidationTools.isNull(truck);

		this.truck = truck;
		loadProperties(RelativeUrl.TRUCK_PROPERTIES.toString());
		priceMap = getPrices();
	}

	@Override
	public Currency getPrice() throws FileNotFoundException, IOException {
		double extraCost = getExtraCost().intValue();
		double weightLimit = getWeightlimit();

		log.debug("getting " + JarStringConstants.EXTRA_COST.toString() + " for weightLimig: " + weightLimit
				+ " and cost: " + extraCost);
		extraCost = calculateFinalCost(extraCost, weightLimit);

		log.debug(JarStringConstants.EXTRA_COST.toString() + " comes to: " + extraCost);

		BigInteger price = priceMap.get(priceLevel);
		price = price.add(new BigInteger(String.valueOf((int) extraCost)));

		return new Currency(price, CurrencyTypes.SEK);
	}

	private double calculateFinalCost(double extraCost, double weightLimit) {
		double truckWeight = truck.getWeight().getAmount() - weightLimit;
		truckWeight = Math.floor(truckWeight);

		if (truckWeight <= 0)
			return 0;

		return extraCost * truckWeight;
	}

	private double getWeightlimit() {
		if (weightLimit == 0) {
			String weightLimitString = properties.getProperty(JarStringConstants.WEIGHT_LIMIT.toString());
			log.debug(JarStringConstants.WEIGHT_LIMIT.toString() + " was found to be " + weightLimitString);

			if (weightLimitString == null)
				throw new NullPointerException("weightlimit not found, check properties file");

			return Double.parseDouble(weightLimitString);
		}
		log.debug(JarStringConstants.WEIGHT_LIMIT.toString() + " is:" + weightLimit);
		return weightLimit;
	}

	@Override
	protected BigInteger getExtraCost() throws FileNotFoundException, IOException {
		if (extraCost == null) {
			String extraCostString = properties.getProperty(JarStringConstants.EXTRA_COST.toString());
			log.debug(JarStringConstants.EXTRA_COST.toString() + " was found to be " + extraCostString);

			if (extraCostString == null)
				throw new NullPointerException("truck extra cost was not found in properties file!");
			else {
				log.debug("returns: " + extraCost);
				extraCost = new BigInteger(extraCostString);

				return extraCost;
			}
		}

		log.debug(JarStringConstants.EXTRA_COST.toString() + " is: " + extraCost);
		return extraCost;
	}
}
