package com.amway.facades.populators;

import com.amway.core.service.AmwayAccountCommerceService;
import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.order.data.AmwayValueData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author Viktar_Yarmalovich on 5/5/2015.
 */
public class AmwayCartModificationPopulator implements Populator<CommerceCartModification, CartModificationData>
{
	private CommercePriceService commercePriceService;
	private ModelService modelService;
	private AmwayAccountCommerceService accountCommerceService;

	public SessionService getSessionService() {
		return sessionService;
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private SessionService sessionService;

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

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

	@Override
	public void populate(final CommerceCartModification source, final CartModificationData target) throws ConversionException
	{
		getAccountCommerceService().setUPGInSession();

		final PriceInformation info = getPriceInfo(source.getEntry().getProduct());

		final PriceData priceData = target.getEntry().getTotalPrice();
		if (priceData != null && AmwayCustomerHelper.isABOCustomer())
		{
			final long quantity = source.getEntry().getQuantity();

			final Double businessVolume = (Double) info.getQualifierValue("businessVolume");
			final Double pointValue = (Double) info.getQualifierValue("pointValue");

			source.getEntry().setBusinessVolume(quantity * businessVolume);
			source.getEntry().setPointValue(quantity * pointValue);

			final AmwayValueData valueData = new AmwayValueData();
			valueData.setBusinessVolume(source.getEntry().getBusinessVolume());
			valueData.setPointValue(source.getEntry().getPointValue());

			priceData.setAmwayValue(valueData);
		}

		if (source.getEntry().getPickupDateTime() != null)
		{
			target.getEntry().setPickupDateTime(source.getEntry().getPickupDateTime());
		}
	}

	public AmwayAccountCommerceService getAccountCommerceService() {
		return accountCommerceService;
	}

	public void setAccountCommerceService(AmwayAccountCommerceService accountCommerceService) {
		this.accountCommerceService = accountCommerceService;
	}
}
