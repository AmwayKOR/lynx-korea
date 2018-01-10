package com.amway.amwayordermanagementwebservices.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Interface for searching orders by custom criteria
 */
public interface AmwayOrderSearchService {

    /**
     * Finds orders by criteria
     *
     * @param criteria
     * @return
     */
    SearchPageData findOrders(OrderSearchCriteria criteria);
}
