package com.amway.apac.facades.product.populators;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commercefacades.product.data.StockData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * Populator to populate {@link StockLevelStatus} in {@link StockData}
 *
 * @author Shubham Goyal
 */
public class AmwayApacStockLevelStatusPopulator implements Populator<StockLevelStatus, StockData>
{

	/**
	 * Populates {@link StockData} as per APAC requirements for source {@link StockLevelStatus}.
	 */
	@Override
	public void populate(final StockLevelStatus source, final StockData target) throws ConversionException
	{
		target.setStockLevelStatus(source);
		if (StockLevelStatus.OUTOFSTOCK.equals(source) || StockLevelStatus.TEMPORARYNOTAVAILABLE.equals(source)
				|| StockLevelStatus.NOLONGERAVAILABLE.equals(source) || StockLevelStatus.NOTYETAVAILABLE.equals(source))
		{
			target.setStockLevel(Long.valueOf(0));
		}
		else if (StockLevelStatus.INSTOCK.equals(source) || StockLevelStatus.BACKORDER.equals(source)
				|| StockLevelStatus.SHIPPED.equals(source))
		{
			target.setStockLevel(null);
		}
	}

}
