package com.amway.core.order.dao.impl;


import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.daos.impl.DefaultOrderDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.Config;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.amway.core.enums.AmwayCartType;
import com.amway.core.order.dao.AmwayOrderDao;


/**
 * Default Implementation to retrieve orders with payment status not PAID
 */
public class DefaultAmwayOrderDao extends DefaultOrderDao implements AmwayOrderDao
{

}
