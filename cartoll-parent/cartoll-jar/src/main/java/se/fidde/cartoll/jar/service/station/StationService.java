package se.fidde.cartoll.jar.service.station;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import se.fidde.cartoll.jar.domain.station.Station;

/**
 * @author fidde Service for stations
 */
public interface StationService {

    Station getStation(long id) throws IOException, SQLException;

    Collection<Station> getAllStations();

    long addStation(Station station);

    boolean removeStation(long id) throws IOException, SQLException;

    long updateStation(Station station);

    boolean removeAllStations();

}
