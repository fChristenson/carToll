package se.fidde.cartoll.jar.util.constants;

/**
 * @author fidde enum for strings used within module
 */
public enum JarStringConstants {
	EXTRA_COST("extraCost"), WEIGHT_LIMIT("weightLimit"), EMPTY_REG_NUMBER("EEE-111"), EMPTY_NAME("empty"), EMPTY_ZIPCODE(
			"666 66");

	private final String NAME;

	private JarStringConstants(String name) {
		NAME = name;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
