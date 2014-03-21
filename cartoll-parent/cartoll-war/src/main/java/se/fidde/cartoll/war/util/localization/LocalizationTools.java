package se.fidde.cartoll.war.util.localization;

import java.io.IOException;

import org.springframework.web.servlet.ModelAndView;

public class LocalizationTools {

	public static void setLocalizedText(Object[] keys, ModelAndView modelAndView) throws IOException {
		CartollMessages messages = new CartollMessages();

		for (Object obj : keys) {
			String message = messages.getMessage(obj.toString());
			if (message != null)
				modelAndView.addObject(obj.toString(), message);
		}
	}
}
