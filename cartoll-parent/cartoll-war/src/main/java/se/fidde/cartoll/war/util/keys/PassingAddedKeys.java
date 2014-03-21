package se.fidde.cartoll.war.util.keys;

public enum PassingAddedKeys {
	PASSING_ADDED_HEADER_TEXT("passingAddedHeaderText"), STATION_TEXT("stationText"), ID_TEXT("idText"), VEHICLE_TEXT(
			"vehicleText"), TIME_TEXT("timeText"), COST_TEXT("costText"), START_PAGE_LINK_TEXT("startPageLinkText"), ADD_NEW_PASSING_LINK_TEXT(
			"addNewPassingLinkText"), PASSING("passing"), ENVIROMENT_CERTIFIED_TEXT("enviromentCertifiedText");

	private final String KEY;

	private PassingAddedKeys(String string) {
		KEY = string;
	}

	@Override
	public String toString() {
		return KEY;
	}
}
