package se.fidde.cartoll.jar.service.passing;

import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.repository.passing.PassingRepository;
import se.fidde.cartoll.jar.util.factories.PriceFactory;
import se.fidde.cartoll.jar.util.factories.PriceFactoryImpl;

@Service
public class PassingServiceImpl implements PassingService {

	private static PriceFactory priceFactory;
	@Autowired
	private PassingRepository passingRepository;
	private Logger log = Logger.getLogger(PassingServiceImpl.class);

	@Override
	public Passing getPassing(long id) throws IOException {
		log.debug("getting passing with id: " + id);
		return passingRepository.getPassing(id);
	}

	@Override
	public long addPassing(Passing passing) throws NullPointerException, IOException {
		if (priceFactory == null)
			priceFactory = new PriceFactoryImpl();

		Currency cost = priceFactory.getPriceFor(passing.getVehicle(), passing.getDate());
		passing.setCost(cost);
		log.debug("adding passing: " + passing);

		return passingRepository.addPassing(passing);
	}

	@Override
	public boolean removePassing(long id) throws IOException {
		log.debug("removing passing with id: " + id);
		return passingRepository.removePassing(id);
	}

	public void setRepository(PassingRepository passingRepository) {
		this.passingRepository = passingRepository;
	}

	@Override
	public long updatePassing(Passing passing) {
		return passingRepository.updatePassing(passing);
	}

	@Override
	public int getTotalPassings() {
		return passingRepository.getTotalPassings();
	}

	@Override
	public Currency getTotalProfit() {
		return passingRepository.getTotalProfit();
	}

	@Override
	public int getTotalPassingsForStation(Station station) {
		return passingRepository.getTotalPassingsForStation(station);
	}

	@Override
	public Currency getTotalProfitForStation(Station station) {
		return passingRepository.getTotalProfitForStation(station);
	}

	@Override
	public Currency getTotalProfitFor(Vehicle vehicle) {
		return passingRepository.getTotalProfitForVehicle(vehicle);
	}

	@Override
	public int getTotalPassingsFor(Vehicle vehicle) {
		return passingRepository.getTotalPassingsForVehicle(vehicle);
	}

	@Override
	public boolean removeAllPassings() {
		return passingRepository.removeAllPassings();
	}

	@Override
	public Collection<Passing> getAllPassingsForStation(Station station) {
		return passingRepository.getAllPassingsForStation(station);
	}

}
