package com.amway.apac.deliveryslot.services.impl;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.servicelayer.model.ModelService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.deliveryslot.constants.AmwayapacdeliveryslotConstants;
import com.amway.apac.deliveryslot.daos.AmwayApacDeliverySlotManagementDao;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;
import com.amway.apac.deliveryslot.services.AmwayApacDeliverySlotManagementService;


/**
 * Default implementation of {@link AmwayApacDeliverySlotManagementService}.
 *
 * @author Ashish Sabal
 */
public class DefaultAmwayApacDeliverySlotManagementService implements AmwayApacDeliverySlotManagementService
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacDeliverySlotManagementService.class);

	/** The Constant DAYS_IN_WEEK. */
	private static final int DAYS_IN_WEEK = 7;

	/** The model service. */
	private ModelService modelService;

	/** The amway apac delivery slot management dao. */
	private AmwayApacDeliverySlotManagementDao amwayApacDeliverySlotManagementDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayDeliverySlotConfigModel> getDeliverySlotByOrderDay(final DayOfWeek orderingDay)
	{
		return getAmwayApacDeliverySlotManagementDao().getDeliverySlotByOrderDay(orderingDay);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createDeliverySlotForDate(final List<AmwayDeliverySlotConfigModel> applicableDeliverySlots,
			final LocalDate orderingDate)
	{
		final List<AmwayDeliverySlotAvailabilityModel> deliverySlotAvailabilityModels = new ArrayList<AmwayDeliverySlotAvailabilityModel>();

		if (CollectionUtils.isNotEmpty(applicableDeliverySlots))
		{
			// 1. Get delivery days, for example : for Fri, it will return Mon and Tues
			final Set<WeekDay> deliveryDays = getApplicableDeliveryDays(applicableDeliverySlots);

			// 2. Get delivery dates, for delivery days
			final Map<WeekDay, LocalDate> deliveryDayAndDateMap = getDeliveryDates(orderingDate, deliveryDays);

			// 3. Create availability model
			applicableDeliverySlots.stream().forEach(configModel -> deliverySlotAvailabilityModels
					.add(createDeliverySlotAvailabilityModel(configModel, deliveryDayAndDateMap)));

			// 4. Save All Results
			if (CollectionUtils.isNotEmpty(deliverySlotAvailabilityModels))
			{
				getModelService().saveAll(deliverySlotAvailabilityModels);

				LOGGER.debug(
						new StringBuilder(200).append("Delivery slot creation info : ").append(deliverySlotAvailabilityModels.size())
								.append(" delivery slots created for ordering date ").append(orderingDate).toString());
			}
			else
			{
				LOGGER.debug(new StringBuilder(200).append("Delivery slot creation info : ")
						.append("NO delivery slot created for ordering date ").append(orderingDate).toString());
			}
		}
	}

	/**
	 * Creates the delivery slot availability model.
	 *
	 * @param configModel
	 *           the config model
	 * @param deliveryDayAndDateMap
	 *           the delivery day and date map
	 * @return the amway delivery slot availability model
	 */
	protected AmwayDeliverySlotAvailabilityModel createDeliverySlotAvailabilityModel(
			final AmwayDeliverySlotConfigModel configModel, final Map<WeekDay, LocalDate> deliveryDayAndDateMap)
	{
		final AmwayDeliverySlotAvailabilityModel availabilityModel = getModelService()
				.create(AmwayDeliverySlotAvailabilityModel.class);
		availabilityModel.setActive(configModel.getActive());
		availabilityModel.setSlotCapacity(configModel.getSlotCapacity());
		availabilityModel.setSlotTime(configModel.getSlotTime());
		availabilityModel.setDeliveryDate(java.sql.Date.valueOf(deliveryDayAndDateMap.get(configModel.getDeliveryDay())));
		availabilityModel.setConsumedCount(new Integer(0));
		availabilityModel.setWarehouse(configModel.getWarehouse());
		availabilityModel.setOrderingTime(configModel.getOrderingTime());
		availabilityModel.setDeliveryDay(configModel.getDeliveryDay());
		availabilityModel.setNotifyCount(configModel.getNotifyCount());
		availabilityModel.setInstructions(configModel.getInstructions());
		availabilityModel.setSlotConfig(configModel);

		return availabilityModel;
	}

	/**
	 * Gets the delivery dates.
	 *
	 * @param orderingDate
	 *           the ordering date
	 * @param deliveryDays
	 *           the delivery days
	 * @return the delivery dates
	 */
	protected Map<WeekDay, LocalDate> getDeliveryDates(final LocalDate orderingDate, final Set<WeekDay> deliveryDays)
	{
		return deliveryDays.stream()
				.collect(Collectors.toMap(weekDay -> weekDay, weekDay -> getDeliveryDate(orderingDate, weekDay)));
	}

	/**
	 * This method will return list of delivery days applicable.
	 *
	 * @param applicableDeliverySlots
	 *           the applicable delivery slots
	 * @return the applicable delivery days
	 */
	protected Set<WeekDay> getApplicableDeliveryDays(final List<AmwayDeliverySlotConfigModel> applicableDeliverySlots)
	{
		return applicableDeliverySlots.stream().map(slot -> slot.getDeliveryDay()).collect(Collectors.toSet());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDate getDeliveryDate(final LocalDate orderingDate, final WeekDay deliveryDay)
	{
		final DayOfWeek orderingDay = orderingDate.getDayOfWeek();
		final DayOfWeek deliveryWeekDay = DayOfWeek.valueOf(deliveryDay.getCode());

		int dayCountBetweenOrderingAndDelivery = deliveryWeekDay.getValue() - orderingDay.getValue();
		if (dayCountBetweenOrderingAndDelivery < AmwayapacdeliveryslotConstants.ZERO_INT.intValue())
		{
			dayCountBetweenOrderingAndDelivery = DAYS_IN_WEEK - Math.abs(dayCountBetweenOrderingAndDelivery);
		}
		return orderingDate.plusDays(dayCountBetweenOrderingAndDelivery);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayDeliverySlotAvailabilityModel> getNextDeliverySlotByDeliveryDateAndSlot(final LocalDate deliveryDate,
			final String slotTime)
	{
		return getAmwayApacDeliverySlotManagementDao().getNextDeliverySlotByDeliveryDateAndSlot(deliveryDate, slotTime);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateInfoInSlotsAvailabilityModels(final AmwayDeliverySlotConfigModel slotConfigModel,
			final List<AmwayDeliverySlotAvailabilityModel> slotModels)
	{
		Date deliveryDate = null;
		if (CollectionUtils.isNotEmpty(slotModels))
		{
			for (final AmwayDeliverySlotAvailabilityModel slotModel : slotModels)
			{
				slotModel.setSlotCapacity(slotConfigModel.getSlotCapacity());
				slotModel.setActive(slotConfigModel.getActive());
				slotModel.setInstructions(slotConfigModel.getInstructions(Locale.ENGLISH), Locale.ENGLISH);
				slotModel.setInstructions(slotConfigModel.getInstructions(Locale.CHINESE), Locale.CHINESE);
				slotModel.setNotifyCount(slotConfigModel.getNotifyCount());

				getModelService().save(slotModel);

				if (null == deliveryDate)
				{
					deliveryDate = slotModel.getDeliveryDate();
				}
			}
			LOGGER.info(new StringBuilder(100).append("Modification of Slots data for delivery Date : ").append(deliveryDate)
					.append(" successfully completed.").toString());
		}
		else
		{
			LOGGER.info(new StringBuilder(100).append("NO SLOT MODEL FOUND for delivery Date : ").append(deliveryDate).toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createDeliverySlotData(final LocalDate orderingDate)
	{
		final DayOfWeek orderingDay = orderingDate.getDayOfWeek();
		LOGGER.debug("Week Of Day : " + orderingDay);

		// If ordering day is SAT or SUN, data already exists in SYSTEM
		if ((!orderingDay.equals(DayOfWeek.SATURDAY)) && (!orderingDay.equals(DayOfWeek.SUNDAY)))
		{
			// 1. Get delivery date from ordering date & day.
			final List<AmwayDeliverySlotConfigModel> deliverySlotModels = getDeliverySlotByOrderDay(orderingDay);

			// 2. Get newDeliverySlots for ordering date
			createDeliverySlotForDate(deliverySlotModels, orderingDate);
		}
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
	 * Gets the amway apac delivery slot management dao.
	 *
	 * @return the amwayApacDeliverySlotManagementDao
	 */
	public AmwayApacDeliverySlotManagementDao getAmwayApacDeliverySlotManagementDao()
	{
		return amwayApacDeliverySlotManagementDao;
	}

	/**
	 * Sets the amway apac delivery slot management dao.
	 *
	 * @param amwayApacDeliverySlotManagementDao
	 *           the amwayApacDeliverySlotManagementDao to set
	 */
	@Required
	public void setAmwayApacDeliverySlotManagementDao(final AmwayApacDeliverySlotManagementDao amwayApacDeliverySlotManagementDao)
	{
		this.amwayApacDeliverySlotManagementDao = amwayApacDeliverySlotManagementDao;
	}
}
