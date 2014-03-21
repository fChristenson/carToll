package se.fidde.cartoll.jar.domain.price;

import java.math.BigInteger;

import org.apache.log4j.Logger;

import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde object for storing money values
 */
public class Currency {

	private CurrencyTypes type;
	private BigInteger amount;
	private Logger log = Logger.getLogger(Currency.class);

	public Currency() {
		amount = new BigInteger("0");
		type = CurrencyTypes.SEK;
	}

	public Currency(BigInteger amount, CurrencyTypes currencyType) {
		ValidationTools.isNull(amount, currencyType);

		log.debug("getting currency for: " + amount + " " + currencyType);
		if (amount.intValue() < 0) {
			throw new IllegalArgumentException("Amount can not be negative!");
		}

		this.amount = amount;
		type = currencyType;
	}

	public Currency addCost(Currency value) {
		ValidationTools.isNull(value);

		log.debug("adding " + value + " to " + this);
		if (value.getType() != type)
			throw new IllegalArgumentException(value.getType() + " invalid currency type!");

		else if (value.getAmount().intValue() < 0)
			throw new IllegalArgumentException(value.getAmount() + " value can not be < 0");

		try {
			BigInteger valueToAdd = new BigInteger(String.valueOf(value.getAmount().intValue()));
			amount = new BigInteger(amount.toString()).add(valueToAdd);
			return new Currency(amount, type);

		} catch (Exception e) {

			return value;
		}
	}

	public BigInteger getAmount() {
		return new BigInteger(amount.toString());
	}

	public CurrencyTypes getType() {
		return type;
	}

	@Override
	public String toString() {
		return String.format("%d %s", amount.intValue(), type.toString());
	}
}
