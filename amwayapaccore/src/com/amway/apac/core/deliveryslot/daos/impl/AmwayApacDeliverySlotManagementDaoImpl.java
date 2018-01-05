/**
 *
 */
package com.amway.apac.core.deliveryslot.daos.impl;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.deliveryslot.daos.AmwayApacDeliverySlotManagementDao;
import com.amway.apac.core.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.core.model.AmwayDeliverySlotConfigModel;


/**
 * Default implementation of {@link AmwayApacDeliverySlotManagementDao}
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacDeliverySlotManagementDaoImpl implements AmwayApacDeliverySlotManagementDao
{
	private static final String DELIVERY_SLOT_BY_ORDER_DAY = new StringBuilder().append("Select {")
			.append(AmwayDeliverySlotConfigModel.PK).append("} from {").append(AmwayDeliverySlotConfigModel._TYPECODE)
			.append(" as slot JOIN ").append(WeekDay._TYPECODE).append(" as wd on {slot.")
			.append(AmwayDeliverySlotConfigModel.ORDERINGDAY).append("}={wd.pk}} where {wd.code}=?")
			.append(AmwayDeliverySlotConfigModel.ORDERINGDAY).toString();

	private static final String NEXT_DELIVERY_SLOT_BY_DATE_AND_SLOT = new StringBuilder().append("Select {")
			.append(AmwayDeliverySlotAvailabilityModel.PK).append("} from {").append(AmwayDeliverySlotAvailabilityModel._TYPECODE)
			.append(" as slot} where {slot.").append(AmwayDeliverySlotAvailabilityModel.DELIVERYDATE).append("}=?")
			.append(AmwayDeliverySlotAvailabilityModel.DELIVERYDATE).append(" AND {slot.")
			.append(AmwayDeliverySlotAvailabilityModel.SLOTTIME).append("}=?").append(AmwayDeliverySlotAvailabilityModel.SLOTTIME)
			.toString();

	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.deliveryslot.daos.AmwayApacDeliverySlotCreationDao#getDeliverySlotByOrderDay(java.time.
	 * DayOfWeek)
	 */
	@Override
	public List<AmwayDeliverySlotConfigModel> getDeliverySlotByOrderDay(final DayOfWeek orderingDay)
	{
		final Map<String, String> queryParams = new HashMap<>();
		queryParams.put(AmwayDeliverySlotConfigModel.ORDERINGDAY, orderingDay.name());

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(DELIVERY_SLOT_BY_ORDER_DAY);
		searchQuery.addQueryParameters(queryParams);

		final SearchResult<AmwayDeliverySlotConfigModel> deliverySlotsResults = getFlexibleSearchService().search(searchQuery);

		return deliverySlotsResults.getResult();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.amway.apac.core.deliveryslot.daos.AmwayApacDeliverySlotCreationDao#getNextDeliverySlotByDeliveryDateAndSlot(
	 * java.time.LocalDate, java.lang.String)
	 */
	@Override
	public List<AmwayDeliverySlotAvailabilityModel> getNextDeliverySlotByDeliveryDateAndSlot(final LocalDate deliveryDate,
			final String slot)
	{
		final Map<String, String> queryParams = new HashMap<>();
		queryParams.put(AmwayDeliverySlotAvailabilityModel.DELIVERYDATE, deliveryDate.toString());
		queryParams.put(AmwayDeliverySlotAvailabilityModel.SLOTTIME, slot);

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(NEXT_DELIVERY_SLOT_BY_DATE_AND_SLOT);
		searchQuery.addQueryParameters(queryParams);

		final SearchResult<AmwayDeliverySlotAvailabilityModel> deliverySlotsResults = getFlexibleSearchService()
				.search(searchQuery);

		return deliverySlotsResults.getResult();
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}
}
