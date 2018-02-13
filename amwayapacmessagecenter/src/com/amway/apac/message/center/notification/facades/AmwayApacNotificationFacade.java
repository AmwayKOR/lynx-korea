package com.amway.apac.message.center.notification.facades;


import de.hybris.platform.core.servicelayer.data.SearchPageData;

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
	 * Change user notification status.
	 *
	 * @param notificationCode
	 *           the notification code
	 * @param newStatus
	 *           the new status
	 */
	void changeUserNotificationStatus(final String notificationCode, final AmwayNotificationUserActionStatus newStatus);

	/**
	 * Gets the notifications for current user.
	 *
	 * @param userParams
	 *           the user params
	 * @param messageType
	 *           the message type
	 * @return the notifications for current user
	 */
	SearchPageData<AmwayApacNotificationData> getNotificationsForCurrentUser(SearchPageData userParams, String messageType);
}
