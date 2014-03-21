package se.fidde.cartoll.jar.domain.price;

/**
 * @author fidde enum for storing types of pricelevels
 */
public enum PriceLevelTypes {
	LOW("low"), MEDIUM("medium"), HIGH("high"), FREE("free");

	private final String NAME;

	private PriceLevelTypes(String key) {
		NAME = key;
	}

	@Override
	public String toString() {
		return NAME;
	}

}
