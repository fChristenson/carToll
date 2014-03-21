package se.fidde.cartoll.war.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.fidde.cartoll.jar.domain.owner.Address;
import se.fidde.cartoll.jar.domain.owner.Name;
import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.EnviromentFriendly;
import se.fidde.cartoll.jar.domain.vehicle.Taxi;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.domain.vehicle.VehicleTypes;
import se.fidde.cartoll.jar.service.owner.OwnerService;
import se.fidde.cartoll.jar.service.passing.PassingService;
import se.fidde.cartoll.jar.service.station.StationService;
import se.fidde.cartoll.jar.service.vehicle.VehicleService;
import se.fidde.cartoll.war.util.keys.IndexKeys;
import se.fidde.cartoll.war.util.localization.LocalizationTools;
import se.fidde.cartoll.war.util.statistics.CartollStatistics;

@Controller
@RequestMapping("index")
public class IndexController {
	@Autowired
	private PassingService passingService;
	@Autowired
	private StationService stationService;
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private VehicleService vehicleService;
	private boolean addMockData = true;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() throws IOException {
		ModelAndView modelAndView = new ModelAndView("index");

		addMockData();
		setData(modelAndView);

		LocalizationTools.setLocalizedText(IndexKeys.values(), modelAndView);
		return modelAndView;
	}

	private void addMockData() throws IOException {
		if (!addMockData)
			return;

		addMockData = false;
		Station station = new Station("test");
		stationService.addStation(station);

		Address address = new Address("foostreet", "666 66", "Onsala");
		Name name = new Name("foo", "bar");
		Owner owner = new Owner(name, address);
		ownerService.addOwner(owner);

		Vehicle vehicle = new Taxi("TES-444", VehicleTypes.TAXI, owner, EnviromentFriendly.TRUE);
		vehicleService.addVehicle(vehicle);
	}

	private void setData(ModelAndView modelAndView) {
		modelAndView.addObject(IndexKeys.TOTAL_PASSINGS.toString(), passingService.getTotalPassings());
		modelAndView.addObject(IndexKeys.TOTAL_PROFIT.toString(), passingService.getTotalProfit());

		List<CartollStatistics> list = getStatistics();
		modelAndView.addObject(IndexKeys.STATIONS.toString(), list);

	}

	private List<CartollStatistics> getStatistics() {
		Collection<Station> stations = stationService.getAllStations();
		ArrayList<CartollStatistics> list = new ArrayList<>();

		for (Station station : stations) {
			int totalPassings = passingService.getTotalPassingsForStation(station);
			Currency totalProfit = passingService.getTotalProfitForStation(station);
			list.add(new CartollStatistics(station.getId(), station.getName(), totalPassings, totalProfit));
		}

		return list;
	}
}
