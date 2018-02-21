package com.amway.apac.deliveryslot.daos.impl;

import static com.amway.apac.deliveryslot.constants.AmwayapacdeliveryslotConstants.ZERO_INT;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel.ACTIVE;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel.DELIVERYDATE;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel.SLOTTIME;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel.WAREHOUSE;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel.ORDERINGDAY;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel.ORDERINGTIME;
import static de.hybris.platform.core.model.ItemModel.PK;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.deliveryslot.daos.AmwayApacDeliverySlotDao;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;


/**
 * Default implementation of {@link AmwayApacDeliverySlotDao}.
 *
 * @author Ashish Sabal
 */
public class DefaultAmwayApacDeliverySlotDao implements AmwayApacDeliverySlotDao
{

	/** The Constant WILD CHARACTER. */
	private static final String WILD_CHARACTER = "%";

	/** Query to find delivery slot availability models with warehouse and delivery date. */
	private static final StringBuilder DELIVERY_SLOTS_AVAILABILITY_QUERY = new StringBuilder(140).append("SELECT {").append(PK)
			.append("} FROM {").append(AmwayDeliverySlotAvailabilityModel._TYPECODE).append("} WHERE {").append(WAREHOUSE)
			.append("} = ?").append(WAREHOUSE).append(" AND {").append(ACTIVE).append("} = ").append(Boolean.TRUE).append(" AND {")
			.append(DELIVERYDATE).append("} LIKE ?").append(DELIVERYDATE);

	/** Query to find delivery slot availability models with warehouse, delivery date and slot time. */
	private static final StringBuilder FIND_DELIVERY_SLOT_QUERY = new StringBuilder(130).append("SELECT {").append(PK)
			.append("} FROM {").append(AmwayDeliverySlotAvailabilityModel._TYPECODE).append("} WHERE {").append(WAREHOUSE)
			.append("} = ?").append(WAREHOUSE).append(" AND {").append(ACTIVE).append("} = ").append(Boolean.TRUE).append(" AND {")
			.append(DELIVERYDATE).append("} LIKE ?").append(DELIVERYDATE).append(" AND {").append(SLOTTIME).append("} = ?")
			.append(SLOTTIME);

	/** Query to find delivery day with warehouse, ordering date and ordering time. */
	private static final StringBuilder FIND_DELIVERY_DAY_QUERY = new StringBuilder(140).append("SELECT {").append(PK)
			.append("} FROM {").append(AmwayDeliverySlotConfigModel._TYPECODE).append("} WHERE {")
			.append(AmwayDeliverySlotConfigModel.WAREHOUSE).append("} = ?").append(AmwayDeliverySlotConfigModel.WAREHOUSE)
			.append(" AND {").append(AmwayDeliverySlotConfigModel.ACTIVE).append("} = ").append(Boolean.TRUE).append(" AND {")
			.append(ORDERINGDAY).append("} = ?").append(ORDERINGDAY).append(" AND {").append(ORDERINGTIME).append("} <= ?")
			.append(ORDERINGTIME).append(" ORDER BY {").append(ORDERINGTIME).append("}");

	/** The flexible search service. */
	private FlexibleSearchService flexibleSearchService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayDeliverySlotAvailabilityModel> getDeliverySlotsAvailability(final WarehouseModel warehouse,
			final Date deliveryDate)
	{
		validateParameterNotNullStandardMessage(WAREHOUSE, warehouse);
		validateParameterNotNullStandardMessage(DELIVERYDATE, deliveryDate);

		final Date today = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		final Map<String, Object> params = new HashMap<>();
		params.put(WAREHOUSE, warehouse);
		params.put(DELIVERYDATE, deliveryDate + WILD_CHARACTER);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(DELIVERY_SLOTS_AVAILABILITY_QUERY);
		query.addQueryParameters(params);

		final SearchResult<AmwayDeliverySlotAvailabilityModel> result = getFlexibleSearchService().search(query);

		return result.getResult();
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

		final Map<String, Object> params = new HashMap<>();
		params.put(WAREHOUSE, warehouse);
		params.put(DELIVERYDATE, deliveryDate + WILD_CHARACTER);
		params.put(SLOTTIME, slotTime);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_DELIVERY_SLOT_QUERY);
		query.addQueryParameters(params);

		final SearchResult<AmwayDeliverySlotAvailabilityModel> result = getFlexibleSearchService().search(query);

		if (CollectionUtils.isNotEmpty(result.getResult()))
		{
			return result.getResult().get(ZERO_INT.intValue());
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WeekDay getDeliveryDay(final WarehouseModel warehouse, final WeekDay orderingDay, final Date orderingTime)
	{
		validateParameterNotNullStandardMessage(WAREHOUSE, warehouse);
		validateParameterNotNullStandardMessage(ORDERINGDAY, orderingDay);
		validateParameterNotNullStandardMessage(ORDERINGTIME, orderingTime);

		final Date today = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		final Map<String, Object> params = new HashMap<>();
		params.put(WAREHOUSE, warehouse);
		params.put(ORDERINGDAY, orderingDay);
		params.put(ORDERINGTIME, orderingTime);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_DELIVERY_DAY_QUERY);
		query.addQueryParameters(params);

		final SearchResult<AmwayDeliverySlotConfigModel> result = getFlexibleSearchService().search(query);

		if (CollectionUtils.isNotEmpty(result.getResult()))
		{
			return result.getResult().get(ZERO_INT.intValue()).getDeliveryDay();
		}

		return null;
	}

	/**
	 * Gets the flexible search service.
	 *
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * Sets the flexible search service.
	 *
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
