package com.amway.apac.message.center.notification.facades;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.components.CMSParagraphComponentModel;
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
	 * @param compId
	 * @return message content
	 * @throws CMSItemNotFoundException
	 */
	CMSParagraphComponentModel getDetailedMessage(final String compId) throws CMSItemNotFoundException;

	/**
	 * Gets the amway notification section for current user with page data.
	 *
	 * @param pageableData
	 *           the pageable data
	 * @param statuses
	 *           the statuses
	 * @param messageType
	 *           the message type
	 * @return the amway notification section for current user with page data
	 */
	SearchPageData<AmwayApacNotificationData> getAmwayNotificationSectionForCurrentUserWithPageData(
			final PageableData pageableData, final AmwayNotificationUserActionStatus[] statuses, final String messageType);

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
