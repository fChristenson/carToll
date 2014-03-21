package se.fidde.cartoll.war.util.localization;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import se.fidde.cartoll.war.util.constants.RelativeUrl;
import se.fidde.cartoll.war.util.constants.WarStringConstants;

public class CartollMessages {
	private Properties properties;

	public CartollMessages() throws IOException {
		loadProperties();
	}

	private void loadProperties() throws IOException {
		properties = new Properties();

		Locale locale = Locale.getDefault();
		String url = RelativeUrl.PROPERTIES.toString() + locale + WarStringConstants.PROPERTIES_FILE_SUFFIX.toString();

		InputStream inputStream = getClass().getResourceAsStream(url);
		if (inputStream == null)
			inputStream = getClass().getResourceAsStream(RelativeUrl.ENGLISH_LANGUAGE.toString());

		properties.load(inputStream);
	}

	public String getMessage(String key) {
		return properties.getProperty(key);
	}

	@Override
	public String toString() {
		return Locale.getDefault().getDisplayLanguage() + " messages";
	}
}
