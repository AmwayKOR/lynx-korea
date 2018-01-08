/**
 *
 */
package com.amway.apac.core.deliveryslot.daos.impl;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.amway.apac.core.deliveryslot.daos.AmwayApacDeliverySlotDao;
import com.amway.apac.core.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.core.model.AmwayDeliverySlotConfigModel;


/**
 * Default implementation of {@link AmwayApacDeliverySlotDao}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacDeliverySlotDao implements AmwayApacDeliverySlotDao
{
	private static final String WILD_CHARACTER = "%";
	private static final StringBuilder DELIVERY_SLOTS_AVAILABILITY_QUERY = new StringBuilder(140).append("SELECT {")
			.append(AmwayDeliverySlotAvailabilityModel.PK).append("} FROM {").append(AmwayDeliverySlotAvailabilityModel._TYPECODE)
			.append("} WHERE {").append(AmwayDeliverySlotAvailabilityModel.WAREHOUSE).append("} = ?")
			.append(AmwayDeliverySlotAvailabilityModel.WAREHOUSE).append(" AND {").append(AmwayDeliverySlotAvailabilityModel.ACTIVE)
			.append("} = ").append(Boolean.TRUE).append(" AND {").append(AmwayDeliverySlotAvailabilityModel.DELIVERYDATE)
			.append("} LIKE ?").append(AmwayDeliverySlotAvailabilityModel.DELIVERYDATE).append(" ORDER BY {")
			.append(AmwayDeliverySlotAvailabilityModel.SLOTNUMBER).append("}");

	private static final StringBuilder FIND_DELIVERY_SLOT_QUERY = new StringBuilder(130).append("SELECT {")
			.append(AmwayDeliverySlotAvailabilityModel.PK).append("} FROM {").append(AmwayDeliverySlotAvailabilityModel._TYPECODE)
			.append("} WHERE {").append(AmwayDeliverySlotAvailabilityModel.WAREHOUSE).append("} = ?")
			.append(AmwayDeliverySlotAvailabilityModel.WAREHOUSE).append(" AND {").append(AmwayDeliverySlotAvailabilityModel.ACTIVE)
			.append("} = ").append(Boolean.TRUE).append(" AND {").append(AmwayDeliverySlotAvailabilityModel.DELIVERYDATE)
			.append("} LIKE ?").append(AmwayDeliverySlotAvailabilityModel.DELIVERYDATE).append(" AND {")
			.append(AmwayDeliverySlotAvailabilityModel.SLOTTIME).append("} = ?").append(AmwayDeliverySlotAvailabilityModel.SLOTTIME);

	private static final StringBuilder FIND_DELIVERY_DAY_QUERY = new StringBuilder(140).append("SELECT {")
			.append(AmwayDeliverySlotConfigModel.PK).append("} FROM {").append(AmwayDeliverySlotConfigModel._TYPECODE)
			.append("} WHERE {").append(AmwayDeliverySlotConfigModel.WAREHOUSE).append("} = ?")
			.append(AmwayDeliverySlotConfigModel.WAREHOUSE).append(" AND {").append(AmwayDeliverySlotConfigModel.ACTIVE)
			.append("} = ").append(Boolean.TRUE).append(" AND {").append(AmwayDeliverySlotConfigModel.ORDERINGDAY).append("} = ?")
			.append(AmwayDeliverySlotConfigModel.ORDERINGDAY).append(" AND {").append(AmwayDeliverySlotConfigModel.ORDERINGTIME)
			.append("} <= ?").append(AmwayDeliverySlotConfigModel.ORDERINGTIME).append(" ORDER BY {")
			.append(AmwayDeliverySlotConfigModel.ORDERINGTIME).append("}");

	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.amway.apac.core.deliveryslot.daos.AmwayApacDeliverySlotDao#getDeliverySlotsAvailability(de.hybris.platform.
	 * ordersplitting.model.WarehouseModel, java.util.Date)
	 */
	@Override
	public List<AmwayDeliverySlotAvailabilityModel> getDeliverySlotsAvailability(final WarehouseModel warehouse,
			final Date deliveryDate)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("warehouse", warehouse);
		ServicesUtil.validateParameterNotNullStandardMessage("deliveryDate", deliveryDate);

		final Date today = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		final Map<String, Object> params = new HashMap<>();
		params.put(AmwayDeliverySlotAvailabilityModel.WAREHOUSE, warehouse);
		params.put(AmwayDeliverySlotAvailabilityModel.DELIVERYDATE, deliveryDate + WILD_CHARACTER);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(DELIVERY_SLOTS_AVAILABILITY_QUERY);
		query.addQueryParameters(params);

		final SearchResult<AmwayDeliverySlotAvailabilityModel> result = getFlexibleSearchService().search(query);

		return result.getResult();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.amway.apac.core.deliveryslot.daos.AmwayApacDeliverySlotDao#getDeliverySlot(de.hybris.platform.ordersplitting.
	 * model.WarehouseModel, java.util.Date, java.lang.String)
	 */
	@Override
	public AmwayDeliverySlotAvailabilityModel getDeliverySlot(final WarehouseModel warehouse, final Date deliveryDate,
			final String slotTime)
	{
		AmwayDeliverySlotAvailabilityModel slot = null;
		ServicesUtil.validateParameterNotNullStandardMessage("warehouse", warehouse);
		ServicesUtil.validateParameterNotNullStandardMessage("deliveryDate", deliveryDate);
		ServicesUtil.validateParameterNotNullStandardMessage("slotTime", slotTime);

		final Map<String, Object> params = new HashMap<>();
		params.put(AmwayDeliverySlotAvailabilityModel.WAREHOUSE, warehouse);
		params.put(AmwayDeliverySlotAvailabilityModel.DELIVERYDATE, deliveryDate + WILD_CHARACTER);
		params.put(AmwayDeliverySlotAvailabilityModel.SLOTTIME, slotTime);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_DELIVERY_SLOT_QUERY);
		query.addQueryParameters(params);

		final SearchResult<AmwayDeliverySlotAvailabilityModel> result = getFlexibleSearchService().search(query);

		if (CollectionUtils.isNotEmpty(result.getResult()))
		{
			slot = result.getResult().get(0);
		}
		return slot;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.amway.apac.core.deliveryslot.daos.AmwayApacDeliverySlotDao#getDeliveryDay(de.hybris.platform.ordersplitting.
	 * model.WarehouseModel, de.hybris.platform.basecommerce.enums.WeekDay, java.util.Date)
	 */
	@Override
	public WeekDay getDeliveryDay(final WarehouseModel warehouse, final WeekDay orderingDay, final Date orderingTime)
	{
		WeekDay deliveryDay = null;
		ServicesUtil.validateParameterNotNullStandardMessage("warehouse", warehouse);
		ServicesUtil.validateParameterNotNullStandardMessage("orderingDay", orderingDay);
		ServicesUtil.validateParameterNotNullStandardMessage("orderingTime", orderingTime);

		final Date today = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		final Map<String, Object> params = new HashMap<>();
		params.put(AmwayDeliverySlotConfigModel.WAREHOUSE, warehouse);
		params.put(AmwayDeliverySlotConfigModel.ORDERINGDAY, orderingDay);
		params.put(AmwayDeliverySlotConfigModel.ORDERINGTIME, orderingTime);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_DELIVERY_DAY_QUERY);
		query.addQueryParameters(params);

		final SearchResult<AmwayDeliverySlotConfigModel> result = getFlexibleSearchService().search(query);

		if (CollectionUtils.isNotEmpty(result.getResult()))
		{
			deliveryDay = result.getResult().get(0).getDeliveryDay();
		}

		return deliveryDay;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
