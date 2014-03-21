package se.fidde.cartoll.jar.repository.vehicle;

import java.util.Collection;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;

/**
 * @author fidde Repository for vehicles
 */
public interface VehicleRepository {

	long addVehicle(Vehicle vehicle);

	Vehicle getVehicle(long id);

	boolean removeVehicle(long id);

	long updateVehicle(Vehicle vehicle);

	Collection<Vehicle> getAllVehicles();

	Collection<Vehicle> getVehiclesForOwner(Owner owner);

}
