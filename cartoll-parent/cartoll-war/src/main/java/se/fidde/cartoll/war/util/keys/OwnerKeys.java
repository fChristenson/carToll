package se.fidde.cartoll.war.util.keys;

public enum OwnerKeys {

	OWNER_HEADER_TEXT("ownerHeaderText"), BACK_TEXT("backText"), OWNER_LINK_TEXT("ownerLinkText"), ADDRESSLINE1(
			"addressLine1"), ADDRESSLINE2("addressLine2"), VEHICLE_TEXT("vehicleText"), VEHICLE_TYPE_TEXT(
			"vehicleTypeText"), NUMBER_OF_PASSINGS_TEXT("numberOfPassingsText"), COST_TEXT("costText"), OWNER("owner"), VEHICLES(
			"vehicles");
	private final String KEY;

	private OwnerKeys(String string) {
		KEY = string;
	}

	@Override
	public String toString() {
		return KEY;
	}
}
