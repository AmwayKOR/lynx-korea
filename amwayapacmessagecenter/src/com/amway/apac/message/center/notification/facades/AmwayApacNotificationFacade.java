package com.amway.apac.message.center.notification.facades;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;


/**
 * Interface for Notification facade
 *
 * @author Aaron Yong
 *
 */
public interface AmwayApacNotificationFacade
{
	/**
	 * Gets the amway notification section for current user with page data.
	 *
	 * @param pageableData
	 *           the pageable data
	 * @return the amway notification section for current user with page data
	 */
	SearchPageData<AmwayApacNotificationData> getNotificationsForCurrentUser(final PageableData pageableData);

	/**
	 * Change user notification status.
	 *
	 * @param notificationCode
	 *           the notification code
	 * @param newStatus
	 *           the new status
	 */
	void changeUserNotificationStatus(final String notificationCode, final AmwayNotificationUserActionStatus newStatus);
}
