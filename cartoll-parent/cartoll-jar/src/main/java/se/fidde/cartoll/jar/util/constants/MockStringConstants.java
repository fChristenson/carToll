package se.fidde.cartoll.jar.util.constants;

public enum MockStringConstants {
	MOCK_STATION_NAME("mockStation"), MOCK_NAME("foo"), MOCK_LAST_NAME("bar"), MOCK_REG_NUMBER("ABC-123"), MOCK_CITY(
			"bazCity"), MOCK_STREET("fooStreet"), MOCK_ZIPCODE("666 66");

	private final String NAME;

	private MockStringConstants(String name) {
		NAME = name;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
