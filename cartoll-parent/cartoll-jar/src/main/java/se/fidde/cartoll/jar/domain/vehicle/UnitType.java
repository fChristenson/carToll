package se.fidde.cartoll.jar.domain.vehicle;

/**
 * @author fidde enum for different weight units
 */
public enum UnitType {
	TONS("tons"), KILOGRAMS("kilograms");

	private final String UNIT;

	private UnitType(String unit) {
		UNIT = unit;
	}

	@Override
	public String toString() {
		return UNIT;
	}
}
