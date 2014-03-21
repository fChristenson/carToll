package se.fidde.cartoll.jar.service.passing;

import java.io.IOException;
import java.util.Collection;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;

/**
 * @author fidde Service for passings
 */
public interface PassingService {

	Passing getPassing(long id) throws IOException;

	long addPassing(Passing passing) throws NullPointerException, IOException;

	boolean removePassing(long id) throws IOException;

	long updatePassing(Passing passing);

	int getTotalPassings();

	Currency getTotalProfit();

	int getTotalPassingsForStation(Station station);

	Currency getTotalProfitForStation(Station station);

	Currency getTotalProfitFor(Vehicle vehicle);

	int getTotalPassingsFor(Vehicle vehicle);

	boolean removeAllPassings();

	Collection<Passing> getAllPassingsForStation(Station station);

}
