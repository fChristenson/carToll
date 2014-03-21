package se.fidde.cartoll.jar.service.vehicle;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.repository.vehicle.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepo;
	private Logger log = Logger.getLogger(VehicleServiceImpl.class);

	@Override
	public long addVehicle(Vehicle vehicle) {
		log.debug("adds " + vehicle);
		return vehicleRepo.addVehicle(vehicle);
	}

	@Override
	public Vehicle getVehicle(long id) {
		log.debug("gets " + id);
		return vehicleRepo.getVehicle(id);
	}

	@Override
	public boolean removeVehicle(long id) {
		log.debug("removes " + id);
		return vehicleRepo.removeVehicle(id);
	}

	public void setRepository(VehicleRepository vehicleRepository) {
		vehicleRepo = vehicleRepository;
	}

	@Override
	public long updateVehicle(Vehicle vehicle) {
		return vehicleRepo.updateVehicle(vehicle);
	}

	@Override
	public Collection<Vehicle> getAllVehicles() {
		return vehicleRepo.getAllVehicles();
	}

	@Override
	public Collection<Vehicle> getVehiclesForOwner(Owner owner) {
		return vehicleRepo.getVehiclesForOwner(owner);
	}
}
