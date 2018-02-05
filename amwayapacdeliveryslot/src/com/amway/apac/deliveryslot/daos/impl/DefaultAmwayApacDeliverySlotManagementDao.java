package com.amway.apac.deliveryslot.daos.impl;

import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel.DELIVERYDATE;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel.SLOTTIME;
import static com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel.ORDERINGDAY;
import static de.hybris.platform.core.model.ItemModel.PK;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

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

import com.amway.apac.deliveryslot.daos.AmwayApacDeliverySlotManagementDao;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;


/**
 * Default implementation of {@link AmwayApacDeliverySlotManagementDao}.
 *
 * @author Ashish Sabal
 */
public class DefaultAmwayApacDeliverySlotManagementDao implements AmwayApacDeliverySlotManagementDao
{
	/** SQL to get delivery slot by ordering day. */
	private static final String DELIVERY_SLOT_BY_ORDER_DAY = new StringBuilder().append("Select {").append(PK).append("} from {")
			.append(AmwayDeliverySlotConfigModel._TYPECODE).append(" as slot JOIN ").append(WeekDay._TYPECODE)
			.append(" as wd on {slot.").append(ORDERINGDAY).append("}={wd.pk}} where {wd.code}=?").append(ORDERINGDAY).toString();

	/** SQL to get the next delivery slot by delivery date and slot. */
	private static final String NEXT_DELIVERY_SLOT_BY_DATE_AND_SLOT = new StringBuilder().append("Select {").append(PK)
			.append("} from {").append(AmwayDeliverySlotAvailabilityModel._TYPECODE).append(" as slot} where {slot.")
			.append(DELIVERYDATE).append("}=?").append(DELIVERYDATE).append(" AND {slot.").append(SLOTTIME).append("}=?")
			.append(SLOTTIME).toString();

	/** The flexible search service. */
	private FlexibleSearchService flexibleSearchService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayDeliverySlotConfigModel> getDeliverySlotByOrderDay(final DayOfWeek orderingDay)
	{
		validateParameterNotNullStandardMessage(ORDERINGDAY, orderingDay);

		final Map<String, String> queryParams = new HashMap<>();
		queryParams.put(ORDERINGDAY, orderingDay.name());

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(DELIVERY_SLOT_BY_ORDER_DAY);
		searchQuery.addQueryParameters(queryParams);

		final SearchResult<AmwayDeliverySlotConfigModel> deliverySlotsResults = getFlexibleSearchService().search(searchQuery);

		return deliverySlotsResults.getResult();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayDeliverySlotAvailabilityModel> getNextDeliverySlotByDeliveryDateAndSlot(final LocalDate deliveryDate,
			final String slot)
	{
		validateParameterNotNullStandardMessage(DELIVERYDATE, deliveryDate);
		validateParameterNotNullStandardMessage(SLOTTIME, slot);

		final Map<String, String> queryParams = new HashMap<>();
		queryParams.put(DELIVERYDATE, deliveryDate.toString());
		queryParams.put(SLOTTIME, slot);

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(NEXT_DELIVERY_SLOT_BY_DATE_AND_SLOT);
		searchQuery.addQueryParameters(queryParams);

		final SearchResult<AmwayDeliverySlotAvailabilityModel> deliverySlotsResults = getFlexibleSearchService()
				.search(searchQuery);

		return deliverySlotsResults.getResult();
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

	/**
	 * Gets the flexible search service.
	 *
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}
}
