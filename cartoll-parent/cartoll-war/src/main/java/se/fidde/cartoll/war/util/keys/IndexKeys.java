package se.fidde.cartoll.war.util.keys;

public enum IndexKeys {

	WELCOME_MESSAGE("welcomeMessage"), ADD_PASSING_TEXT("addPassingText"), REMOVE_ALL_PASSINGS_TEXT(
			"removeAllPassingsText"), STATION_TEXT("stationText"), NUMBER_OF_PASSINGS_TEXT("numberOfPassingsText"), PROFIT_TEXT(
			"profitText"), TOTAL_PASSINGS("totalPassings"), TOTAL_PROFIT("totalProfit"), STATIONS("stations"), TOTAL_PASSINGS_TEXT(
			"totalPassingsText"), TOTAL_PROFIT_TEXT("totalProfitText"), VEHICLES("vehicles");

	private final String KEY;

	private IndexKeys(String string) {
		KEY = string;
	}

	@Override
	public String toString() {
		return KEY;
	}
}
