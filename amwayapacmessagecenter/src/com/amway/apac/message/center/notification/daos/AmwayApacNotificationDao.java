package com.amway.apac.message.center.notification.daos;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;


/**
 * DAO layer for the message center notifications.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacNotificationDao
{
	/**
	 * Returns AmwayNotificaionModels in the form of searchPageData for given baseSite, customer, statuses and search
	 * text.
	 *
	 * @param pageableData
	 * @param baseSiteModel
	 *           Base Site
	 * @param customer
	 *           Customer for whom notifications needed
	 * @param statuses
	 *           List of statuses to be filtered.
	 * @param searchText
	 *           searchText to search notifications
	 * @return {@link AmwayNotificationModel}
	 * @throws IllegalArgumentException
	 *            if customer is null
	 */
	SearchPageData<AmwayNotificationModel> getNotifications(final PageableData pageableData, final BaseSiteModel baseSiteModel,
			final CustomerModel customer, final List<AmwayNotificationUserActionStatus> statuses, final String searchText,
			final String accountClassficationCode);

}
