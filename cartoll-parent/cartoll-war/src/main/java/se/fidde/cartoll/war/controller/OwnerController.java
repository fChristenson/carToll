package se.fidde.cartoll.war.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.domain.price.Currency;
import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.service.owner.OwnerService;
import se.fidde.cartoll.jar.service.passing.PassingService;
import se.fidde.cartoll.jar.service.vehicle.VehicleService;
import se.fidde.cartoll.war.util.constants.RelativeUrl;
import se.fidde.cartoll.war.util.constants.WarStringConstants;
import se.fidde.cartoll.war.util.keys.IndexKeys;
import se.fidde.cartoll.war.util.keys.OwnerKeys;
import se.fidde.cartoll.war.util.localization.CartollMessages;
import se.fidde.cartoll.war.util.localization.LocalizationTools;
import se.fidde.cartoll.war.util.statistics.CartollStatistics;

@Controller
@RequestMapping("owner")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	@Autowired
	private PassingService passingService;
	@Autowired
	private VehicleService vehicleService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showOwner(@RequestParam long id) throws IOException {
		ModelAndView modelAndView = new ModelAndView("owner");

		setData(id, modelAndView);
		setLinks(modelAndView);

		LocalizationTools.setLocalizedText(OwnerKeys.values(), modelAndView);
		return modelAndView;
	}

	private void setData(long id, ModelAndView modelAndView) throws IOException {
		Owner owner = ownerService.getOwner(id);
		modelAndView.addObject(OwnerKeys.OWNER.toString(), owner);
		List<CartollStatistics> list = getStatistics(id);
		modelAndView.addObject(IndexKeys.VEHICLES.toString(), list);
	}

	private void setLinks(ModelAndView modelAndView) throws IOException {
		modelAndView.addObject(WarStringConstants.LINK_BACK.toString(), RelativeUrl.INDEX_HTML.toString());
		modelAndView.addObject(WarStringConstants.BACK_TEXT.toString(),
				new CartollMessages().getMessage(WarStringConstants.BACK_TEXT.toString()));
	}

	private List<CartollStatistics> getStatistics(long id) throws IOException {
		Owner owner = ownerService.getOwner(id);

		Collection<Vehicle> vehicles = vehicleService.getVehiclesForOwner(owner);
		ArrayList<CartollStatistics> result = new ArrayList<>();

		for (Vehicle vehicle : vehicles) {
			int totalPassings = passingService.getTotalPassingsFor(vehicle);
			Currency totalProfit = passingService.getTotalProfitFor(vehicle);

			result.add(new CartollStatistics(vehicle.getId(), vehicle.getRegNumber(), vehicle.getType(), totalPassings,
					totalProfit));
		}

		return result;
	}
}
