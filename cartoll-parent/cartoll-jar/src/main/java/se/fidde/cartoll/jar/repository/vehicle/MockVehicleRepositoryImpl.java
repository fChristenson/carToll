package se.fidde.cartoll.jar.repository.vehicle;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

public class MockVehicleRepositoryImpl implements VehicleRepository {

	private Map<String, Vehicle> vehicles;

	public MockVehicleRepositoryImpl() {
		vehicles = new ConcurrentHashMap<>();
	}

	@Override
	public long addVehicle(Vehicle vehicle) {
		ValidationTools.isNull(vehicle);

		try {
			vehicles.put(vehicle.getRegNumber(), vehicle);

			return vehicle.getId();

		} catch (Exception e) {

			return IntegerConstants.EMPTY_ID.getInt();
		}
	}

	@Override
	public Vehicle getVehicle(long id) {
		Vehicle vehicle = vehicles.get(id);

		if (vehicle == null)
			return Vehicle.getEmptyVehicle();

		return vehicle;
	}

	@Override
	public boolean removeVehicle(long id) {
		Vehicle remove = vehicles.remove(id);

		if (remove == null)
			return false;

		return true;
	}

	@Override
	public long updateVehicle(Vehicle vehicle) {
		try {
			vehicles.put(vehicle.getRegNumber(), vehicle);
			return vehicle.getId();

		} catch (Exception e) {

			return IntegerConstants.EMPTY_ID.getInt();
		}
	}

	@Override
	public Set<Vehicle> getAllVehicles() {
		return new HashSet<>(vehicles.values());
	}

	@Override
	public Collection<Vehicle> getVehiclesForOwner(Owner owner) {
		HashSet<Vehicle> result = new HashSet<>();
		for (Vehicle vehicle : vehicles.values()) {

			if (vehicle.getOwner().getId() == owner.getId())
				result.add(vehicle);
		}

		return result;
	}
}
