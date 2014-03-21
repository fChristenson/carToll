package se.fidde.cartoll.jar.domain.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde entity representing a taxi
 */
@Entity
@DiscriminatorValue(value = "taxi")
public class Taxi extends Car {

	private EnviromentFriendly isEnviromentFriendly;

	public Taxi() {
	}

	public Taxi(String regNumber, VehicleTypes type, Owner owner, EnviromentFriendly isEnviromentFriendly) {
		super(regNumber, type, owner);
		ValidationTools.isNull(isEnviromentFriendly);

		this.setIsEnviromentFriendly(isEnviromentFriendly);
	}

	public boolean isEnviromentFriendly() {
		if (getIsEnviromentFriendly() == EnviromentFriendly.TRUE)
			return true;

		return false;
	}

	public EnviromentFriendly getIsEnviromentFriendly() {
		return isEnviromentFriendly;
	}

	public void setIsEnviromentFriendly(EnviromentFriendly isEnviromentFriendly) {
		this.isEnviromentFriendly = isEnviromentFriendly;
	}
}
