package com.amway.apac.facades.product.populators;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.product.converters.populator.StockPopulator;
import de.hybris.platform.commercefacades.product.data.StockData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.beans.factory.annotation.Required;


/**
 * Overriding OOTB {@link StockPopulator} to populate additional attributes
 *
 * @author Parvesh Goyal
 *
 */
public class AmwayApacStockPopulator extends StockPopulator<ProductModel, StockData>
{
	private EnumerationService enumerationService;

	/**
	 * Overriding OOTB implementation to populate additional attributes
	 */
	@Override
	public void populate(final ProductModel productModel, final StockData stockData) throws ConversionException
	{
		validateParameterNotNullStandardMessage("productModel", productModel);
		validateParameterNotNullStandardMessage("stockData", stockData);

		super.populate(productModel, stockData);

		if (null != stockData.getStockLevelStatus())
		{
			stockData.setStockLevelStatusName(getEnumerationService().getEnumerationName(stockData.getStockLevelStatus()));
		}
	}

	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	/**
	 * @param enumerationService
	 *           the enumerationService to set
	 */
	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

}
