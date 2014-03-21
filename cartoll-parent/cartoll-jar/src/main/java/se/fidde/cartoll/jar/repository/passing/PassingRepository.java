package se.fidde.cartoll.jar.repository.passing;

import java.io.IOException;
import java.util.Collection;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;

/**
 * @author fidde Repository for passings
 */
public interface PassingRepository {

	Passing getPassing(long id) throws IOException;

	long addPassing(Passing passing);

	boolean removePassing(long id) throws IOException;

	long updatePassing(Passing passing);

	int getTotalPassings();

	Currency getTotalProfit();

	Currency getTotalProfitForStation(Station station);

	int getTotalPassingsForStation(Station station);

	Currency getTotalProfitForVehicle(Vehicle vehicle);

	int getTotalPassingsForVehicle(Vehicle vehicle);

	boolean removeAllPassings();

	Collection<Passing> getAllPassingsForStation(Station station);

}
