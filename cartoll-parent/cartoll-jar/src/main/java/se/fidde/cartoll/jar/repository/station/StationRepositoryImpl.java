package se.fidde.cartoll.jar.repository.station;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.repository.AbstractRepository;

public class StationRepositoryImpl extends AbstractRepository<Station>
        implements StationRepository {

    private Logger log = Logger.getLogger(StationRepositoryImpl.class);

    @Transactional
    @Override
    public Station getStation(long id) throws IOException, SQLException {
        return get(id);
    }

    @Transactional
    @Override
    public boolean removeStation(long id) throws IOException, SQLException {
        Station station = get(id);

        return remove(station);
    }

    @Transactional
    @Override
    public long addStation(Station station) {
        log.debug("add station: " + station);
        Station result = add(station);

        return result.getId();
    }

    @Transactional
    @Override
    public long updateStation(Station station) {
        log.debug("update station: " + station);
        Station result = update(station);

        return result.getId();
    }

    @Transactional
    @Override
    public Collection<Station> getAllStations() {
        return getAll();
    }

    @Transactional
    @Override
    protected Station get(long id) throws IOException, SQLException {
        log.debug("get station: " + id);

        Station station = entityManager.find(Station.class, id);
        log.debug("station found was: " + station);

        if (station == null)
            return Station.getEmptyStation();

        return station;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    protected Collection<Station> getAll() {
        log.debug("get all stations");
        if (entityManager == null)
            return Collections.emptySet();

        Query namedQuery = entityManager.createNamedQuery("getAllStations");

        return namedQuery.getResultList();
    }

    @Override
    public boolean removeAllStations() {
        entityManager.createNamedQuery("removeAllStations");
        return true;
    }

}
