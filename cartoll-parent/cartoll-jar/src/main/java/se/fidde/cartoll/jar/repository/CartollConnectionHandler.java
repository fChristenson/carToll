package se.fidde.cartoll.jar.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import se.fidde.cartoll.jar.util.constants.RelativeUrl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class CartollConnectionHandler {

	private static String password;
	private static String username;
	private static String databaseName;
	private static String host;
	private static Properties properties;
	private static Logger log = Logger.getLogger(CartollConnectionHandler.class);

	public static Connection getConnection() throws SQLException, IOException {
		loadProperties();
		setConnectionVariables();

		log.debug("creating MysqlDataSource");
		MysqlDataSource ds = new MysqlDataSource();

		ds.setServerName(host);
		ds.setDatabaseName(databaseName);
		ds.setUser(username);
		ds.setPassword(password);

		return ds.getConnection();
	}

	private static void setConnectionVariables() {
		log.debug("setting connection vars");

		username = properties.getProperty(DatabaseStringConstants.USER.toString());
		password = properties.getProperty(DatabaseStringConstants.PASSWORD.toString());
		databaseName = properties.getProperty(DatabaseStringConstants.DATABASE_NAME.toString());
		host = properties.getProperty(DatabaseStringConstants.HOST.toString());
	}

	private static void loadProperties() throws IOException {
		log.debug("loading properties");
		if (properties == null)
			properties = new Properties();

		InputStream inStream = CartollConnectionHandler.class.getResourceAsStream(RelativeUrl.DATABASE_PROPERTIES
				.toString());
		properties.load(inStream);
	}

}
