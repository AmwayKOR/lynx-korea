package com.amway.apac.deliveryslot.services.impl;

import static com.amway.apac.deliveryslot.constants.AmwayapacdeliveryslotConstants.DELIVERY_SLOT;
import static com.amway.apac.deliveryslot.constants.AmwayapacdeliveryslotConstants.TIME_FORMAT;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel.DELIVERYDATE;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel.SLOTTIME;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel.WAREHOUSE;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.commerceservices.delivery.impl.DefaultDeliveryService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.deliveryslot.constants.AmwayapacdeliveryslotConstants;
import com.amway.apac.deliveryslot.daos.AmwayApacDeliverySlotDao;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.services.AmwayApacDeliveryService;
import com.amway.apac.deliveryslot.services.AmwayApacDeliverySlotManagementService;
import com.amway.core.exceptions.AmwayServiceException;


/**
 * Default implementation of {@link AmwayApacDeliveryService}.
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacDeliveryService extends DefaultDeliveryService implements AmwayApacDeliveryService
{
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);

	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacDeliveryService.class);

	/** The amway apac delivery slot dao. */
	private AmwayApacDeliverySlotDao amwayApacDeliverySlotDao;

	/** The cart service. */
	private CartService cartService;

	private AmwayApacDeliverySlotManagementService amwayApacDeliverySlotManagementService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayDeliverySlotAvailabilityModel> getDeliverySlotsAvailability()
	{
		List<AmwayDeliverySlotAvailabilityModel> slotAvailabilityModels = new ArrayList<>();
		if (getCartService().hasSessionCart())
		{
			final CartModel sessionCart = getCartService().getSessionCart();
			final WarehouseModel warehouse = sessionCart.getWarehouse();
			final Date deliveryDate = getDeliveryDate(warehouse);
			if (null != deliveryDate)
			{
				slotAvailabilityModels = getAmwayApacDeliverySlotDao().getDeliverySlotsAvailability(warehouse, deliveryDate);
			}

			clearSelectedDeliverySlotIfRequired(sessionCart);
		}
		return slotAvailabilityModels;
	}

	/**
	 * Clear selected delivery slot if required.
	 *
	 * @param cart
	 *           the cart
	 */
	protected void clearSelectedDeliverySlotIfRequired(final CartModel cart)
	{
		final AmwayDeliverySlotAvailabilityModel slot = cart.getSelectedDeliverySlot();
		if (null != slot && (slot.getSlotCapacity().intValue()
				- slot.getConsumedCount().intValue() <= AmwayapacdeliveryslotConstants.ZERO_INT.intValue()))
		{
			cart.setSelectedDeliverySlot(null);
			getModelService().save(cart);
			getModelService().refresh(cart);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getDeliveryDate(final WarehouseModel warehouse)
	{
		validateParameterNotNullStandardMessage(WAREHOUSE, warehouse);

		final Date now = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		final int dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);

		final String formattedTime = dateFormat.format(Calendar.getInstance().getTime());
		final Time orderingTime = Time.valueOf(formattedTime);

		final WeekDay[] deliveryDays = WeekDay.values();
		WeekDay deliveryDay = null;

		deliveryDay = getAmwayApacDeliverySlotDao().getDeliveryDay(warehouse,
				deliveryDays[dayofWeek - AmwayapacdeliveryslotConstants.ONE_INT.intValue()], orderingTime);

		if (null != deliveryDay)
		{
			return getDateByDay(deliveryDay, now);
		}
		return null;
	}

	/**
	 * Gets the date by day.
	 *
	 * @param deliveryDay
	 *           the delivery day
	 * @param todayDate
	 *           the today date
	 * @return the date by day
	 */
	protected Date getDateByDay(final WeekDay deliveryDay, final Date todayDate)
	{
		return java.sql.Date.valueOf(getAmwayApacDeliverySlotManagementService()
				.getDeliveryDate(todayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), deliveryDay));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer reserve(final AmwayDeliverySlotAvailabilityModel deliverySlot) throws AmwayServiceException
	{
		validateParameterNotNullStandardMessage(DELIVERY_SLOT, deliverySlot);

		getModelService().refresh(deliverySlot);
		if (deliverySlot.getSlotCapacity().intValue()
				- deliverySlot.getConsumedCount().intValue() > AmwayapacdeliveryslotConstants.ZERO_INT.intValue())
		{
			final Integer updatedConsumedSlot = Integer
					.valueOf(deliverySlot.getConsumedCount().intValue() + AmwayapacdeliveryslotConstants.ONE_INT.intValue());
			deliverySlot.setConsumedCount(updatedConsumedSlot);
			getModelService().save(deliverySlot);
			getModelService().refresh(deliverySlot);
			return deliverySlot.getConsumedCount();
		}
		else
		{
			throw new AmwayServiceException(new StringBuilder(200).append("Selected delivery slot [ ").append(deliverySlot.getPk())
					.append(" ] for time: [ ").append(deliverySlot.getSlotTime()).append(" ] not available.").toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer release(final AmwayDeliverySlotAvailabilityModel deliverySlot) throws AmwayServiceException
	{
		validateParameterNotNullStandardMessage(DELIVERY_SLOT, deliverySlot);

		getModelService().refresh(deliverySlot);
		final int updatedConsumedSlot = deliverySlot.getConsumedCount().intValue() - 1;
		deliverySlot.setConsumedCount(Integer.valueOf(updatedConsumedSlot));
		getModelService().save(deliverySlot);
		getModelService().refresh(deliverySlot);
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(200).append("Consumed count and Slot Capacity of delivery slot [")
					.append(deliverySlot.getPk()).append("] is [").append(deliverySlot.getConsumedCount()).append("] and [")
					.append(deliverySlot.getSlotCapacity()).toString());
		}

		return deliverySlot.getConsumedCount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setDeliverySlot(final WarehouseModel warehouse, final Date deliveryDate, final String slotTime)
	{
		validateParameterNotNullStandardMessage(WAREHOUSE, warehouse);
		validateParameterNotNullStandardMessage(DELIVERYDATE, deliveryDate);
		validateParameterNotNullStandardMessage(SLOTTIME, slotTime);

		boolean successfullySet = false;
		final AmwayDeliverySlotAvailabilityModel slot = getAmwayApacDeliverySlotDao().getDeliverySlot(warehouse, deliveryDate,
				slotTime);
		if (null != slot && getCartService().hasSessionCart())
		{
			final CartModel cart = getCartService().getSessionCart();
			cart.setSelectedDeliverySlot(slot);
			getModelService().save(cart);
			getModelService().refresh(cart);
			successfullySet = true;
		}
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(50).append("Slot returned was: [ ").append(slot).append(" ]").toString());
		}
		return successfullySet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayDeliverySlotAvailabilityModel getDeliverySlot(final WarehouseModel warehouse, final Date deliveryDate,
			final String slotTime)
	{
		validateParameterNotNullStandardMessage(WAREHOUSE, warehouse);
		validateParameterNotNullStandardMessage(DELIVERYDATE, deliveryDate);
		validateParameterNotNullStandardMessage(SLOTTIME, slotTime);

		return getAmwayApacDeliverySlotDao().getDeliverySlot(warehouse, deliveryDate, slotTime);
	}

	/**
	 * Gets the cart service.
	 *
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * Sets the cart service.
	 *
	 * @param cartService
	 *           the cartService to set
	 */
	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	/**
	 * Gets the amway apac delivery slot dao.
	 *
	 * @return the amwayApacDeliverySlotDao
	 */
	public AmwayApacDeliverySlotDao getAmwayApacDeliverySlotDao()
	{
		return amwayApacDeliverySlotDao;
	}

	/**
	 * Sets the amway apac delivery slot dao.
	 *
	 * @param amwayApacDeliverySlotDao
	 *           the amwayApacDeliverySlotDao to set
	 */
	@Required
	public void setAmwayApacDeliverySlotDao(final AmwayApacDeliverySlotDao amwayApacDeliverySlotDao)
	{
		this.amwayApacDeliverySlotDao = amwayApacDeliverySlotDao;
	}

	/**
	 * @return the amwayApacDeliverySlotManagementService
	 */
	public AmwayApacDeliverySlotManagementService getAmwayApacDeliverySlotManagementService()
	{
		return amwayApacDeliverySlotManagementService;
	}

	/**
	 * @param amwayApacDeliverySlotManagementService the amwayApacDeliverySlotManagementService to set
	 */
	@Required
	public void setAmwayApacDeliverySlotManagementService(final AmwayApacDeliverySlotManagementService amwayApacDeliverySlotManagementService)
	{
		this.amwayApacDeliverySlotManagementService = amwayApacDeliverySlotManagementService;
	}
}
