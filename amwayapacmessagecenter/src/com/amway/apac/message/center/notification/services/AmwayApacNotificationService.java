package com.amway.apac.message.center.notification.services;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.servicelayer.data.SearchPageData;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.apac.message.center.notification.NotificationSearchParamData;


/**
 * Service layer for the message center notifications.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacNotificationService
{

	/**
	 * Fetches the notifications in the form of searchPageData for given search params : customer, statuses and search
	 * text.
	 *
	 * @param notificationSearchParam
	 *           the notification search params
	 * @return AmwayNotificaionModels in the form of searchPageData
	 */
	SearchPageData<AmwayNotificationModel> getNotifications(NotificationSearchParamData notificationSearchParam);

	/**
	 * Fetches a notification for a given unique code.
	 *
	 * @param notificationCode
	 *           Unique code for a notification
	 * @return AmwayNotification notification found
	 * @throws IllegalArgumentException
	 *            if notificationCode is null or empty.
	 */
	AmwayNotificationModel getNotificationByCode(final String notificationCode);

	/**
	 * Changes the status flag in {@link AmwayNotificationUserActionModel} for a given notification and customer to
	 * newStatus
	 *
	 * @param notification
	 *           Notification for which UserAction to be modified
	 * @param customer
	 *           Customer for which UserAction to be modified
	 * @param newStatus
	 *           New status
	 * @throws IllegalArgumentException
	 *            if notification, customer or newStatus is null.
	 */
	void changeUserNotificationStatus(final AmwayNotificationModel notification, final CustomerModel customer,
			final AmwayNotificationUserActionStatus newStatus);

	/**
	 * Gets the notification action by user and notification.
	 *
	 * @param customer
	 *           the customer
	 * @param notification
	 *           the notification
	 * @return notification action list
	 */
	AmwayNotificationUserActionModel getNotificationActionByUserAndNotification(CustomerModel customer,
			AmwayNotificationModel notification);

	/**
	 * Gets the notification action by notification for current user.
	 *
	 * @param notification
	 *           the notification
	 * @return notification action list
	 */
	AmwayNotificationUserActionModel getNotificationActionForCurrentUser(AmwayNotificationModel notification);
}
