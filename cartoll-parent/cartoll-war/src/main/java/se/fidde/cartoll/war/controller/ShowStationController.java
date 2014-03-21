package se.fidde.cartoll.war.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import se.fidde.cartoll.jar.domain.station.Station;
import se.fidde.cartoll.jar.service.passing.PassingService;
import se.fidde.cartoll.jar.service.station.StationService;
import se.fidde.cartoll.war.util.constants.RelativeUrl;
import se.fidde.cartoll.war.util.constants.WarStringConstants;
import se.fidde.cartoll.war.util.keys.ShowStationKeys;
import se.fidde.cartoll.war.util.localization.CartollMessages;
import se.fidde.cartoll.war.util.localization.LocalizationTools;

@Controller
@RequestMapping("showStation")
public class ShowStationController {

	@Autowired
	private StationService stationService;
	@Autowired
	private PassingService passingService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showStation(@RequestParam("id") long id) throws IOException, SQLException {
		ModelAndView modelAndView = new ModelAndView("showStation");
		Station station = stationService.getStation(id);

		setData(modelAndView, station);
		setLinks(modelAndView);

		LocalizationTools.setLocalizedText(ShowStationKeys.values(), modelAndView);
		return modelAndView;
	}

	private void setData(ModelAndView modelAndView, Station station) {
		modelAndView.addObject(ShowStationKeys.STATION.toString(), station);
		modelAndView.addObject(ShowStationKeys.PASSINGS.toString(), passingService.getAllPassingsForStation(station));
		modelAndView.addObject(ShowStationKeys.SHOW_STATION_HEADERTEXT.toString(), station.getName());
	}

	private void setLinks(ModelAndView modelAndView) throws IOException {
		modelAndView.addObject(WarStringConstants.LINK_BACK.toString(), RelativeUrl.INDEX_HTML.toString());
		modelAndView.addObject(WarStringConstants.BACK_TEXT.toString(),
				new CartollMessages().getMessage(WarStringConstants.BACK_TEXT.toString()));
	}
}
