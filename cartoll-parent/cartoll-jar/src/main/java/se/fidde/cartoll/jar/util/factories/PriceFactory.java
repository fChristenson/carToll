package se.fidde.cartoll.jar.util.factories;

import java.io.IOException;
import java.util.Date;

import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;

public interface PriceFactory {

	Currency getPriceFor(Vehicle vehicle, Date date) throws IOException, NullPointerException;

}
