package se.fidde.cartoll.jar.domain.price;

/**
 * @author fidde enum for types of currencies
 */
public enum CurrencyTypes {
	SEK("sek"), DOLLAR("dollar");

	private final String NAME;

	private CurrencyTypes(String name) {
		NAME = name;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
