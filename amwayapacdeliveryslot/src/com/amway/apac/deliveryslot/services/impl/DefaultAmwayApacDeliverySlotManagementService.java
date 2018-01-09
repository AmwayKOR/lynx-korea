/**
 *
 */
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.deliveryslot.daos.AmwayApacDeliverySlotManagementDao;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;
import com.amway.apac.deliveryslot.services.AmwayApacDeliverySlotManagementService;


/**
 * Default implementation of {@link AmwayApacDeliverySlotManagementService}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacDeliverySlotManagementService implements AmwayApacDeliverySlotManagementService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacDeliverySlotManagementService.class);

	private ModelService modelService;
	private AmwayApacDeliverySlotManagementDao amwayApacDeliverySlotManagementDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.amway.apac.core.deliveryslot.services.AmwayApacDeliverySlotCreationJobService#getDeliverySlotByOrderDay(java.
	 * time.DayOfWeek)
	 */
	@Override
	public List<AmwayDeliverySlotConfigModel> getDeliverySlotByOrderDay(final DayOfWeek orderingDay)
	{
		return getAmwayApacDeliverySlotManagementDao().getDeliverySlotByOrderDay(orderingDay);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.amway.apac.core.deliveryslot.services.AmwayApacDeliverySlotCreationJobService#createDeliverySlotForDate(java.
	 * util.List, java.time.LocalDate)
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

				LOG.debug(new StringBuilder().append("Delivery slot creation info : ").append(deliverySlotAvailabilityModels.size())
						.append(" delivery slots created for ordering date ").append(orderingDate).toString());
			}
			else
			{
				LOG.debug(new StringBuilder().append("Delivery slot creation info : ")
						.append("NO delivery slot created for ordering date ").append(orderingDate).toString());
			}
		}
	}

	/**
	 * @param configModel
	 * @param deliveryDayAndDateMap
	 * @return
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
	 * @param orderingDate
	 * @param deliveryDays
	 * @return
	 */
	protected Map<WeekDay, LocalDate> getDeliveryDates(final LocalDate orderingDate, final Set<WeekDay> deliveryDays)
	{
		return deliveryDays.stream()
				.collect(Collectors.toMap(weekDay -> weekDay, weekDay -> getDeliveryDate(orderingDate, weekDay)));
	}

	/**
	 * This method will return list of delivery days applicable
	 *
	 * @param applicableDeliverySlots
	 * @return
	 */
	protected Set<WeekDay> getApplicableDeliveryDays(final List<AmwayDeliverySlotConfigModel> applicableDeliverySlots)
	{
		return applicableDeliverySlots.stream().map(slot -> slot.getDeliveryDay()).collect(Collectors.toSet());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.deliveryslot.services.AmwayApacDeliverySlotCreationJobService#getDeliveryDate(java.time.
	 * LocalDate, de.hybris.platform.basecommerce.enums.WeekDay)
	 */
	@Override
	public LocalDate getDeliveryDate(final LocalDate orderingDate, final WeekDay deliveryDay)
	{
		final DayOfWeek orderingDay = orderingDate.getDayOfWeek();
		final DayOfWeek deliveryWeekDay = DayOfWeek.valueOf(deliveryDay.getCode());

		int n = deliveryWeekDay.getValue() - orderingDay.getValue();
		if (n < 0)
		{
			n = 7 - Math.abs(n);
		}
		return orderingDate.plusDays(n);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.deliveryslot.services.AmwayApacDeliverySlotManagementService#
	 * getNextDeliverySlotByDeliveryDateAndSlot(java.time.LocalDate, java.lang.String)
	 */
	@Override
	public List<AmwayDeliverySlotAvailabilityModel> getNextDeliverySlotByDeliveryDateAndSlot(final LocalDate deliveryDate,
			final String slotTime)
	{
		return getAmwayApacDeliverySlotManagementDao().getNextDeliverySlotByDeliveryDateAndSlot(deliveryDate, slotTime);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.deliveryslot.services.AmwayApacDeliverySlotManagementService#
	 * updateInfoInSlotsAvailabilityModels(com.amway.apac.core.model.AmwayDeliverySlotConfigModel, java.util.List)
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
			LOG.info("Modification of Slots data for delivery Date : " + deliveryDate + " successfully completed.");
		}
		else
		{
			LOG.info("NO SLOT MODEL FOUND for delivery Date : " + deliveryDate);
		}
	}

	@Override
	public void createDeliverySlotData(final LocalDate orderingDate)
	{
		final DayOfWeek orderingDay = orderingDate.getDayOfWeek();
		LOG.debug("Week Of Day : " + orderingDay);

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
	 * @return the amwayApacDeliverySlotManagementDao
	 */
	public AmwayApacDeliverySlotManagementDao getAmwayApacDeliverySlotManagementDao()
	{
		return amwayApacDeliverySlotManagementDao;
	}

	/**
	 * @param amwayApacDeliverySlotManagementDao
	 *           the amwayApacDeliverySlotManagementDao to set
	 */
	@Required
	public void setAmwayApacDeliverySlotManagementDao(final AmwayApacDeliverySlotManagementDao amwayApacDeliverySlotManagementDao)
	{
		this.amwayApacDeliverySlotManagementDao = amwayApacDeliverySlotManagementDao;
	}
}
