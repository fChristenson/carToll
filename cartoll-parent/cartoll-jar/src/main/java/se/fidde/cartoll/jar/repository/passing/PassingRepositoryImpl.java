package se.fidde.cartoll.jar.repository.passing;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.price.CurrencyTypes;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.repository.AbstractRepository;

public class PassingRepositoryImpl extends AbstractRepository<Passing> implements PassingRepository {

	private Logger log = Logger.getLogger(PassingRepositoryImpl.class);

	@Transactional
	@Override
	public Passing getPassing(long id) throws IOException {
		return get(id);
	}

	@Transactional
	@Override
	public long addPassing(Passing passing) {
		log.debug("add passing: " + passing);
		Passing result = add(passing);

		return result.getId();
	}

	@Transactional
	@Override
	public boolean removePassing(long id) throws IOException {
		Passing passing = get(id);

		return remove(passing);
	}

	@Transactional
	@Override
	public long updatePassing(Passing passing) {
		log.debug("update passing: " + passing);
		Passing result = update(passing);

		return result.getId();
	}

	@Transactional
	@Override
	public int getTotalPassings() {
		Query query = entityManager.createNamedQuery("getTotalPassings");
		Long result = (Long) query.getSingleResult();
		log.debug("total passings found = " + result);

		return result.intValue();
	}

	@Transactional
	@Override
	public Currency getTotalProfit() {
		Query query = entityManager.createNamedQuery("getTotalProfit");

		return getProfit(query);
	}

	@Transactional
	@Override
	public Currency getTotalProfitForStation(Station station) {
		log.debug("get: " + station + " with id: " + station);

		Query query = entityManager.createNamedQuery("getTotalProfitForStation");
		query.setParameter("stationId", station);

		return getProfit(query);
	}

	@Transactional
	@Override
	public int getTotalPassingsForStation(Station station) {
		log.debug("get: " + station + " with id: " + station);
		Query query = entityManager.createNamedQuery("getTotalPassingsForStation");
		query.setParameter("stationId", station);

		Long result = (Long) query.getSingleResult();
		log.debug("totalPassings found for station: " + station + " was " + result);

		return result.intValue();
	}

	@Transactional
	@Override
	public Currency getTotalProfitForVehicle(Vehicle vehicle) {
		log.debug("get: " + vehicle + " with id: " + vehicle);

		Query query = entityManager.createNamedQuery("getTotalProfitForVehicle");
		query.setParameter("vehicleId", vehicle);

		return getProfit(query);
	}

	@Transactional
	@Override
	public int getTotalPassingsForVehicle(Vehicle vehicle) {
		log.debug("get: " + vehicle + " with id: " + vehicle);
		Query query = entityManager.createNamedQuery("getTotalPassingsForVehicle");

		query.setParameter("vehicleId", vehicle);
		Long result = (Long) query.getSingleResult();
		log.debug("totalPassings found for vehicle: " + vehicle + " was " + result);

		return result.intValue();
	}

	@Transactional
	@Override
	public boolean removeAllPassings() {
		log.debug("clearing all passings");
		Query query = entityManager.createNamedQuery("clearPassings");

		query.executeUpdate();
		log.debug("all passings cleared");

		return true;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Passing> getAllPassingsForStation(Station station) {
		log.debug("getting all passings for station: " + station);

		Query query = entityManager.createNamedQuery("getAllPassingsForStation");
		query.setParameter("stationId", station);

		return query.getResultList();
	}

	private Currency getProfit(Query query) {
		BigInteger sum = null;
		try {
			sum = (BigInteger) query.getSingleResult();
			log.debug("sum found: " + sum);

			if (sum == null)
				return new Currency();

			return new Currency(sum, CurrencyTypes.SEK);

		} catch (Exception e) {

			return new Currency();
		}
	}

	@Transactional
	@Override
	protected Passing add(Passing obj) {
		Passing merge = entityManager.merge(obj);

		return merge;
	}

	@Transactional
	@Override
	protected Passing get(long id) throws IOException {
		log.debug("get passing: " + id);
		Passing passing = entityManager.find(Passing.class, id);

		log.debug("passing found was: " + passing);
		if (passing == null) {
			log.debug("returns empty passing");

			return Passing.getEmptyPassing();
		}

		return passing;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	protected Collection<Passing> getAll() {
		log.debug("get all passings");
		Query namedQuery = entityManager.createNamedQuery("getAllPassings");

		return namedQuery.getResultList();
	}

}
