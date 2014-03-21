package se.fidde.cartoll.jar.util.factories;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.easymock.EasyMock;
import org.joda.time.DateTime;

import se.fidde.cartoll.jar.domain.owner.Address;
import se.fidde.cartoll.jar.domain.owner.Name;
import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.price.CurrencyTypes;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Car;
import se.fidde.cartoll.jar.domain.vehicle.EnviromentFriendly;
import se.fidde.cartoll.jar.domain.vehicle.Taxi;
import se.fidde.cartoll.jar.domain.vehicle.Truck;
import se.fidde.cartoll.jar.domain.vehicle.UnitType;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.domain.vehicle.VehicleMockData;
import se.fidde.cartoll.jar.domain.vehicle.VehicleTypes;
import se.fidde.cartoll.jar.domain.vehicle.Weight;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.constants.MockStringConstants;

public class MockObjectFactory {

	public static Taxi getMockTaxi(EnviromentFriendly enviromentFriendly) {
		VehicleMockData mockData = getMockVehicleId(getMockOwner(), VehicleTypes.TAXI);

		return (Taxi) VehicleFactory.createTaxi(mockData.getRegNumber(), mockData.getType(), mockData.getOwner(),
				enviromentFriendly);
	}

	public static Car getMockCar() {
		VehicleMockData mockData = getMockVehicleId(getMockOwner(), VehicleTypes.CAR);
		return (Car) VehicleFactory.createCar(mockData.getRegNumber(), mockData.getType(), mockData.getOwner());
	}

	public static Truck getMockTruck(double weight) {
		Weight weight2 = new Weight(weight, UnitType.TONS);
		VehicleMockData mockData = getMockVehicleId(getMockOwner(), VehicleTypes.TRUCK);

		return (Truck) VehicleFactory.createTruck(mockData.getRegNumber(), mockData.getType(), mockData.getOwner(),
				weight2);
	}

	public static VehicleMockData getMockVehicleId(Owner mockOwner, VehicleTypes type) {
		return new VehicleMockData(MockStringConstants.MOCK_REG_NUMBER.toString(), type, mockOwner);
	}

	public static Owner getMockOwner() {
		return new Owner(getMockName(), getMockAddress());
	}

	public static Name getMockName() {
		return new Name(MockStringConstants.MOCK_NAME.toString(), MockStringConstants.MOCK_LAST_NAME.toString());
	}

	public static Address getMockAddress() {
		return new Address(MockStringConstants.MOCK_STREET.toString(), MockStringConstants.MOCK_ZIPCODE.toString(),
				MockStringConstants.MOCK_CITY.toString());
	}

