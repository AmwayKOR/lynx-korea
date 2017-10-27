package com.amway.apac.facades.product.populators;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.util.Config;

import java.util.Collection;

import com.amway.apac.facades.product.AmwayApacPriceDataFactory;
import com.amway.facades.populators.AmwayProductPricePopulator;
import org.springframework.beans.factory.annotation.Required;


/**
 * Created by MY020221 on 9/28/2017.
 *
 * Overriding populate() of AmwayProductPricePopulator to get ABO Price and Retail Price for APAC
 */
public class AmwayApacProductPricePopulator extends AmwayProductPricePopulator
{
	protected static final String RETAIL_PRICE_GROUP = "amwayapaccore.defaultRetailPriceGroup";

	private SessionService sessionService;
	private AmwayApacPriceDataFactory amwayApacPriceDataFactory;

	/**
	 * This method is used to get PriceRow of a given group
	 * @param productModel This is the ProductModel
	 * @param group This is the group name
	 * @return PriceRowModel This returns the PriceRowModel of a given group
	 */
	protected PriceRowModel getPriceRow(final ProductModel productModel, final String group)
	{
		return sessionService.executeInLocalView(new SessionExecutionBody() {
			@Override
			public PriceRowModel execute() {
				final Collection<PriceRowModel> prices = productModel.getEurope1Prices();
				final PriceRowModel priceRow = prices.stream().filter(p -> group.equalsIgnoreCase(p.getUg().getCode())).findFirst().get();
				return priceRow;
			}
		});
	}

	/**
	 * This method is used to populate ABO Price and Retail Price into ProductData for
	 * @param productModel This is the Product Model
	 * @param productData This is the Product Data
	 * @throws ConversionException
	 */
	@Override
	public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
	{
		final PriceRowModel priceRow = getPriceRow(productModel, Config.getParameter(DISTRIBUTOR_PRICE_GROUP));
		if (priceRow != null)
		{
			final PriceData priceData = amwayApacPriceDataFactory.create(priceRow);
			productData.setPrice(priceData);
		}

		final PriceRowModel retailPriceRow = getPriceRow(productModel, Config.getParameter(RETAIL_PRICE_GROUP));
		if (retailPriceRow != null)
		{
			final PriceData retailPriceData = amwayApacPriceDataFactory.create(retailPriceRow);
			productData.setRetailPrice(retailPriceData);
		}
	}

	public AmwayApacPriceDataFactory getAmwayApacPriceDataFactory()
	{
		return amwayApacPriceDataFactory;
	}

	@Required
	public void setAmwayApacPriceDataFactory(final AmwayApacPriceDataFactory amwayApacPriceDataFactory)
	{
		this.amwayApacPriceDataFactory = amwayApacPriceDataFactory;
	}

	@Override
	public SessionService getSessionService() {
		return sessionService;
	}

	@Override
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}
}
