package se.fidde.cartoll.war.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.fidde.cartoll.jar.service.passing.PassingService;

@Controller
@RequestMapping("removeAllPassings")
public class RemoveAllPassingsController {

	@Autowired
	private PassingService passingService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView removeAllPassings() {
		passingService.removeAllPassings();
		ModelAndView modelAndView = new ModelAndView("redirect:index.html");

		return modelAndView;
	}
}
