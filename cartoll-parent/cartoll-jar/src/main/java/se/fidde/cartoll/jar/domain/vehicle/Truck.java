package se.fidde.cartoll.jar.domain.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde entity representing a truck
 */
@Entity
@DiscriminatorValue(value = "truck")
public class Truck extends Car {

	private double weightAmount;
	private UnitType unit;

	public Truck() {
	}

	public Truck(String regNumber, VehicleTypes type, Owner owner, Weight weight) {
		super(regNumber, type, owner);
		ValidationTools.isNull(weight);

		weightAmount = weight.getAmount();
		unit = weight.getUnit();
	}

	public Weight getWeight() {
		return new Weight(weightAmount, unit);
	}

	public void setWeight(Weight weight) {
		weightAmount = weight.getAmount();
		unit = weight.getUnit();
	}

}
