package com.amway.facades.populators;

import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.order.data.AmwayValueData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;
import org.springframework.beans.factory.annotation.Required;

import java.math.BigDecimal;
import java.util.Collection;


/**
 * @author Viktar_Yarmalovich on 4/30/2015.
 */
public class AmwayProductPricePopulator implements Populator<ProductModel, ProductData>
{
	protected static final String DISTRIBUTOR_PRICE_GROUP = "amwaycore.defaultABOPriceGroup";

	private CommercePriceService commercePriceService;
	private ModelService modelService;
	private SessionService sessionService;
	private UserService userService;

	protected CommercePriceService getCommercePriceService()
	{
		return commercePriceService;
	}

	@Required
	public void setCommercePriceService(final CommercePriceService commercePriceService)
	{
		this.commercePriceService = commercePriceService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected PriceRowModel getPriceRow(final ProductModel product)
	{
		return sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public PriceRowModel execute()
			{
				if (!userService.isAnonymousUser(userService.getCurrentUser()))
				{
					final Collection<PriceRowModel> prices = product.getEurope1Prices();
					for (final PriceRowModel priceRow : prices)
					{
						final HybrisEnumValue ug = priceRow.getUg();
						if (ug != null && Config.getParameter(DISTRIBUTOR_PRICE_GROUP).equalsIgnoreCase(ug.getCode()))
						{
							return priceRow;
						}
					}
				}
				return null;
			}
		});
	}

	protected PriceRowModel getRetailPriceRow(final ProductModel product)
	{
		return sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public PriceRowModel execute()
			{
				if (!userService.isAnonymousUser(userService.getCurrentUser()))
				{
					final Collection<PriceRowModel> prices = product.getEurope1Prices();
					for (final PriceRowModel priceRow : prices)
					{
						final HybrisEnumValue ug = priceRow.getUg();
						if (ug == null)
						{
							return priceRow;
						}
					}
				}
				return null;
			}
		});
	}


	@Override
	public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
	{
		final PriceRowModel priceRow = getPriceRow(productModel);
		if (priceRow != null && AmwayCustomerHelper.isABOCustomer())
		{
			AmwayValueData valueData = new AmwayValueData();
			valueData.setBusinessVolume(new Double(priceRow.getBusinessVolume()));
			valueData.setPointValue(new Double(priceRow.getPointValue()));
			final PriceRowModel retailPriceRow = getRetailPriceRow(productModel);
			if (productData.getPrice() != null)
			{
				productData.getPrice().setAmwayValue(valueData);
			}
			productData.setRetailPrice(new PriceData());
			if (retailPriceRow != null && retailPriceRow.getPrice() != null)
			{
				productData.getRetailPrice().setValue(BigDecimal.valueOf(retailPriceRow.getPrice()));
				productData.getRetailPrice().setCurrencyIso(retailPriceRow.getCurrency().getIsocode());
				productData.getRetailPrice().setFormattedValue(
						retailPriceRow.getCurrency().getSymbol() + retailPriceRow.getPrice().toString());
			}
		}
	}
}
