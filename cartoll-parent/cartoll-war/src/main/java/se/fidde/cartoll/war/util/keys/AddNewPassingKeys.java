package se.fidde.cartoll.war.util.keys;

public enum AddNewPassingKeys {
	ADD_NEW_PASSING_HEADER_TEXT("addNewPassingHeaderText"), STATION_TEXT("stationText"), VEHICLE_TEXT("vehicleText"), TIME_TEXT(
			"timeText"), TIME_UNIT_TEXT("timeUnitText"), ADD_TEXT("addText"), VEHICLES("vehicles"), STATIONS("stations"), BEAN(
			"bean"), ID("id"), ENVIROMENT_CERTIFIED_TEXT("enviromentCertifiedText");

	private final String KEY;

	private AddNewPassingKeys(String string) {
		KEY = string;
	}

	@Override
	public String toString() {
		return KEY;
	}
}
