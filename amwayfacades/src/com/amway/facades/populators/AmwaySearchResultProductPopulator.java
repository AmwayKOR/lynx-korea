/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package com.amway.facades.populators;

import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.order.data.AmwayValueData;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator;
import de.hybris.platform.commerceservices.promotion.CommercePromotionService;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.ProductFixedPricePromotionModel;
import de.hybris.platform.promotions.model.PromotionPriceRowModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AmwaySearchResultProductPopulator extends SearchResultProductPopulator
{
	private static final Logger LOG = Logger.getLogger(AmwaySearchResultProductPopulator.class);

	private UserService userService;
	private CommercePromotionService commercePromotionService;

	@Override
	public void populate(final SearchResultValueData source, final ProductData target)
	{
		super.populate(source, target);

		target.setAlias(this.<String>getValue(source, "alias"));

		final Double retailPriceValue = this.<Double>getValue(source, "priceValue");
		final Double priceValue = this.<Double>getValue(source, "aboPriceValue");
		final Double pointValue = this.<Double>getValue(source, "pointValue");
		final Double businessVolume = this.<Double>getValue(source, "businessVolume");

		if (AmwayCustomerHelper.isABOCustomer())
		{
			if (priceValue != null)
			{
				final PriceData priceData = getPriceDataFactory()
						.create(PriceDataType.BUY, BigDecimal.valueOf(priceValue.doubleValue()),
								getCommonI18NService().getCurrentCurrency());

				AmwayValueData valueData = new AmwayValueData();
				valueData.setPointValue(pointValue == null ? new Double(0.0) : pointValue);
				valueData.setBusinessVolume(businessVolume == null ? new Double(0.0) : businessVolume);
				priceData.setAmwayValue(valueData);
				target.setPrice(priceData);
			}

			if (retailPriceValue != null)
			{
				final PriceData retailPriceData = getPriceDataFactory()
						.create(PriceDataType.BUY, BigDecimal.valueOf(retailPriceValue.doubleValue()),
								getCommonI18NService().getCurrentCurrency());
				target.setRetailPrice(retailPriceData);
			}
		}

		final List<String> brands = this.<List<String>> getValue(source, "brand");
		if (brands != null && !brands.isEmpty())
		{
			target.setProductBrand(String.join(",", brands));
		}
	}

	@Override
	protected void populatePromotions(final SearchResultValueData source, final ProductData target)
	{
		final String promotionCode = this.<String>getValue(source, "primaryPromotionCode");

		if (StringUtils.isEmpty(promotionCode))
		{
			return;
		}

		AbstractPromotionModel promoModel = getCommercePromotionService().getPromotion(promotionCode);
		PromotionData promotionData = createPromotionData(promotionCode);

		if (promoModel.getName() != null)
		{
			promotionData.setName(promoModel.getName());
		}

		target.setPotentialPromotions(Collections.singletonList(promotionData));

		if (promoModel instanceof ProductFixedPricePromotionModel)
		{

			final ProductFixedPricePromotionModel fixedPricePromotion = (ProductFixedPricePromotionModel) promoModel;
			final Collection<PromotionPriceRowModel> priceRows = fixedPricePromotion.getProductFixedUnitPrice();

			final CurrencyModel currency = getCommonI18NService().getCurrentCurrency();

			final PriceData priceData = new PriceData();
			final PriceData priceTotalData = new PriceData();

			for (final PromotionPriceRowModel promotionPrice : priceRows)
			{
				if (promotionPrice.getCurrency().getPk().compareTo(currency.getPk()) == 0)
				{
					if (AmwayCustomerHelper.isABOCustomer())
					{
						AmwayValueData valueData = new AmwayValueData();
						// TODO
						//valueData.setPointValue(promotionPrice.getPointValue());
						//valueData.setBusinessVolume(promotionPrice.getBusinessVolume());
						priceData.setAmwayValue(valueData);
					}

					priceData.setCurrencyIso(promotionPrice.getCurrency().getIsocode());
					priceData.setFormattedValue(promotionPrice.getCurrency().getSymbol() + promotionPrice.getPrice().toString());
					priceData.setValue(BigDecimal.valueOf(promotionPrice.getPrice()));
				}
			}

			promotionData.setPromotionPrice(priceData);
		}
	}

	protected PromotionData createPromotionData(final String code)
	{
		final PromotionData promotionData = createPromotionData();
		promotionData.setCode(code);

		return promotionData;
	}

	protected void populateStock(final SearchResultValueData source, final ProductData target)
	{

		//        for(String key :source.getValues().keySet() ) {
		//            LOG.info(key);
		//            LOG.info(source.getValues().get(key));
		//        }

		final String stockLevelStatus = this.<String>getValue(source, "stockLevelStatus");
		if (StringUtils.isNotEmpty(stockLevelStatus))
		{
			final StockLevelStatus stockLevelStatusEnum = StockLevelStatus.valueOf(stockLevelStatus);

			if (StockLevelStatus.LOWSTOCK.equals(stockLevelStatusEnum))
			{
				try
				{
					// In case of low stock then make a call to the stock service to determine if in or out of stock.
					// In this case (low stock) it is ok to load the product from the DB and do the real stock check
					final ProductModel productModel = getProductService().getProductForCode(target.getCode());
					if (productModel != null)
					{
						target.setStock(getStockConverter().convert(productModel));
					}
				}
				catch (final UnknownIdentifierException ex)
				{
					// If the product is no longer visible to the customergroup then this exception can be thrown

					// We can't remove the product from the results, but we can mark it as out of stock
					LOG.info("Exception on product stock check for product " + target.getCode() +
								". Got this error: " + ex.getMessage() + "  Setting to out-of-stock.", ex);
					target.setStock(getStockLevelStatusConverter().convert(StockLevelStatus.OUTOFSTOCK));
				}
			}
			else
			{
				target.setStock(getStockLevelStatusConverter().convert(stockLevelStatusEnum));
			}
		}
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public CommercePromotionService getCommercePromotionService()
	{
		return commercePromotionService;
	}

	@Required
	public void setCommercePromotionService(final CommercePromotionService commercePromotionService)
	{
		this.commercePromotionService = commercePromotionService;
	}
}
