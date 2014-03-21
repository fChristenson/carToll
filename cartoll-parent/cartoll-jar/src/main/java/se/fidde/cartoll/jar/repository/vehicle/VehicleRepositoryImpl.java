package se.fidde.cartoll.jar.repository.vehicle;

import java.io.IOException;
import java.util.Collection;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.repository.AbstractRepository;

public class VehicleRepositoryImpl extends AbstractRepository<Vehicle> implements VehicleRepository {

	private Logger log = Logger.getLogger(VehicleRepositoryImpl.class);

	@Transactional
	@Override
	public long addVehicle(Vehicle vehicle) {
		log.debug("add: " + vehicle);
		Vehicle result = add(vehicle);

		return result.getId();
	}

	@Transactional
	@Override
	public Vehicle getVehicle(long id) {
		log.debug("get: " + id);
		Vehicle vehicle = entityManager.find(Vehicle.class, id);

		return vehicle;
	}

	@Transactional
	@Override
	public boolean removeVehicle(long id) {
		log.debug("remove: " + id);
		Query query = entityManager.createNamedQuery("removeVehicle");

		query.setParameter("vehicleId", id);
		query.executeUpdate();

		return true;
	}

	@Transactional
	@Override
	public long updateVehicle(Vehicle vehicle) {
		log.debug("update: " + vehicle);
		Vehicle result = update(vehicle);

		return result.getId();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Vehicle> getAllVehicles() {
		Query query = entityManager.createNamedQuery("getAllVehicles");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Collection<Vehicle> getVehiclesForOwner(Owner owner) {
		Query query = entityManager.createNamedQuery("getVehiclesForOwner");
		query.setParameter("ownerId", owner);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	protected Collection<Vehicle> getAll() {
		Query query = entityManager.createNamedQuery("getAllVehicles");
		return query.getResultList();
	}

	@Transactional
	@Override
	protected Vehicle get(long id) throws IOException {
		log.debug("get vehicle: " + id);
		Vehicle vehicle = entityManager.find(Vehicle.class, id);

		log.debug("vehicle found was: " + vehicle);
		if (vehicle == null)
			return Vehicle.getEmptyVehicle();

		return vehicle;
	}

}