	public static Address getEasyMockAdress() {
		Address mock = EasyMock.createNiceMock(Address.class);

		EasyMock.expect(mock.getStreet()).andReturn(MockStringConstants.MOCK_STREET.toString()).anyTimes();
		EasyMock.expect(mock.getZipcode()).andReturn(MockStringConstants.MOCK_ZIPCODE.toString()).anyTimes();
		EasyMock.expect(mock.getCity()).andReturn(MockStringConstants.MOCK_CITY.toString()).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Name getEasyMockName() {
		Name mock = EasyMock.createNiceMock(Name.class);

		EasyMock.expect(mock.getFirstName()).andReturn(MockStringConstants.MOCK_NAME.toString()).anyTimes();
		EasyMock.expect(mock.getLastName()).andReturn(MockStringConstants.MOCK_LAST_NAME.toString()).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Owner getEasyMockOwner() {
		Owner mock = EasyMock.createNiceMock(Owner.class);

		EasyMock.expect(mock.getFullName()).andReturn(getEasyMockName()).anyTimes();
		EasyMock.expect(mock.getFullAddress()).andReturn(getEasyMockAdress()).anyTimes();
		EasyMock.expect(mock.getVehicles()).andReturn(new HashSet<Vehicle>()).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Car getEasyMockCar() {
		Car mock = EasyMock.createNiceMock(Car.class);

		EasyMock.expect(mock.getRegNumber()).andReturn(MockStringConstants.MOCK_REG_NUMBER.toString()).anyTimes();
		EasyMock.expect(mock.getType()).andReturn(VehicleTypes.CAR).anyTimes();
		EasyMock.expect(mock.getOwner()).andReturn(getEasyMockOwner()).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Station getEasyMockStation() {
		Station mock = EasyMock.createNiceMock(Station.class);
		EasyMock.expect(mock.getName()).andReturn(MockStringConstants.MOCK_NAME.toString()).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Currency getEasyMockCurrency() {
		Currency mock = EasyMock.createNiceMock(Currency.class);

		EasyMock.expect(mock.getAmount()).andReturn(new BigInteger("0")).anyTimes();
		EasyMock.expect(mock.getType()).andReturn(CurrencyTypes.SEK).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Passing getEasyMockPassing(Date date) throws IOException {
		Passing mock = EasyMock.createNiceMock(Passing.class);

		EasyMock.expect(mock.getVehicle()).andReturn(getEasyMockCar()).anyTimes();
		EasyMock.expect(mock.getCost()).andReturn(getEasyMockCurrency()).anyTimes();
		EasyMock.expect(mock.getDate()).andReturn(date).anyTimes();
		EasyMock.expect(mock.getStation()).andReturn(getEasyMockStation()).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Truck getEasyMockTruck() {
		Truck mock = EasyMock.createNiceMock(Truck.class);

		EasyMock.expect(mock.getRegNumber()).andReturn(MockStringConstants.MOCK_REG_NUMBER.toString()).anyTimes();
		EasyMock.expect(mock.getType()).andReturn(VehicleTypes.TRUCK).anyTimes();
		EasyMock.expect(mock.getOwner()).andReturn(getEasyMockOwner()).anyTimes();
		EasyMock.expect(mock.getWeight()).andReturn(getEasyMockWeight()).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Weight getEasyMockWeight() {
		Weight mock = EasyMock.createNiceMock(Weight.class);

		EasyMock.expect(mock.getAmount()).andReturn(IntegerConstants.TRUCK_MOCK_WEIGHT.getDouble()).anyTimes();
		EasyMock.expect(mock.getUnit()).andReturn(UnitType.TONS).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Date getMockDate(int hours) {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, 0);

		return calendar.getTime();
	}

	public static Taxi getEasyMockTaxi(EnviromentFriendly enviromentFriendly) {
		Taxi mock = EasyMock.createNiceMock(Taxi.class);

		EasyMock.expect(mock.getRegNumber()).andReturn(MockStringConstants.MOCK_REG_NUMBER.toString()).anyTimes();
		EasyMock.expect(mock.getType()).andReturn(VehicleTypes.TAXI).anyTimes();
		EasyMock.expect(mock.getOwner()).andReturn(getEasyMockOwner()).anyTimes();

		boolean isEnviromentFriendly = (enviromentFriendly == EnviromentFriendly.TRUE) ? true : false;
		EasyMock.expect(mock.isEnviromentFriendly()).andReturn(isEnviromentFriendly).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static VehicleMockData getEasyMockVehicleId(VehicleTypes type) {
		VehicleMockData mock = EasyMock.createNiceMock(VehicleMockData.class);

		EasyMock.expect(mock.getRegNumber()).andReturn(MockStringConstants.MOCK_REG_NUMBER.toString()).anyTimes();
		EasyMock.expect(mock.getType()).andReturn(type).anyTimes();
		EasyMock.expect(mock.getOwner()).andReturn(getEasyMockOwner()).anyTimes();

		EasyMock.replay(mock);
		return mock;
	}

	public static Passing getMockPassing(Station mockStation) throws IOException {
		DateTime time = new DateTime().withHourOfDay(IntegerConstants.HIGH_COST_TIME.getInt()).withMinuteOfHour(0);
		Taxi taxi = getMockTaxi(EnviromentFriendly.TRUE);

		Passing passing = new Passing(taxi, mockStation, time.toDate());
		Currency currency = new PriceFactoryImpl().getPriceFor(taxi, time.toDate());

		passing.setCost(currency);
		return passing;
	}

	public static Station getMockStation() {
		return new Station(MockStringConstants.MOCK_STATION_NAME.toString());
	}

	public static Weight getMockWeight(double amount) {
		return new Weight(amount, UnitType.TONS);
	}

}
