package se.fidde.cartoll.war.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.domain.vehicle.Taxi;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.domain.vehicle.VehicleTypes;
import se.fidde.cartoll.jar.service.passing.PassingService;
import se.fidde.cartoll.jar.service.station.StationService;
import se.fidde.cartoll.jar.service.vehicle.VehicleService;
import se.fidde.cartoll.war.util.constants.RelativeUrl;
import se.fidde.cartoll.war.util.constants.WarStringConstants;
import se.fidde.cartoll.war.util.keys.AddNewPassingKeys;
import se.fidde.cartoll.war.util.localization.CartollMessages;
import se.fidde.cartoll.war.util.localization.LocalizationTools;

@Controller
@RequestMapping("addNewPassing")
public class addNewPassingController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private StationService stationService;
	@Autowired
	private PassingService passingService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView addNewPassing() throws IOException {
		ModelAndView modelAndView = new ModelAndView("addNewPassing");

		setData(modelAndView);
		setLinks(modelAndView);

		LocalizationTools.setLocalizedText(AddNewPassingKeys.values(), modelAndView);
		return modelAndView;
	}

	private void setData(ModelAndView modelAndView) throws IOException {
		Collection<Vehicle> allVehicles = vehicleService.getAllVehicles();
		setDisplayNames(allVehicles);

		modelAndView.addObject(AddNewPassingKeys.VEHICLES.toString(), allVehicles);
		modelAndView.addObject(AddNewPassingKeys.STATIONS.toString(), stationService.getAllStations());
		modelAndView.addObject(AddNewPassingKeys.BEAN.toString(), new PassingFormBean());
	}

	private void setDisplayNames(Collection<Vehicle> allVehicles) throws IOException {
		String message = new CartollMessages().getMessage(AddNewPassingKeys.ENVIROMENT_CERTIFIED_TEXT.toString());

		for (Vehicle vehicle : allVehicles) {
			String name = String.format("%s-%s ", vehicle.getType(), vehicle.getRegNumber());

			if (vehicle.getType() == VehicleTypes.TAXI) {
				Taxi taxi = (Taxi) vehicle;
				name += (taxi.isEnviromentFriendly()) ? message : "";
			}

			vehicle.setDisplayName(name);
		}
	}

	private void setLinks(ModelAndView modelAndView) throws IOException {
		modelAndView.addObject(WarStringConstants.LINK_BACK.toString(), RelativeUrl.INDEX_HTML.toString());
		modelAndView.addObject(WarStringConstants.BACK_TEXT.toString(),
				new CartollMessages().getMessage(WarStringConstants.BACK_TEXT.toString()));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addNewPassingPost(PassingFormBean bean) throws IOException, NumberFormatException, SQLException {
		ModelAndView modelAndView = new ModelAndView("redirect:passingAdded.html");
		addPassing(modelAndView, bean);

		return modelAndView;
	}

	private void addPassing(ModelAndView modelAndView, PassingFormBean bean) throws IOException, NumberFormatException,
			SQLException {

		Vehicle vehicle = vehicleService.getVehicle(Long.parseLong(bean.getVehicleId()));
		Station station = stationService.getStation(Long.parseLong(bean.getStationId()));
		Date date = getDate(bean);

		Passing passing = new Passing(vehicle, station, date);
		long addPassing = passingService.addPassing(passing);
		modelAndView.addObject(AddNewPassingKeys.ID.toString(), addPassing);
	}

	private Date getDate(PassingFormBean bean) {
		DateTime time = new DateTime();
		time = time.withHourOfDay(Integer.parseInt(bean.getHour()));
		time = time.withMinuteOfHour(Integer.parseInt(bean.getMinute()));

		Date date = time.toDate();
		return date;
	}
}
