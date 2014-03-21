package se.fidde.cartoll.jar.domain.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import se.fidde.cartoll.jar.domain.owner.Owner;

/**
 * @author fidde entity representing a car
 */
@Entity
@DiscriminatorValue(value = "car")
public class Car extends Vehicle {

	public Car() {
	}

	public Car(String regNumber, VehicleTypes type, Owner owner) {
		super(regNumber, type, owner);
	}

}
