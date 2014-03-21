package se.fidde.cartoll.jar.domain.price;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import se.fidde.cartoll.jar.repository.CartollConnectionHandler;
import se.fidde.cartoll.jar.repository.DatabaseStringConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde database strategy for loading pricelevels into memory
 * 
 */
public class DatabasePriceLoaderImpl extends AbstractPriceLoader implements PriceLoader {

	private Map<String, PriceLevelTypes> priceLevels;
	private Logger log = Logger.getLogger(DatabasePriceLoaderImpl.class);

	public DatabasePriceLoaderImpl() throws SQLException, FileNotFoundException, IOException {
		priceLevels = getPricesFromDatabase();
	}

	@Override
	public PriceLevelTypes getPriceLevelFor(Date date) throws FileNotFoundException, IOException {
		log.debug("getting pricelevel for: " + date);
		ValidationTools.isNull(date);

		for (String timeRange : priceLevels.keySet()) {
			log.debug("splitting " + timeRange);

			String[] split = timeRange.split("-");
			if (checkIfDateHasCost(date, split))
				return priceLevels.get(timeRange);
		}
		throw new NullPointerException(String.format("Price for date: %s was not found!", date));
	}

	private Map<String, PriceLevelTypes> getPricesFromDatabase() throws SQLException, FileNotFoundException,
			IOException {
		log.debug("loading prices");
		ResultSet resultSet = getData();

		Map<String, PriceLevelTypes> result = new ConcurrentHashMap<>();

		while (resultSet.next()) {
			int index = resultSet.getInt("priceLevel");
			PriceLevelTypes type = PriceLevelTypes.values()[index];
			String timeRange = resultSet.getString("timeRange");

			result.put(timeRange, type);
			log.debug(String.format("entry added to priceMap, timeRange: %s priceLevel: %s", timeRange, type));
		}

		log.debug("prices found: " + result.size());
		return result;
	}

	private ResultSet getData() throws SQLException, FileNotFoundException, IOException {
		log.debug("getting resultSet");

		Connection connection = CartollConnectionHandler.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(DatabaseStringConstants.PRICE_LEVELS_SQL_QUERY.toString());

		log.debug("resultSet found: " + resultSet);
		return resultSet;
	}

}
