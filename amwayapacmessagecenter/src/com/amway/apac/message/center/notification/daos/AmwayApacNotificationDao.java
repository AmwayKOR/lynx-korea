package com.amway.apac.message.center.notification.daos;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.apac.message.center.notification.NotificationSearchParamData;


/**
 * DAO layer for the message center notifications.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacNotificationDao extends GenericDao
{

	/**
	 * Returns AmwayNotificaionModels in the form of searchPageData for given baseSite, customer, statuses and search
	 * text.
	 *
	 * @param notificationSearchParam
	 *           the notification search param
	 * @param currentBaseSite
	 *           the current base site
	 * @return {@link AmwayNotificationModel}
	 * @throws IllegalArgumentException
	 *            if customer is null
	 */
	SearchPageData<AmwayNotificationModel> getNotifications(final NotificationSearchParamData notificationSearchParam,
			final BaseSiteModel currentBaseSite);

	/**
	 * @param customer
	 * @param notification
	 *
	 */
	AmwayNotificationUserActionModel getNotificationAction(final AmwayNotificationModel notification,
			final CustomerModel customer);

}
