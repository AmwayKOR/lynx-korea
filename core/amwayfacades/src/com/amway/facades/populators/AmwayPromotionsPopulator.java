package com.amway.facades.populators;

import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.order.data.AmwayValueData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.ProductFixedPricePromotionModel;
import de.hybris.platform.promotions.model.PromotionPriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Required;

import java.math.BigDecimal;
import java.util.Collection;


/**
 * @author Anatoli_Levakou
 */
public class AmwayPromotionsPopulator implements Populator<AbstractPromotionModel, PromotionData>
{
	private CommonI18NService commonI18NService;
	private UserService userService;

	@Override
	public void populate(final AbstractPromotionModel source, final PromotionData target) throws ConversionException
	{

		if (source instanceof ProductFixedPricePromotionModel)
		{
			final ProductFixedPricePromotionModel fixedPricePromotion = (ProductFixedPricePromotionModel) source;
			final Collection<PromotionPriceRowModel> priceRows = fixedPricePromotion.getProductFixedUnitPrice();

			final CurrencyModel currency = commonI18NService.getCurrentCurrency();

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
					priceTotalData.setCurrencyIso(promotionPrice.getCurrency().getIsocode());
					priceTotalData.setValue(BigDecimal.valueOf(promotionPrice.getPrice()));
					priceTotalData.setFormattedValue(promotionPrice.getCurrency().getSymbol() + promotionPrice.getPrice().toString());
				}
			}

			target.setPromotionPrice(priceData);
			target.setTotalPrice(priceTotalData);
		}
		if (source.getName() != null)
		{
			target.setName(source.getName());
		}
	}

	protected CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}
