package se.fidde.cartoll.jar.util.validation;

import org.apache.log4j.Logger;

/**
 * @author fidde util class for validations of parameters
 */
public class ValidationTools {

	private static Logger log = Logger.getLogger(ValidationTools.class);

	public static void assertNotNull(Object... objects) {
		for (Object object : objects)
			assert object != null;
	}

	public static void isNull(Object... objects) {
		for (Object obj : objects)
			if (obj == null) {
				log.debug("param was null");
				throw new NullPointerException();
			}
	}

}
