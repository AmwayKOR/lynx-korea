package com.amway.apac.facades.product.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.util.Config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.facades.product.AmwayApacPriceDataFactory;
import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.order.data.AmwayValueData;
import com.amway.facades.populators.AmwayProductPricePopulator;


/**
 * Overriding {@link AmwayApacProductPricePopulator} to get ABO Price and Retail Price for APAC
 *
 * @author Govind
 */
public class AmwayApacProductPricePopulator extends AmwayProductPricePopulator
{
	/**
	 * Retail price group
	 */
	protected static final String RETAIL_PRICE_GROUP = "amwayapaccore.defaultRetailPriceGroup";

	private AmwayApacPriceDataFactory amwayApacPriceDataFactory;

	/**
	 * This method is used to get PriceRow of a given group
	 *
	 * @param productModel
	 *           This is the ProductModel
	 * @param group
	 *           This is the group name
	 * @return PriceRowModel This returns the PriceRowModel of a given group
	 */
	protected PriceRowModel getPriceRow(final ProductModel productModel, final String group)
	{
		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public PriceRowModel execute()
			{
				PriceRowModel priceRow = null;
				if (!getUserService().isAnonymousUser(getUserService().getCurrentUser()))
				{
					final Collection<PriceRowModel> prices = productModel.getEurope1Prices();
					priceRow = prices.stream().filter(p -> group.equalsIgnoreCase(p.getUg().getCode())).findFirst().get();
				}
				return priceRow;
			}
		});
	}

	/**
	 * This method is used to populate ABO Price and Retail Price into ProductData for
	 *
	 * @param productModel
	 *           This is the Product Model
	 * @param productData
	 *           This is the Product Data
	 * @throws ConversionException
	 */
	@Override
	public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
	{
		final PriceRowModel priceRow = getPriceRow(productModel);
		if (priceRow != null && AmwayCustomerHelper.isABOCustomer())
		{
			final AmwayValueData valueData = new AmwayValueData();
			valueData.setBusinessVolume(priceRow.getBusinessVolume());
			valueData.setPointValue(priceRow.getPointValue());
			if (productData.getPrice() != null)
			{
				productData.getPrice().setAmwayValue(valueData);
			}

			final PriceRowModel retailPriceRow = getPriceRow(productModel, Config.getParameter(RETAIL_PRICE_GROUP));
			if (null != retailPriceRow)
			{
				productData.setRetailPrice(getAmwayApacPriceDataFactory().create(retailPriceRow));
			}
		}
	}

	/**
	 *
	 * @return the amwayApacPriceDataFactory
	 */
	public AmwayApacPriceDataFactory getAmwayApacPriceDataFactory()
	{
		return amwayApacPriceDataFactory;
	}

	/**
	 *
	 * @param amwayApacPriceDataFactory
	 *           the amwayApacPriceDataFactory to set
	 */
	@Required
	public void setAmwayApacPriceDataFactory(final AmwayApacPriceDataFactory amwayApacPriceDataFactory)
	{
		this.amwayApacPriceDataFactory = amwayApacPriceDataFactory;
	}

}
