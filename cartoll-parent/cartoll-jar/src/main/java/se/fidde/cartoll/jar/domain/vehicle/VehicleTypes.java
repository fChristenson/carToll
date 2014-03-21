package se.fidde.cartoll.jar.domain.vehicle;

/**
 * @author fidde enums for all vehicle types
 */
public enum VehicleTypes {
	CAR("car"), TRUCK("truck"), TAXI("taxi"), EMPTY("empty");

	private final String NAME;

	private VehicleTypes(String name) {
		NAME = name;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
