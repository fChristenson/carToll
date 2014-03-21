package se.fidde.cartoll.jar.util.factories;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.vehicle.Car;
import se.fidde.cartoll.jar.domain.vehicle.EnviromentFriendly;
import se.fidde.cartoll.jar.domain.vehicle.Taxi;
import se.fidde.cartoll.jar.domain.vehicle.Truck;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.domain.vehicle.VehicleTypes;
import se.fidde.cartoll.jar.domain.vehicle.Weight;

/**
 * @author fidde Vehicle factory
 * 
 */
public class VehicleFactory {

	public static Vehicle createCar(String regNumber, VehicleTypes type, Owner owner) {
		return new Car(regNumber, type, owner);
	}

	public static Vehicle createTaxi(String regNumber, VehicleTypes type, Owner owner,
			EnviromentFriendly isEnviromentFriendly) {
		return new Taxi(regNumber, type, owner, isEnviromentFriendly);
	}

	public static Vehicle createTruck(String regNumber, VehicleTypes type, Owner owner, Weight weight) {
		return new Truck(regNumber, type, owner, weight);
	}
}
