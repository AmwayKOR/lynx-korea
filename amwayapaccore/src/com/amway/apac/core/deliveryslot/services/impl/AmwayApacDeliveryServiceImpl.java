/**
 *
 */
package com.amway.apac.core.deliveryslot.services.impl;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.commerceservices.delivery.impl.DefaultDeliveryService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.deliveryslot.daos.AmwayApacDeliverySlotDao;
import com.amway.apac.core.deliveryslot.services.AmwayApacDeliveryService;
import com.amway.apac.core.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.core.exceptions.AmwayServiceException;


/**
 * Default implementation of {@link AmwayApacDeliveryService}.
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacDeliveryServiceImpl extends DefaultDeliveryService implements AmwayApacDeliveryService
{
	private static final Logger LOG = Logger.getLogger(AmwayApacDeliveryServiceImpl.class);

	private AmwayApacDeliverySlotDao amwayApacDeliverySlotDao;
	private CartService cartService;
	private TransactionTemplate transactionTemplate;


	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.deliveryslot.services.AmwayApacDeliveryService#getDeliverySlotsAvailability()
	 */
	@Override
	public List<AmwayDeliverySlotAvailabilityModel> getDeliverySlotsAvailability()
	{
		List<AmwayDeliverySlotAvailabilityModel> slotAvailabilityModels = new ArrayList<AmwayDeliverySlotAvailabilityModel>();
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
	 * @param cart
	 */
	protected void clearSelectedDeliverySlotIfRequired(final CartModel cart)
	{
		final AmwayDeliverySlotAvailabilityModel slot = cart.getSelectedDeliverySlot();
		if (null != slot && (slot.getSlotCapacity().intValue() - slot.getConsumedCount().intValue() <= 0))
		{
			cart.setSelectedDeliverySlot(null);
			getModelService().save(cart);
			getModelService().refresh(cart);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.deliveryslot.services.AmwayApacDeliveryService#getDeliveryDate(de.hybris.platform.
	 * ordersplitting.model.WarehouseModel)
	 */
	@Override
	public Date getDeliveryDate(final WarehouseModel warehouse)
	{

		final Date now = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		final int dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);

		final SimpleDateFormat dateFormat = new SimpleDateFormat(AmwayapacCoreConstants.TIME_FORMAT);
		final String formattedDate = dateFormat.format(new Date()).toString();
		final Time orderingTime = Time.valueOf(formattedDate);

		final WeekDay[] deliveryDays = WeekDay.values();
		WeekDay deliveryDay = null;

		deliveryDay = getAmwayApacDeliverySlotDao().getDeliveryDay(warehouse, deliveryDays[dayofWeek - 1], orderingTime);

		if (null != deliveryDay)
		{
			return getDateByDay(deliveryDay, now);
		}
		return null;
	}

	/**
	 * @param deliveryDay
	 * @param todayDate
	 * @return
	 */
	protected Date getDateByDay(final WeekDay deliveryDay, final Date todayDate)
	{
		final int dayValue = deliveryDay.ordinal();
		LocalDateTime localDateTime = todayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		boolean calculated = false;
		while (!calculated)
		{
			localDateTime = localDateTime.plusDays(1);
			if (localDateTime.getDayOfWeek().getValue() == dayValue)
			{
				final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AmwayapacCoreConstants.DATE_FORMAT_PROFILE);
				final String formattedDate = localDateTime.format(formatter);
				final LocalDate localDate = LocalDate.parse(formattedDate);
				calculated = true;
				return java.sql.Date.valueOf(localDate);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.deliveryslot.services.AmwayApacDeliveryService#reserve(com.amway.apac.core.model.
	 * AmwayDeliverySlotAvailabilityModel)
	 */
	@SuppressWarnings("boxing")
	@Override
	public Integer reserve(final AmwayDeliverySlotAvailabilityModel deliverySlot) throws AmwayServiceException
	{
		ServicesUtil.validateParameterNotNullStandardMessage("deliverySlot", deliverySlot);
		return (Integer) getTransactionTemplate().execute(new TransactionCallback()
		{
			@Override
			public Integer doInTransaction(final TransactionStatus status)
			{
				try
				{
					getModelService().refresh(deliverySlot);
					if (deliverySlot.getSlotCapacity() - deliverySlot.getConsumedCount() > 0)
					{
						final Integer updatedConsumedSlot = deliverySlot.getConsumedCount() + 1;
						deliverySlot.setConsumedCount(updatedConsumedSlot);
						getModelService().save(deliverySlot);
						getModelService().refresh(deliverySlot);
						return deliverySlot.getConsumedCount();
					}
					else
					{
						throw new AmwayServiceException(new StringBuilder().append("Selected delivery slot [ ")
								.append(deliverySlot.getPk()).append(" ] for time: [ ").append(deliverySlot.getSlotTime())
								.append(" ] not available.").toString());
					}
				}
				catch (final Exception e)
				{
					throw new AmwayServiceException("Exception Occured while reserving delivery slot");
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.deliveryslot.services.AmwayApacDeliveryService#release(com.amway.apac.core.model.
	 * AmwayDeliverySlotAvailabilityModel)
	 */
	@Override
	public Integer release(final AmwayDeliverySlotAvailabilityModel deliverySlot) throws AmwayServiceException
	{
		ServicesUtil.validateParameterNotNullStandardMessage("deliverySlot", deliverySlot);
		return (Integer) getTransactionTemplate().execute(new TransactionCallback()
		{
			@Override
			public Integer doInTransaction(final TransactionStatus status)
			{
				try
				{
					getModelService().refresh(deliverySlot);
					final int updatedConsumedSlot = deliverySlot.getConsumedCount().intValue() - 1;
					deliverySlot.setConsumedCount(new Integer(updatedConsumedSlot));
					getModelService().save(deliverySlot);
					getModelService().refresh(deliverySlot);

					LOG.info(new StringBuilder().append("Consumed count and Slot Capacity of delivery slot [")
							.append(deliverySlot.getPk()).append("] is [").append(deliverySlot.getConsumedCount()).append("] and [")
							.append(deliverySlot.getSlotCapacity()).toString());

					return deliverySlot.getConsumedCount();
				}
				catch (final Exception e)
				{
					throw new AmwayServiceException("Exception Occured while releasing delivery slot");
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.deliveryslot.services.AmwayApacDeliveryService#setDeliverySlot(de.hybris.platform.
	 * ordersplitting.model.WarehouseModel, java.util.Date, java.lang.String)
	 */
	@Override
	public boolean setDeliverySlot(final WarehouseModel warehouse, final Date deliveryDate, final String slotTime)
	{
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
		LOG.info(new StringBuilder().append("Slot returned was: [ ").append(slot).append(" ]").toString());
		return successfullySet;
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	/**
	 * @return the amwayApacDeliverySlotDao
	 */
	public AmwayApacDeliverySlotDao getAmwayApacDeliverySlotDao()
	{
		return amwayApacDeliverySlotDao;
	}

	/**
	 * @param amwayApacDeliverySlotDao
	 *           the amwayApacDeliverySlotDao to set
	 */
	public void setAmwayApacDeliverySlotDao(final AmwayApacDeliverySlotDao amwayApacDeliverySlotDao)
	{
		this.amwayApacDeliverySlotDao = amwayApacDeliverySlotDao;
	}

	/**
	 * @return the transactionTemplate
	 */
	public TransactionTemplate getTransactionTemplate()
	{
		return transactionTemplate;
	}

	/**
	 * @param transactionTemplate
	 *           the transactionTemplate to set
	 */
	public void setTransactionTemplate(final TransactionTemplate transactionTemplate)
	{
		this.transactionTemplate = transactionTemplate;
	}
}
