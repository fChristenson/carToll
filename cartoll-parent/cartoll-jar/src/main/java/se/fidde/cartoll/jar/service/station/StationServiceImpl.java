package se.fidde.cartoll.jar.service.station;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.repository.station.StationRepository;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;
    private Logger log = Logger.getLogger(StationServiceImpl.class);

    @Override
    public Station getStation(long id) throws IOException, SQLException {
        ValidationTools.isNull(id);
        log.debug("gets " + id);

        return stationRepository.getStation(id);
    }

    @Override
    public Collection<Station> getAllStations() {
        log.debug("gets all stations");
        return stationRepository.getAllStations();
    }

    @Override
    public long addStation(Station station) {
        ValidationTools.isNull(station);

        log.debug("adds " + station);
        return stationRepository.addStation(station);
    }

    @Override
    public boolean removeStation(long id) throws IOException, SQLException {
        ValidationTools.isNull(id);

        log.debug("removes " + id);
        return stationRepository.removeStation(id);
    }

    public void setRepository(StationRepository repository) {
        ValidationTools.isNull(repository);

        stationRepository = repository;
    }

    @Override
    public long updateStation(Station station) {
        ValidationTools.isNull(station);

        return stationRepository.updateStation(station);
    }

    @Override
    public boolean removeAllStations() {
        stationRepository.removeAllStations();
        return false;
    }

}
