package com.amway.facades.populators;

import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.order.data.AmwayValueData;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.DeliveryOrderEntryGroupData;
import de.hybris.platform.commercefacades.order.data.PromotionOrderEntryConsumedData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.PromotionResultData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.L10NService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.util.TaxValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.amway.facades.order.data.AmwayValueData;


/**
 * @author Aryadna_Birchanka
 */
public class AmwayCartPopulator implements Populator<AbstractOrderModel, AbstractOrderData>
{
	private L10NService l10NService;

	private UserService userService;

	@Autowired
	private PriceDataFactory priceDataFactory;
	public static final String FIXED_PROMOTION_TYPE_KEY = "type.productfixedpricepromotion.name";

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public L10NService getL10NService()
	{
		return l10NService;
	}

	@Required
	public void setL10NService(L10NService l10NService)
	{
		this.l10NService = l10NService;
	}

	@Override
	public void populate(AbstractOrderModel orderModel, AbstractOrderData orderData)
	{
		if (AmwayCustomerHelper.isABOCustomer((CustomerModel) orderModel.getUser()))
		{
			final AmwayValueData valueData = new AmwayValueData();
			valueData.setBusinessVolume(orderModel.getBusinessVolume());
			valueData.setPointValue(orderModel.getPointValue());

			orderData.getSubTotal().setAmwayValue(valueData);
			final PriceData totalPrice = orderData.getTotalPrice();
			final PriceData totalPriceWithTax = orderData.getTotalPriceWithTax();
			totalPrice.setAmwayValue(valueData);
			totalPriceWithTax.setAmwayValue(valueData);
			for (DeliveryOrderEntryGroupData groupData : orderData.getDeliveryOrderGroups())
			{
				groupData.setTotalPriceWithTax(totalPriceWithTax);
			}
			if (orderData.getAppliedProductPromotions() != null)
			{
				for (PromotionResultData promotionResultData : orderData.getAppliedProductPromotions())
				{
					String promotionType = promotionResultData.getPromotionData().getPromotionType();
					String localizedProductFixedPromotionType = l10NService.getLocalizedString(FIXED_PROMOTION_TYPE_KEY);
					if (!localizedProductFixedPromotionType.equals(promotionType))
					{
						break;
					}
					PriceData totalPromotionPrice = promotionResultData.getPromotionData().getTotalPrice();
					PriceData promotionPrice = promotionResultData.getPromotionData().getPromotionPrice();
					AmwayValueData amwayValueData = new AmwayValueData();
					for (PromotionOrderEntryConsumedData entryConsumedData : promotionResultData.getConsumedEntries())
					{
						amwayValueData
								.setBusinessVolume(promotionPrice.getAmwayValue().getBusinessVolume() * entryConsumedData.getQuantity());
						amwayValueData.setPointValue(promotionPrice.getAmwayValue().getPointValue() * entryConsumedData.getQuantity());
						double totalPriceValue = CoreAlgorithms
								.round(promotionPrice.getValue().doubleValue() * entryConsumedData.getQuantity(), 2);
						totalPromotionPrice.setValue(BigDecimal.valueOf(totalPriceValue));
					}
					totalPromotionPrice.setCurrencyIso(promotionPrice.getCurrencyIso());
					totalPromotionPrice.setAmwayValue(amwayValueData);
					totalPromotionPrice
							.setFormattedValue(orderModel.getCurrency().getSymbol() + totalPromotionPrice.getValue().toString());
					promotionResultData.getPromotionData().setTotalPrice(totalPromotionPrice);
				}
			}

			prepareDataForCartPage(orderModel, orderData);
		}

	}

