package com.amway.apac.message.center.notification.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;


/**
 * Service layer for the message center notifications.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacNotificationService
{
	/**
	 * Returns AmwayNotificaionModels in the form of searchPageData for given customer, statuses and search text.
	 *
	 * @param pageableData
	 * @param userModel
	 *           Customer for whom notifications needed
	 * @param statuses
	 *           List of statuses to be filtered.
	 * @param searchText
	 *           searchText to search notifications
	 * @return {@link AmwayNotificationModel}
	 * @throws IllegalArgumentException
	 *            if pageableData or customer is null
	 */
	SearchPageData<AmwayNotificationModel> getNotifications(final PageableData pageableData, final CustomerModel userModel,
			final List<AmwayNotificationUserActionStatus> statuses, final String searchText);

	/**
	 * Returns {@link AmwayNotificationModel} for a given unique code.
	 *
	 * @param notificationCode
	 *           Unique code for a notification
	 * @return AmwayNotification
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
	 */
	void changeUserNotificationStatus(final AmwayNotificationModel notification, final CustomerModel customer,
			final AmwayNotificationUserActionStatus newStatus);

}
