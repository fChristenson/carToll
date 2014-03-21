package se.fidde.cartoll.jar.util.constants;

public enum ValidationStringConstants {
	VALID_PASSING_PROPERTIES_FORMAT("\\d{2}\\\\:\\d{2}\\s?-\\s?\\d{2}\\\\:\\d{2}=\\w{3,6}"), ZIPCODE_REGEX(
			"\\d{3}(\\s|-)?\\d{2}"), REG_NUMBER_REGEX("\\w{3}-?\\d{3}");

	private final String NAME;

	private ValidationStringConstants(String name) {
		NAME = name;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
