package se.fidde.cartoll.jar.repository;

public enum DatabaseStringConstants {
	PRICE_LEVELS_SQL_QUERY("SELECT * FROM PriceLevels"), USER("user"), PASSWORD("password"), DATABASE_NAME("name"), HOST(
			"host");

	private final String NAME;

	private DatabaseStringConstants(String name) {
		NAME = name;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
