package se.fidde.cartoll.jar.domain.vehicle;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import se.fidde.cartoll.jar.domain.owner.Address;
import se.fidde.cartoll.jar.domain.owner.Name;
import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.util.constants.JarStringConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde Vehicle represents a vehicle that will have to pay a fee for
 *         passing a station
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicleType", discriminatorType = DiscriminatorType.STRING, length = 10)
@NamedQueries({ @NamedQuery(name = "getAllVehicles", query = "SELECT v FROM Vehicle v"),
		@NamedQuery(name = "removeVehicle", query = "DELETE FROM Vehicle WHERE id = :vehicleId"),
		@NamedQuery(name = "getVehicle", query = "SELECT v FROM Vehicle v WHERE v.id = :vehicleId"),
		@NamedQuery(name = "getVehiclesForOwner", query = "SELECT v FROM Vehicle v WHERE v.owner = :ownerId") })
public abstract class Vehicle {

	@Id
	@GeneratedValue
	protected long id;

	private String regNumber;
	private VehicleTypes type;

	@ManyToOne(cascade = CascadeType.ALL)
	private Owner owner;

	@Transient
	private String displayName;

	public Vehicle() {
	}

	public Vehicle(String regNumber, VehicleTypes type, Owner owner) {
		ValidationTools.isNull(regNumber, owner);

		this.regNumber = regNumber;
		this.type = type;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return getRegNumber();
	}

	public static Vehicle getEmptyVehicle() {
		Address address = new Address(JarStringConstants.EMPTY_NAME.toString(),
				JarStringConstants.EMPTY_ZIPCODE.toString(), JarStringConstants.EMPTY_NAME.toString());

		Name name = new Name(JarStringConstants.EMPTY_NAME.toString(), JarStringConstants.EMPTY_NAME.toString());
		Owner owner = new Owner(name, address);

		return new Car(JarStringConstants.EMPTY_REG_NUMBER.toString(), VehicleTypes.CAR, owner);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDisplayName(String name) {
		displayName = name;
	}

	public String getDisplayName() {
		if (displayName == null)
			return String.format("%s %s", regNumber, type);
		return displayName;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public VehicleTypes getType() {
		return type;
	}

	public void setType(VehicleTypes type) {
		this.type = type;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

}
