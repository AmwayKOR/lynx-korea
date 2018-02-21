package com.amway.facades.populators;

import com.amway.core.service.AmwayAccountCommerceService;
import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.order.data.AmwayValueData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author Viktar_Yarmalovich on 5/4/2015.
 */
public class AmwayCartPricePopulator implements Populator<CartModel, CartData>
{
	private CommercePriceService commercePriceService;
	private ModelService modelService;
	private UserService userService;

	public AmwayAccountCommerceService getAccountCommerceService() {
		return accountCommerceService;
	}

	public void setAccountCommerceService(AmwayAccountCommerceService accountCommerceService) {
		this.accountCommerceService = accountCommerceService;
	}

	private AmwayAccountCommerceService accountCommerceService;

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

	protected PriceRowModel getPriceRow(ProductModel product)
	{
		PriceInformation info;
		if (CollectionUtils.isEmpty(product.getVariants()))
		{

			info = this.getCommercePriceService().getWebPriceForProduct(product);
		}
		else
		{
			info = this.getCommercePriceService().getFromPriceForProduct(product);
		}
		final PriceRow priceRow = (PriceRow) info.getQualifierValue("pricerow");

		return (PriceRowModel) getModelService().get(priceRow.getPK());
	}

	protected PriceInformation getPriceInfo(ProductModel product)
	{
		PriceInformation info;
		if (CollectionUtils.isEmpty(product.getVariants()))
		{
			info = this.getCommercePriceService().getWebPriceForProduct(product);
		}
		else
		{
			info = this.getCommercePriceService().getFromPriceForProduct(product);
		}

		return info;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public void populate(CartModel cartModel, CartData cartData) throws ConversionException
	{
		if (AmwayCustomerHelper.isABOCustomer())
		{
			getAccountCommerceService().setUPGInSession();

			for (AbstractOrderEntryModel entry : cartModel.getEntries())
			{
				for (OrderEntryData entryData : cartData.getEntries())
				{
					if (entryData.getProduct().getCode().equalsIgnoreCase(entry.getProduct().getCode()))
					{
						//final PriceRowModel priceRow = getPriceRow(entry.getProduct());
						final PriceInformation info = getPriceInfo(entry.getProduct());
						if (info == null)
						{
							continue;
						}
						PriceData entryBasePrice = entryData.getBasePrice();
						AmwayValueData baseValueData = new AmwayValueData();

						final Double businessVolume = (Double) info.getQualifierValue("businessVolume");
						final Double pointValue = (Double) info.getQualifierValue("pointValue");

						baseValueData.setBusinessVolume(businessVolume);
						baseValueData.setPointValue(pointValue);
						entryBasePrice.setAmwayValue(baseValueData);
						PriceData entryTotalPrice = entryData.getTotalPrice();
						AmwayValueData valueData = entryData.getValue();
						entryTotalPrice.setAmwayValue(valueData);
					}
				}
			}
		}
	}
}
