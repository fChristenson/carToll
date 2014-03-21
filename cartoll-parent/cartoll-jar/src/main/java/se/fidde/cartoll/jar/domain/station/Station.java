package se.fidde.cartoll.jar.domain.station;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import se.fidde.cartoll.jar.util.constants.JarStringConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde Stations are placed around some area and if a vehicle passes a
 *         station a passing is logged
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllStations", query = "SELECT s FROM Station s"),
        @NamedQuery(name = "removeAllStations", query = "DELETE FROM Station") })
public class Station {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    public Station() {
    }

    public Station(String name) {
        ValidationTools.isNull(name);

        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Station getEmptyStation() throws IOException {
        return new Station(JarStringConstants.EMPTY_NAME.toString());
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
