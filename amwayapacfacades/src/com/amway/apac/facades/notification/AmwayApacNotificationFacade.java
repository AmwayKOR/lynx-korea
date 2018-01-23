package com.amway.apac.facades.notification;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.components.CMSParagraphComponentModel;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apacfacades.notification.data.AmwayApacNotificationSectionData;


/**
 * @author Aaron Yong
 *
 */
public interface AmwayApacNotificationFacade
{

	/**
	 * Gets the amway notification section.
	 *
	 * @param pageNumber
	 *           the page number
	 * @param pageSize
	 *           the page size
	 * @param sortCode
	 *           the sort code
	 * @return the amway notification section
	 */
	AmwayApacNotificationSectionData getAmwayNotificationSection(int pageNumber, int pageSize, final String sortCode);

	/**
	 * @param compId
	 * @return message content
	 * @throws CMSItemNotFoundException
	 */
	CMSParagraphComponentModel getDetailedMessage(String compId) throws CMSItemNotFoundException;

	/**
	 * @param pageNumber
	 * @param pageSize
	 * @param sortCode
	 * @param status
	 * @return
	 */
	AmwayApacNotificationSectionData getAmwayNotificationSectionForCurrentUser(int pageNumber, int pageSize, String sortCode,
			AmwayNotificationUserActionStatus[] statuses);

	/**
	 * @param pageNumber
	 * @param pageSize
	 * @param sortCode
	 * @param status
	 * @return
	 */
	AmwayApacNotificationSectionData getAmwayNotificationSectionForCurrentUser(int pageNumber, int pageSize, String sortCode,
			AmwayNotificationUserActionStatus[] statuses, String searchText);

	boolean changeUserNotificationStatus(String notificationCode, AmwayNotificationUserActionStatus newStatus);



}
