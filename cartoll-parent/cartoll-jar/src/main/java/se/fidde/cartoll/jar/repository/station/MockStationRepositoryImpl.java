package se.fidde.cartoll.jar.repository.station;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

public class MockStationRepositoryImpl implements StationRepository {

    private Map<Long, Station> stations;

    public MockStationRepositoryImpl() throws IOException {
        stations = new ConcurrentHashMap<>();
    }

    @Override
    public Station getStation(long id) throws IOException {
        ValidationTools.isNull(id);

        Station station = stations.get(id);
        if (station == null)
            return Station.getEmptyStation();

        return station;
    }

    @Override
    public Set<Station> getAllStations() {
        return new HashSet<>(stations.values());
    }

    @Override
    public boolean removeStation(long id) {
        ValidationTools.isNull(id);

        Station station = stations.remove(id);
        if (station == null)
            return false;

        return true;
    }

    @Override
    public long addStation(Station station) {
        ValidationTools.isNull(station);

        try {
            station.setId(stations.size());
            stations.put(station.getId(), station);

            return station.getId();

        } catch (Exception e) {

            return IntegerConstants.EMPTY_ID.getInt();
        }
    }

    @Override
    public long updateStation(Station station) {
        ValidationTools.isNull(station);

        try {
            stations.put(station.getId(), station);
            return station.getId();

        } catch (Exception e) {

            return IntegerConstants.EMPTY_ID.getInt();
        }
    }

    @Override
    public boolean removeAllStations() {
        stations.clear();
        return true;
    }

}
