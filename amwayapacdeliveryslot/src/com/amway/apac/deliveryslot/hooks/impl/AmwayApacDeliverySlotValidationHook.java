package com.amway.apac.deliveryslot.hooks.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.enums.OrderType;
import com.amway.apac.deliveryslot.constants.AmwayapacdeliveryslotConstants;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.orders.AmwayApacCommerceCartModificationStatus;
import com.amway.apac.deliveryslot.services.AmwayApacDeliveryService;


/**
 * Delivery slot validation hook default implementation to validate slot changes.
 *
 * @author Ashish Sabal
 */
public class AmwayApacDeliverySlotValidationHook implements CartValidationHook
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayApacDeliverySlotValidationHook.class);

	/** The Constant String CART PARAM. */
	private static final String CART_PARAM = "Cart Parameter";

	/** The amway apac delivery service. */
	private AmwayApacDeliveryService amwayApacDeliveryService;

	/** The model service. */
	private ModelService modelService;

	/**
	 * Blank method stub
	 */
	@Override
	public void beforeValidateCart(final CommerceCartParameter parameter, final List<CommerceCartModification> modifications)
	{
		// Blank method stub
	}

	/**
	 * After validate cart.
	 *
	 * @param parameter
	 *           the parameter
	 * @param modifications
	 *           the modifications
	 */
	@Override
	public void afterValidateCart(final CommerceCartParameter parameter, final List<CommerceCartModification> modifications)
	{
		validateParameterNotNullStandardMessage(CART_PARAM, parameter);

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
					createCommerceModificationsForSlotsFound(deliverySlotsFound, cartModel, modifications);
				}
				else
				{
					LOGGER.info(
							new StringBuilder(50).append("No delivery Slot found for cart: ").append(cartModel.getCode()).toString());
				}
			}
		}
	}

	/**
	 * Creates the modifications for slots found.
	 *
	 * @param deliverySlotsFound
	 *           the delivery slots found
	 * @param cartModel
	 *           the cart model
	 * @param modifications
	 *           the modifications
	 */
	protected void createCommerceModificationsForSlotsFound(final List<AmwayDeliverySlotAvailabilityModel> deliverySlotsFound,
			final CartModel cartModel, final List<CommerceCartModification> modifications)
	{
		final int filledSlots = (int) deliverySlotsFound.stream().filter(slotAvailable -> isFilled(slotAvailable)).count();
		if (filledSlots == deliverySlotsFound.size())
		{
			modifications.add(createAddToCartResp(AmwayApacCommerceCartModificationStatus.NO_DELIVERY_SLOT_EMPTY_ERROR,
					(CartEntryModel) cartModel.getEntries().iterator().next(), AmwayapacdeliveryslotConstants.ZERO_INT.intValue()));
		}
		else if (cartModel.getSelectedDeliverySlot() == null)
		{
			modifications.add(createAddToCartResp(AmwayApacCommerceCartModificationStatus.NO_DELIVERY_SLOT_ERROR,
					(CartEntryModel) cartModel.getEntries().iterator().next(), AmwayapacdeliveryslotConstants.ZERO_INT.intValue()));
		}
		else
		{
			final Date deliveryDate = getAmwayApacDeliveryService().getDeliveryDate(cartModel.getWarehouse());
			final AmwayDeliverySlotAvailabilityModel slot = getAmwayApacDeliveryService().getDeliverySlot(cartModel.getWarehouse(),
					deliveryDate, cartModel.getSelectedDeliverySlot().getSlotTime());
			if (null != slot && !slot.equals(cartModel.getSelectedDeliverySlot()))
			{
				modifications.add(createAddToCartResp(AmwayApacCommerceCartModificationStatus.INVALID_DELIVERY_SLOT_ERROR,
						(CartEntryModel) cartModel.getEntries().iterator().next(), AmwayapacdeliveryslotConstants.ZERO_INT.intValue()));
				cartModel.setSelectedDeliverySlot(null);
				getModelService().save(cartModel);
			}
			else if (null == slot)
			{
				modifications.add(createAddToCartResp(AmwayApacCommerceCartModificationStatus.INVALID_DELIVERY_SLOT_ERROR,
						(CartEntryModel) cartModel.getEntries().iterator().next(), AmwayapacdeliveryslotConstants.ZERO_INT.intValue()));
				cartModel.setSelectedDeliverySlot(null);
				getModelService().save(cartModel);
			}
		}
	}

	/**
	 * Creates commerce cart modification for cart.
	 *
	 * @param status
	 *           the status
	 * @param entry
	 *           the entry
	 * @param quantityAdded
	 *           the quantity added
	 * @return the commerce cart modification
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
	 * Checks if slot is filled.
	 *
	 * @param deliverySlotModel
	 *           the delivery slot model
	 * @return true, if slot is filled
	 */
	protected boolean isFilled(final AmwayDeliverySlotAvailabilityModel deliverySlotModel)
	{
		return deliverySlotModel.getSlotCapacity().intValue() - deliverySlotModel.getConsumedCount().intValue() == 0;
	}

	/**
	 * Gets the model service.
	 *
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * Sets the model service.
	 *
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * Gets the amway apac delivery service.
	 *
	 * @return the amwayApacDeliveryService
	 */
	public AmwayApacDeliveryService getAmwayApacDeliveryService()
	{
		return amwayApacDeliveryService;
	}

	/**
	 * Sets the amway apac delivery service.
	 *
	 * @param amwayApacDeliveryService
	 *           the amwayApacDeliveryService to set
	 */
	@Required
	public void setAmwayApacDeliveryService(final AmwayApacDeliveryService amwayApacDeliveryService)
	{
		this.amwayApacDeliveryService = amwayApacDeliveryService;
	}
}
