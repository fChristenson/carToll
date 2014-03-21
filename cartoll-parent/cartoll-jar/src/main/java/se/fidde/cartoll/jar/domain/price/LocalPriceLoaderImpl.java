package se.fidde.cartoll.jar.domain.price;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import se.fidde.cartoll.jar.util.constants.RelativeUrl;
import se.fidde.cartoll.jar.util.constants.ValidationStringConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde strategy for parsing in pricelevels from local property file
 */
public class LocalPriceLoaderImpl extends AbstractPriceLoader implements PriceLoader {

	private final Map<String, PriceLevelTypes> priceLevels;
	private Logger log = Logger.getLogger(LocalPriceLoaderImpl.class);

	public LocalPriceLoaderImpl() throws IOException {
		priceLevels = loadPriceLevelsFromPropertiesFile();
	}

	public PriceLevelTypes getPriceLevelFor(Date date) throws FileNotFoundException, IOException {
		log.debug("getting pricelevel for: " + date);
		ValidationTools.isNull(date);

		for (String timeRange : priceLevels.keySet()) {
			log.debug("splitting " + timeRange);
			String[] split = timeRange.split("-");
			if (checkIfDateHasCost(date, split))
				return priceLevels.get(timeRange);
		}

		return PriceLevelTypes.FREE;
	}

	private PriceLevelTypes parsePriceLevel(String priceLevelString) {
		ValidationTools.assertNotNull(priceLevelString);

		log.debug("getting enum for: " + priceLevelString);
		return PriceLevelTypes.valueOf(priceLevelString.toUpperCase());
	}

	private Map<String, PriceLevelTypes> loadPriceLevelsFromPropertiesFile() throws IOException {

		Map<String, PriceLevelTypes> map = new ConcurrentHashMap<String, PriceLevelTypes>();
		getDefaultPrices(map);
		return map;

	}

	private void getDefaultPrices(Map<String, PriceLevelTypes> map) {
		InputStream inputStream = getClass().getResourceAsStream(RelativeUrl.JAR_PASSING_PROPERTIES.toString());
		Scanner scanner = new Scanner(inputStream);

		try {
			while (scanner.hasNext()) {

				scanForPrices(scanner, map);
			}
		} finally {
			log.debug("closing scanner");
			scanner.close();
		}
	}

	private void scanForPrices(Scanner scanner, Map<String, PriceLevelTypes> map) {
		String line = scanner.findInLine(ValidationStringConstants.VALID_PASSING_PROPERTIES_FORMAT.toString());
		if (line == null)
			scanner.nextLine();
		else {
			log.debug(line + " is being added to pricelevels map");
			addParsedValuesToMap(map, line);
		}
	}

	private void addParsedValuesToMap(Map<String, PriceLevelTypes> map, String line) {
		log.debug("parsing and adding " + line);
		ValidationTools.assertNotNull(map, line);

		line = line.trim().replaceAll("\\\\", "");
		String[] split = line.split("=");

		log.debug("Added key: " + split[0] + " value: " + split[1]);
		map.put(split[0], parsePriceLevel(split[1]));
	}
}
