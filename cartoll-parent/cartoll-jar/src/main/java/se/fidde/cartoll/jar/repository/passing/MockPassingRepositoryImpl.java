package se.fidde.cartoll.jar.repository.passing;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.price.CurrencyTypes;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

public class MockPassingRepositoryImpl implements PassingRepository {

	private Map<Long, Passing> passings;

	public MockPassingRepositoryImpl() throws IOException {
		passings = new ConcurrentHashMap<>();
	}

	@Override
	public Passing getPassing(long id) throws IOException {
		Passing passing = passings.get(id);
		if (passing == null)
			return Passing.getEmptyPassing();

		return passing;
	}

	@Override
	public long addPassing(Passing passing) {
		ValidationTools.isNull(passing);

		try {
			passing.setId(passings.size());
			passings.put(passing.getId(), passing);

			return passing.getId();

		} catch (Exception e) {

			return IntegerConstants.EMPTY_ID.getInt();
		}
	}

	@Override
	public boolean removePassing(long id) {
		if (passings.remove(id) == null)
			return false;

		return true;
	}

	@Override
	public long updatePassing(Passing passing) {
		try {
			passings.put(passing.getId(), passing);

			return passing.getId();

		} catch (Exception e) {

			return IntegerConstants.EMPTY_ID.getInt();
		}
	}

	@Override
	public int getTotalPassings() {
		return passings.size();
	}

	@Override
	public Currency getTotalProfit() {
		int result = 0;
		for (Passing passing : passings.values())
			result += passing.getCost().getAmount().intValue();

		return new Currency(new BigInteger(String.valueOf(result)), CurrencyTypes.SEK);
	}

	@Override
	public Currency getTotalProfitForStation(Station station) {
		int result = 0;
		for (Passing passing : passings.values())

			if (passing.getStation().getId() == station.getId())
				result += passing.getCost().getAmount().intValue();

		return new Currency(new BigInteger(String.valueOf(result)), CurrencyTypes.SEK);
	}

	@Override
	public int getTotalPassingsForStation(Station station) {
		int result = 0;
		for (Passing passing : passings.values())

			if (passing.getStation().getId() == station.getId())
				++result;

		return result;
	}

	@Override
	public Currency getTotalProfitForVehicle(Vehicle vehicle) {
		int result = 0;
		for (Passing passing : passings.values())

			if (passing.getVehicle().getId() == vehicle.getId())
				result += passing.getCost().getAmount().intValue();

		return new Currency(new BigInteger(String.valueOf(result)), CurrencyTypes.SEK);
	}

	@Override
	public int getTotalPassingsForVehicle(Vehicle vehicle) {
		int result = 0;
		for (Passing passing : passings.values())

			if (passing.getVehicle().getId() == vehicle.getId())
				++result;

		return result;
	}

	@Override
	public boolean removeAllPassings() {
		try {
			passings.clear();

			return true;

		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public Collection<Passing> getAllPassingsForStation(Station station) {
		HashSet<Passing> result = new HashSet<>();
		for (Passing passing : passings.values())

			if (passing.getStation().getId() == station.getId())
				result.add(passing);

		return result;
	}
}