	/**
	 * Create data for cart page display
	 * 
	 * @param orderModel
	 * @param orderData
	 */
	private void prepareDataForCartPage(final AbstractOrderModel orderModel, final AbstractOrderData orderData)
	{
		// NEW CHANGES
		double subtotal = 0;
		if (orderModel.getSubtotal() != null)
		{
			subtotal = orderModel.getSubtotal().doubleValue();
		}
		final BigDecimal newSubTotal = BigDecimal.valueOf(subtotal).add(orderData.getProductDiscounts().getValue())
				.setScale(2, RoundingMode.HALF_UP);

		// Sub total without product discount
		orderData.setSubtotalWithoutProductDiscount(priceDataFactory.create(PriceDataType.BUY, newSubTotal,
				orderModel.getCurrency()));

		// Order Tax Discount
		orderData.setOrderTaxDiscount(priceDataFactory.create(PriceDataType.BUY,
				BigDecimal.valueOf(getOrderTaxDiscount(orderModel)), orderModel.getCurrency()));

		//Total tax discount at entry level
		orderData.setProductTaxDiscount(priceDataFactory.create(PriceDataType.BUY,
				BigDecimal.valueOf(getProductTaxDiscounts(orderModel)), orderModel.getCurrency()));

		// Total tax
		orderData.setTotalTaxDiscount(priceDataFactory.create(
				PriceDataType.BUY,
				orderData.getOrderTaxDiscount().getValue().add(orderData.getProductTaxDiscount().getValue())
						.setScale(2, RoundingMode.HALF_UP), orderModel.getCurrency()));

		// Total price including delivery cost
		double deliveryCost = 0;
		if (orderModel.getDeliveryCost() != null)
		{
			deliveryCost = orderModel.getDeliveryCost().doubleValue();
		}

		final BigDecimal newTotalPrice = newSubTotal.add(BigDecimal.valueOf(deliveryCost).setScale(2, RoundingMode.HALF_UP));
//		orderData.setTotalPriceWithoutDiscountExclGST(priceDataFactory.create(PriceDataType.BUY, newTotalPrice,
//				orderModel.getCurrency()));

		// Delivery Tax
		double deliveryTax = 0;
		if (orderModel.getDeliveryTax() != null)
		{
			deliveryTax = orderModel.getDeliveryTax().doubleValue();
		}
		orderData.setDeliveryTax(priceDataFactory.create(PriceDataType.BUY, BigDecimal.valueOf(deliveryTax),
				orderModel.getCurrency()));

		final BigDecimal newTotalTax = orderData.getTotalTax().getValue().add(BigDecimal.valueOf(deliveryTax))
				.setScale(2, RoundingMode.HALF_UP);
		orderData.setTotalTax(priceDataFactory.create(PriceDataType.BUY, newTotalTax, orderModel.getCurrency()));

		BigDecimal newSavings = orderData.getOrderDiscounts().getValue().add(orderData.getProductDiscounts().getValue())
				.setScale(4, RoundingMode.HALF_UP);
		newSavings = newSavings.subtract(orderData.getOrderTaxDiscount().getValue()).setScale(2, RoundingMode.HALF_UP);
		orderData.setSavings(priceDataFactory.create(PriceDataType.BUY, newSavings, orderModel.getCurrency()));
		
		final BigDecimal newTotalPriceWithDisount = newTotalPrice.subtract(newSavings.setScale(2, RoundingMode.HALF_UP));
//		orderData.setTotalPriceWithDiscountExclGST(
//				priceDataFactory.create(PriceDataType.BUY, newTotalPriceWithDisount, orderModel.getCurrency()));
		

		final BigDecimal savingsWithTax = orderData.getTotalTaxDiscount().getValue().add(orderData.getSavings().getValue()).setScale(2,
				RoundingMode.HALF_UP);
		orderData.setTotalDiscountWithTax(priceDataFactory.create(PriceDataType.BUY, savingsWithTax, orderModel.getCurrency()));

	}

	/**
	 * Get tax discount at order entry level
	 *
	 * @param cart
	 * @return
	 */
	private double getProductTaxDiscounts(final AbstractOrderModel cart)
	{
		double totalTax = 0;
		if (CollectionUtils.isNotEmpty(cart.getEntries()))
		{
			for (final AbstractOrderEntryModel orderEntry : cart.getEntries())
			{
				for (final TaxValue tempTaxValue : orderEntry.getTaxValues())
				{
					if (Double.valueOf(tempTaxValue.getValue()).compareTo(Double.valueOf(0.0)) < 0)
					{
						totalTax += tempTaxValue.getValue() * orderEntry.getQuantity().doubleValue() * -1.0D;
					}
				}

			}
		}
		return totalTax;
	}


	/**
	 * Get tax discount on order
	 *
	 * @param cart
	 * @return
	 */
	private double getOrderTaxDiscount(final AbstractOrderModel cart)
	{
		final List<DiscountValue> discountValues = cart.getGlobalDiscountValues();
		double total = 0;
		if (CollectionUtils.isNotEmpty(discountValues))
		{
			for (final DiscountValue tempValue : discountValues)
			{
				if (tempValue.getCode().endsWith("_tax"))
				{
					total += tempValue.getAppliedValue();
				}
			}
		}
		return total;
	}
	
}
