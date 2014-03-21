package se.fidde.cartoll.jar.domain.vehicle;

import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde object for holding a weight
 */
public class Weight {

	private double amount;
	private UnitType unit;

	public Weight(double amount, UnitType unit) {
		ValidationTools.isNull(amount, unit);

		this.setAmount(amount);
		this.setUnit(unit);
	}

	@Override
	public String toString() {
		return String.format("%d %s", amount, unit);
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public UnitType getUnit() {
		return unit;
	}

	public void setUnit(UnitType unit) {
		this.unit = unit;
	}

}
