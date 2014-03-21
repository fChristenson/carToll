package se.fidde.cartoll.jar.util.constants;

public enum IntegerConstants {

	CAR_COST_LOW(8), TRUCK_WEIGHT_FEE(10), TRUCK_WEIGHTLIMIT(6), TRUCK_COST_LOW(12), TAXI_COST_LOW(10), TAXI_COST_LOW_WITH_SPECIALCOST(
			15), FREE_PRICE_LEVEL(0), CAR_COST_MEDIUM(10), CAR_COST_HIGH(12), TRUCK_COST_MEDIUM(20), TRUCK_COST_HIGH(25), TAXI_COST_MEDIUM(
			15), TAXI_COST_HIGH(20), MOCK_CURRENCY(1), TRUCK_SPECIAL_COST(10), TAXI_ENVIROMENT_FRIENDLY_SPECIAL_COST(0), TAXI_SPECIAL_COST(
			5), TRUCK_MOCK_WEIGHT(7), LOW_COST_TIME(6), TAXI_COST_FREE(0), MEDIUM_COST_TIME(8), HIGH_COST_TIME(7), MOCK_ID(
			1), EMPTY_ID(-1), CONNECTION_CHECKER_SLEEP_TIME(500);

	private final int NUMBER;

	private IntegerConstants(int number) {
		NUMBER = number;
	}

	public int getInt() {
		return NUMBER;
	}

	public double getDouble() {
		return NUMBER;
	}
}
