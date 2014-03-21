package se.fidde.cartoll.jar.util.factories;

import java.io.IOException;

import se.fidde.cartoll.jar.domain.price.DatabasePriceLoaderImpl;
import se.fidde.cartoll.jar.domain.price.LocalPriceLoaderImpl;
import se.fidde.cartoll.jar.domain.price.PriceLoader;
import se.fidde.cartoll.jar.domain.price.PriceLoaderType;

/**
 * @author fidde factory for getting a price loader
 * 
 */
public class PriceLoaderFactory {

	public static PriceLoader getPriceLevelLoader(PriceLoaderType loader) throws IOException {

		switch (loader) {
		case DATABASE:
			try {
				return new DatabasePriceLoaderImpl();

			} catch (Exception e) {
				return new LocalPriceLoaderImpl();
			}

		default:
			return new LocalPriceLoaderImpl();
		}
	}

}
