package se.fidde.cartoll.war.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import se.fidde.cartoll.jar.domain.passing.Passing;
import se.fidde.cartoll.jar.domain.vehicle.Taxi;
import se.fidde.cartoll.jar.domain.vehicle.VehicleTypes;
import se.fidde.cartoll.jar.service.passing.PassingService;
import se.fidde.cartoll.war.util.keys.PassingAddedKeys;
import se.fidde.cartoll.war.util.localization.CartollMessages;
import se.fidde.cartoll.war.util.localization.LocalizationTools;

@Controller
@RequestMapping("passingAdded")
public class PassingAddedController {

	@Autowired
	private PassingService passingService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView passingAdded(@RequestParam long id) throws IOException {
		ModelAndView modelAndView = new ModelAndView("passingAdded");

		Passing passing = passingService.getPassing(id);
		modelAndView.addObject(PassingAddedKeys.PASSING.toString(), passing);
		addEnviromentCertifiedText(modelAndView, passing);

		LocalizationTools.setLocalizedText(PassingAddedKeys.values(), modelAndView);
		return modelAndView;
	}

	private void addEnviromentCertifiedText(ModelAndView modelAndView, Passing passing) throws IOException {
		String message = new CartollMessages().getMessage(PassingAddedKeys.ENVIROMENT_CERTIFIED_TEXT.toString());

		if (passing.getVehicle().getType() == VehicleTypes.TAXI) {
			Taxi taxi = (Taxi) passing.getVehicle();

			if (taxi.isEnviromentFriendly()) {
				String displayName = String.format("%s %s %s", taxi.getRegNumber(), taxi.getType(), message);
				taxi.setDisplayName(displayName);
			}
		}
	}
}
