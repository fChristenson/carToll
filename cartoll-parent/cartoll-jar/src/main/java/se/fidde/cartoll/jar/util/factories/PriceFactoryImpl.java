package se.fidde.cartoll.jar.util.factories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

import se.fidde.cartoll.jar.domain.price.CarPrice;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.price.PriceLevelTypes;
import se.fidde.cartoll.jar.domain.price.PriceLoaderType;
import se.fidde.cartoll.jar.domain.price.TaxiPrice;
import se.fidde.cartoll.jar.domain.price.TruckPrice;
import se.fidde.cartoll.jar.domain.vehicle.Taxi;
import se.fidde.cartoll.jar.domain.vehicle.Truck;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde Price strategy factory for getting a vehicles passing price
 * 
 */
public class PriceFactoryImpl implements PriceFactory {
	private Logger log = Logger.getLogger(PriceFactoryImpl.class);

	public Currency getPriceFor(Vehicle vehicle, Date date) throws IOException, NullPointerException {
		ValidationTools.isNull(vehicle, date);

		log.debug(String.format("getting pricelevel for: %s on date: %s", vehicle, date));
		PriceLevelTypes priceLevel = getPriceLevel(date);

		log.debug("pricelevel found was: " + priceLevel);
		return getCalculationStrategyFor(vehicle, priceLevel);
	}

	private PriceLevelTypes getPriceLevel(Date date) throws IOException, FileNotFoundException {
		PriceLevelTypes priceLevel = null;
		try {
			priceLevel = PriceLoaderFactory.getPriceLevelLoader(PriceLoaderType.DATABASE).getPriceLevelFor(date);

		} catch (Exception e) {
			priceLevel = PriceLoaderFactory.getPriceLevelLoader(PriceLoaderType.PROPERTIES).getPriceLevelFor(date);
		}

		return priceLevel;
	}

	private Currency getCalculationStrategyFor(Vehicle vehicle, PriceLevelTypes priceLevel)
			throws FileNotFoundException, IOException {
		ValidationTools.assertNotNull(vehicle, priceLevel);

		switch (vehicle.getType()) {

		case EMPTY:
			log.debug(vehicle + " returns empty price");
			return new Currency();

		case CAR:
			log.debug(vehicle + " returns price for car");
			return new CarPrice(priceLevel).getPrice();

		case TAXI:
			log.debug(vehicle + " returns price for taxi");
			return new TaxiPrice(priceLevel, (Taxi) vehicle).getPrice();

		case TRUCK:
			log.debug(vehicle + " returns price for truck");
			return new TruckPrice(priceLevel, (Truck) vehicle).getPrice();

		default:
			throw new NullPointerException(vehicle + " is not a valid vehicle!");
		}
	}
}
