package se.fidde.cartoll.war.util.constants;

public enum WarStringConstants {
	PROPERTIES_FILE_SUFFIX(".properties"), HEADER_TEXT("headerText"), BACK_TEXT("backText"), LINK_BACK("linkBack");

	private final String NAME;

	private WarStringConstants(String string) {
		NAME = string;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
