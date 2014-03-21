package se.fidde.cartoll.war.util.statistics;

import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.vehicle.VehicleTypes;

public class CartollStatistics {

	private long id;
	private String name;
	private int totalPassings;
	private Currency totalCost;
	private VehicleTypes type;

	public CartollStatistics(long id, String name, int totalPassings, Currency prise) {
		this.id = id;
		this.name = name;
		this.totalPassings = totalPassings;
		totalCost = prise;
	}

	public CartollStatistics(long id, String name, VehicleTypes type, int totalPassings, Currency price) {
		this.id = id;
		this.name = name;
		this.totalPassings = totalPassings;
		totalCost = price;
		this.setType(type);
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getTotalPassings() {
		return totalPassings;
	}

	public Currency getTotalCost() {
		return totalCost;
	}

	public VehicleTypes getType() {
		return type;
	}

	public void setType(VehicleTypes type) {
		this.type = type;
	}
}