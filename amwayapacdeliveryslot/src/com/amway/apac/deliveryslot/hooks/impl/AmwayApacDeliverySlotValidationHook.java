/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.apac.deliveryslot.hooks.impl;

import de.hybris.platform.commerceservices.model.PickUpDeliveryModeModel;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.commerceservices.strategies.hooks.CartValidationHook;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.enums.OrderType;
import com.amway.apac.core.orders.AmwayApacCommerceCartModificationStatus;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.services.AmwayApacDeliveryService;


/**
 * Delivery slot validation hook default implementation to validate slot changes
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacDeliverySlotValidationHook implements CartValidationHook
{
	private static final Logger LOG = Logger.getLogger(AmwayApacDeliverySlotValidationHook.class);

	private AmwayApacDeliveryService amwayApacDeliveryService;
	private ModelService modelService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.commerceservices.strategies.hooks.CartValidationHook#beforeValidateCart(de.hybris.platform.
	 * commerceservices.service.data.CommerceCartParameter, java.util.List)
	 */
	@Override
	public void beforeValidateCart(final CommerceCartParameter parameter, final List<CommerceCartModification> modifications)
	{
		// YTODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commerceservices.strategies.hooks.CartValidationHook#afterValidateCart(de.hybris.platform.
	 * commerceservices.service.data.CommerceCartParameter, java.util.List)
	 */
	@Override
	public void afterValidateCart(final CommerceCartParameter parameter, final List<CommerceCartModification> modifications)
	{
		final CartModel cartModel = parameter.getCart();

		if (cartModel != null && CollectionUtils.isNotEmpty(cartModel.getEntries()))
		{
			if ((cartModel.getDeliveryMode() != null) && (!(cartModel.getDeliveryMode() instanceof PickUpDeliveryModeModel))
					&& !CollectionUtils.isEmpty(cartModel.getEntries()) && !OrderType.REGISTRATION.equals(cartModel.getOrderType()))
			{
				final List<AmwayDeliverySlotAvailabilityModel> deliverySlotsFound = getAmwayApacDeliveryService()
						.getDeliverySlotsAvailability();

				if (CollectionUtils.isNotEmpty(deliverySlotsFound))
				{
					final int filledSlots = (int) deliverySlotsFound.stream().filter(slotAvailable -> isFilled(slotAvailable)).count();
					if (filledSlots == deliverySlotsFound.size())
					{
						modifications.add(createAddToCartResp(AmwayApacCommerceCartModificationStatus.NO_DELIVERY_SLOT_EMPTY_ERROR,
								(CartEntryModel) cartModel.getEntries().iterator().next(), 0));
					}
					else if (cartModel.getSelectedDeliverySlot() == null)
					{
						modifications.add(createAddToCartResp(AmwayApacCommerceCartModificationStatus.NO_DELIVERY_SLOT_ERROR,
								(CartEntryModel) cartModel.getEntries().iterator().next(), 0));
					}
					else
					{
						final Date deliveryDate = getAmwayApacDeliveryService().getDeliveryDate(cartModel.getWarehouse());
						final AmwayDeliverySlotAvailabilityModel slot = getAmwayApacDeliveryService().getDeliverySlot(
								cartModel.getWarehouse(), deliveryDate, cartModel.getSelectedDeliverySlot().getSlotTime());
						if (null != slot && !slot.equals(cartModel.getSelectedDeliverySlot()))
						{
							modifications.add(createAddToCartResp(AmwayApacCommerceCartModificationStatus.INVALID_DELIVERY_SLOT_ERROR,
									(CartEntryModel) cartModel.getEntries().iterator().next(), 0));
							cartModel.setSelectedDeliverySlot(null);
							getModelService().save(cartModel);
						}
						else if (null == slot)
						{
							modifications.add(createAddToCartResp(AmwayApacCommerceCartModificationStatus.INVALID_DELIVERY_SLOT_ERROR,
									(CartEntryModel) cartModel.getEntries().iterator().next(), 0));
							cartModel.setSelectedDeliverySlot(null);
							getModelService().save(cartModel);
						}
					}
				}
				else
				{
					LOG.info("No delivery Slot found for cart: " + cartModel.getCode());
				}
			}
		}
	}

	/**
	 * Creates commerce cart modification for cart
	 *
	 * @param status
	 * @param entry
	 * @param quantityAdded
	 * @return
	 */
	protected CommerceCartModification createAddToCartResp(final String status, final CartEntryModel entry,
			final long quantityAdded)
	{

		final CommerceCartModification modification = new CommerceCartModification();
		modification.setStatusCode(status);
		modification.setQuantityAdded(quantityAdded);

		modification.setEntry(entry);

		return modification;
	}

	/**
	 * @param deliverySlotModel
	 * @return
	 */
	private boolean isFilled(final AmwayDeliverySlotAvailabilityModel deliverySlotModel)
	{
		return deliverySlotModel.getSlotCapacity().intValue() - deliverySlotModel.getConsumedCount().intValue() == 0;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the amwayApacDeliveryService
	 */
	public AmwayApacDeliveryService getAmwayApacDeliveryService()
	{
		return amwayApacDeliveryService;
	}

	/**
	 * @param amwayApacDeliveryService
	 *           the amwayApacDeliveryService to set
	 */
	@Required
	public void setAmwayApacDeliveryService(final AmwayApacDeliveryService amwayApacDeliveryService)
	{
		this.amwayApacDeliveryService = amwayApacDeliveryService;
	}
}
