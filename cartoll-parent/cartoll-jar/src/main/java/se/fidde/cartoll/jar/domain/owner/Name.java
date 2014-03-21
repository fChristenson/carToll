package se.fidde.cartoll.jar.domain.owner;

import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde object for a persons name
 */
public class Name {

	private String firstName;
	private String lastName;

	public Name(String firstName, String lastName) {
		ValidationTools.isNull(firstName, lastName);

		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	@Override
	public String toString() {
		return String.format("%s %s", getFirstName(), getLastName());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
