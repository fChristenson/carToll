package se.fidde.cartoll.jar.integration;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Car;
import se.fidde.cartoll.jar.domain.vehicle.EnviromentFriendly;
import se.fidde.cartoll.jar.domain.vehicle.Taxi;
import se.fidde.cartoll.jar.domain.vehicle.Truck;
import se.fidde.cartoll.jar.service.owner.OwnerService;
import se.fidde.cartoll.jar.service.station.StationService;
import se.fidde.cartoll.jar.service.vehicle.VehicleService;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.factories.MockObjectFactory;
import se.fidde.cartoll.jar.util.factories.PriceFactory;
import se.fidde.cartoll.jar.util.factories.PriceFactoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/db.xml" })
public class CartollIntegrationTest {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private StationService stationService;
    @Autowired
    private VehicleService vehicleService;
    private PriceFactory priceFactory = new PriceFactoryImpl();

    Station station1 = MockObjectFactory.getMockStation();
    Station station2 = new Station("name");

    Car car = MockObjectFactory.getMockCar();

    Taxi taxiEco = MockObjectFactory.getMockTaxi(EnviromentFriendly.TRUE);
    Taxi taxiNonEco = MockObjectFactory.getMockTaxi(EnviromentFriendly.FALSE);

    Truck truckLight = MockObjectFactory.getMockTruck(5);
    Truck truckHeavy = MockObjectFactory.getMockTruck(8.5);

    DateTime time = new DateTime();

    @Before
    public void setUp() {
        ownerService.removeAllOwners();
        stationService.removeAllStations();
    }

    @Test
    public void testMandatoryTest() throws IOException {

        ownerService.addOwner(MockObjectFactory.getMockOwner());
        assertEquals("Database has one owner", 1, ownerService.getAllOwners()
                .size());

        stationService.addStation(station1);
        stationService.addStation(station2);

        assertEquals("Database has 2 stations", 2, stationService
                .getAllStations().size());

        Passing passing = new Passing(car, station1, time.withHourOfDay(7)
                .withMinuteOfHour(0).toDate());
        Currency price = priceFactory.getPriceFor(passing.getVehicle(),
                passing.getDate());
        passing.setCost(price);

        assertEquals(IntegerConstants.CAR_COST_HIGH.getDouble(), passing
                .getCost().getAmount().intValue(), 0);

        passing = new Passing(taxiNonEco, station1, time.withHourOfDay(9)
                .withMinuteOfHour(0).toDate());
        price = priceFactory.getPriceFor(passing.getVehicle(),
                passing.getDate());
        passing.setCost(price);

        assertEquals(
                IntegerConstants.TAXI_COST_LOW_WITH_SPECIALCOST.getDouble(),
                passing.getCost().getAmount().intValue(), 0);

        passing = new Passing(taxiEco, station1, time.withHourOfDay(11)
                .withMinuteOfHour(0).toDate());
        price = priceFactory.getPriceFor(passing.getVehicle(),
                passing.getDate());
        passing.setCost(price);

        assertEquals(IntegerConstants.TAXI_COST_LOW.getDouble(), passing
                .getCost().getAmount().intValue(), 0);

        passing = new Passing(truckLight, station2, time.withHourOfDay(13)
                .withMinuteOfHour(0).toDate());
        price = priceFactory.getPriceFor(passing.getVehicle(),
                passing.getDate());
        passing.setCost(price);

        assertEquals(12.0, passing.getCost().getAmount().intValue(), 0);

        passing = new Passing(truckHeavy, station2, time.withHourOfDay(15)
                .withMinuteOfHour(0).toDate());
        price = priceFactory.getPriceFor(passing.getVehicle(),
                passing.getDate());
        passing.setCost(price);

        assertEquals(40.0, passing.getCost().getAmount().intValue(), 0);
    }
}
