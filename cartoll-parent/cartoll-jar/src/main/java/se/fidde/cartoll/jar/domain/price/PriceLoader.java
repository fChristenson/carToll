package se.fidde.cartoll.jar.domain.price;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * @author fidde interface for geting pricelevels into memory
 * 
 */
public interface PriceLoader {

	public PriceLevelTypes getPriceLevelFor(Date date) throws FileNotFoundException, IOException;
}
