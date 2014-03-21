package se.fidde.cartoll.jar.repository.station;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import se.fidde.cartoll.jar.domain.station.Station;

/**
 * @author fidde Repository for stations
 */
public interface StationRepository {

    Station getStation(long id) throws IOException, SQLException;

    Collection<Station> getAllStations();

    boolean removeStation(long id) throws IOException, SQLException;

    long addStation(Station station);

    long updateStation(Station station);

    boolean removeAllStations();

}
