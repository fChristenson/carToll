package se.fidde.cartoll.jar.domain.price;

import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import se.fidde.cartoll.jar.util.validation.ValidationTools;

public abstract class AbstractPriceLoader {

	private Logger log = Logger.getLogger(AbstractPriceLoader.class);

	protected boolean checkIfDateHasCost(Date date, String[] dateStrings) {
		log.debug("checking if " + date + " is in range of " + dateStrings);
		ValidationTools.assertNotNull(date, dateStrings);

		Date start = parseDate(date, dateStrings[0]);
		Date end = parseDate(date, dateStrings[1]);
		end = checkIfEndDateIsNextDay(start, end);

		if (date.after(start) && date.before(end) || date.equals(start) || date.equals(end)) {
			log.debug(date + " was found to be in range: " + start + " - " + end);
			return true;
		}
		log.debug(date + " was found to not be in range: " + start + " - " + end);
		return false;
	}

	protected Date parseDate(Date date, String timeString) {
		log.debug("parsing " + timeString + " to be time for " + date);
		ValidationTools.assertNotNull(date, timeString);

		DateTime result = new DateTime(date);
		DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm");
		DateTime time = timeFormatter.parseDateTime(timeString);

		result = result.withHourOfDay(time.getHourOfDay());
		result = result.withMinuteOfHour(time.getMinuteOfHour());
		log.debug("returns: " + result);
		return result.toDate();
	}

	protected Date checkIfEndDateIsNextDay(Date start, Date end) {
		log.debug("checking 2 day span for: " + start + " and " + end);
		ValidationTools.assertNotNull(start, end);

		if (start.after(end)) {
			log.debug(start + " was found to be > than " + end);
			end = new DateTime(end).plusDays(1).toDate();
		}
		log.debug("returns: " + end);
		return end;
	}

}
