package se.fidde.cartoll.jar.util.constants;

/**
 * @author fidde enum for relative urls in module
 */
public enum RelativeUrl {
	JAR_PASSING_PROPERTIES("/prices/passing.properties"), SYSTEM_PASSING_PROPERTIES("passing.properties"), TRUCK_PROPERTIES(
			"/prices/truck.properties"), TAXI_PROPERTIES("/prices/taxi.properties"), CAR_PROPERTIES(
			"/prices/car.properties"), DATABASE_PROPERTIES("/databaseInfo.properties");

	private final String URL;

	private RelativeUrl(String url) {
		URL = url;
	}

	@Override
	public String toString() {
		return URL;
	}
}
