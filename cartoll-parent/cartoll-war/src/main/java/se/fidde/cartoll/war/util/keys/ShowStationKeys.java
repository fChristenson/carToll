package se.fidde.cartoll.war.util.keys;

public enum ShowStationKeys {
	SHOW_STATION_HEADERTEXT("showStationHeaderText"), REG_NUMBER_TEXT("regNumberText"), VEHICLE_TYPE_TEXT(
			"vehicleTypeText"), OWNER_TEXT("ownerText"), PROFIT_TEXT("profitText"), DATE_TEXT("dateText"), STATION(
			"station"), PASSINGS("passings");

	private final String KEY;

	private ShowStationKeys(String string) {
		KEY = string;
	}

	@Override
	public String toString() {
		return KEY;
	}
}
