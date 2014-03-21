package se.fidde.cartoll.jar.domain.vehicle;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.util.constants.ValidationStringConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde object for holding data that identifies a vehicle
 */
public class VehicleMockData {

	private String regNumber;
	private VehicleTypes type;
	private Owner owner;

	public VehicleMockData(String regNumber, VehicleTypes type, Owner owner) {
		ValidationTools.isNull(regNumber, type, owner);
		isRegNumber(regNumber);

		this.regNumber = regNumber;
		this.type = type;
		this.owner = owner;
	}

	public static void isRegNumber(String regNumber) {
		if (regNumber.matches(ValidationStringConstants.REG_NUMBER_REGEX.toString()))
			return;

		throw new IllegalArgumentException(regNumber + " is not a valid format for regNumbers!");
	}

	public String getRegNumber() {
		return regNumber;
	}

	public VehicleTypes getType() {
		return type;
	}

	public Owner getOwner() {
		return new Owner(owner.getFullName(), owner.getFullAddress());
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public void setType(VehicleTypes type) {
		this.type = type;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
