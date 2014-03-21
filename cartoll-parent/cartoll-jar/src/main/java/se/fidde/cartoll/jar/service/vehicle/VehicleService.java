package se.fidde.cartoll.jar.service.vehicle;

import java.util.Collection;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;

/**
 * @author fidde Service for vehicles
 */
public interface VehicleService {

	long addVehicle(Vehicle vehicle);

	Vehicle getVehicle(long id);

	boolean removeVehicle(long id);

	long updateVehicle(Vehicle vehicle);

	Collection<Vehicle> getAllVehicles();

	Collection<Vehicle> getVehiclesForOwner(Owner owner);

}
