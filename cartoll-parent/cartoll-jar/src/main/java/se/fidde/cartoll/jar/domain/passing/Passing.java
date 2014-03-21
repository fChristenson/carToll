package se.fidde.cartoll.jar.domain.passing;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import se.fidde.cartoll.jar.domain.owner.Address;
import se.fidde.cartoll.jar.domain.owner.Name;
import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.price.CurrencyTypes;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Car;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.domain.vehicle.VehicleTypes;
import se.fidde.cartoll.jar.util.constants.JarStringConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde A passing is created when a vehicle passes a station and it
 *         calculates the price for passing the toll
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "getTotalProfit", query = "SELECT sum(p.cost) FROM Passing p"),
		@NamedQuery(name = "getTotalPassings", query = "SELECT count(p) FROM Passing p"),
		@NamedQuery(name = "getTotalPassingsForStation", query = "SELECT count(p) FROM Passing p WHERE p.station = :stationId"),
		@NamedQuery(name = "getTotalPassingsForVehicle", query = "SELECT count(p) FROM Passing p WHERE p.vehicle = :vehicleId"),
		@NamedQuery(name = "clearPassings", query = "DELETE FROM Passing"),
		@NamedQuery(name = "getTotalProfitForStation", query = "SELECT sum(p.cost) FROM Passing p WHERE p.station = :stationId"),
		@NamedQuery(name = "getTotalProfitForVehicle", query = "SELECT sum(p.cost) FROM Passing p WHERE p.vehicle = :vehicleId"),
		@NamedQuery(name = "getAllPassingsForStation", query = "SELECT p FROM Passing p WHERE p.station = :stationId") })
public class Passing {
	@Id
	@GeneratedValue
	private long id;
	private long date;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Vehicle vehicle;

	@ManyToOne(cascade = CascadeType.ALL)
	private Station station;

	private BigInteger cost;
	private CurrencyTypes type;

	public Passing() {
	}

	public Passing(Vehicle vehicle, Station station, Date date) throws IOException {
		ValidationTools.isNull(vehicle, station, date);

		this.vehicle = vehicle;
		this.date = date.getTime();
		this.station = station;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("ID: ").append(getId());
		stringBuilder.append(", DATE: ").append(getDate());
		stringBuilder.append(", STATION: ").append(getStation());
		stringBuilder.append(", CAR: ").append(getVehicle());
		stringBuilder.append(", COST: ").append(getCost());

		return stringBuilder.toString();
	}

	public static Passing getEmptyPassing() throws IOException {
		Name name = new Name(JarStringConstants.EMPTY_NAME.toString(), JarStringConstants.EMPTY_NAME.toString());
		Address address = new Address(JarStringConstants.EMPTY_NAME.toString(),
				JarStringConstants.EMPTY_ZIPCODE.toString(), JarStringConstants.EMPTY_NAME.toString());

		Owner owner = new Owner(name, address);
		Vehicle vehicle = new Car(JarStringConstants.EMPTY_REG_NUMBER.toString(), VehicleTypes.CAR, owner);

		Station station = new Station(JarStringConstants.EMPTY_NAME.toString());
		Date date = new Date(0);

		return new Passing(vehicle, station, date);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getDate() {
		return new Date(date);
	}

	public void setDate(long date) {
		this.date = date;
	}

	public Station getStation() {
		Station result = new Station(station.getName());
		station.setId(station.getId());
		return result;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Currency getCost() {
		if (cost == null || type == null)
			return new Currency();
		return new Currency(cost, type);
	}

	public void setCost(Currency cost) {
		this.cost = cost.getAmount();
		type = cost.getType();
	}

}
