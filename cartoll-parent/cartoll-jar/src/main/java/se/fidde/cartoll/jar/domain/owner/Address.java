package se.fidde.cartoll.jar.domain.owner;

import se.fidde.cartoll.jar.util.constants.ValidationStringConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde Address contains a persons address information
 */
public class Address {
	private String street;
	private String zipcode;
	private String city;

	public Address(String street, String zipcode, String city) {
		ValidationTools.isNull(street, zipcode, city);
		isZipCode(zipcode);

		this.setStreet(street);
		this.setZipcode(zipcode);
		this.setCity(city);
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", getStreet(), getZipcode(), getCity());
	}

	public static void isZipCode(String zipcode) {
		if (zipcode.matches(ValidationStringConstants.ZIPCODE_REGEX.toString()))
			return;

		throw new IllegalArgumentException(zipcode + " is not a valid format for a zipcode!");
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
}
