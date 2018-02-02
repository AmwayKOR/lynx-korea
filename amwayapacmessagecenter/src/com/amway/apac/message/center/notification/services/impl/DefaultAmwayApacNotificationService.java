package com.amway.apac.message.center.notification.services.impl;

import static com.amway.apac.message.center.model.AmwayNotificationModel.CODE;
import static com.amway.apac.message.center.model.AmwayNotificationModel.SITE;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
import com.amway.apac.message.center.notification.NotificationSearchParamData;
import com.amway.apac.message.center.notification.daos.AmwayApacNotificationDao;
import com.amway.apac.message.center.notification.services.AmwayApacNotificationService;


/**
 * Default implementation for {@link AmwayApacNotificationService}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacNotificationService implements AmwayApacNotificationService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacNotificationService.class);

	private static final String NOTIFICATION_STRING = "Notification";
	private static final String CUSTOMER_STRING = "Customer";
	private static final String NEW_STATUS_STRING = "New Status";
	private static final String PAGEABLE_DATA = "Pageable Data";

	private AmwayApacNotificationDao amwayApacNotificationDao;
	private BaseSiteService baseSiteService;
	private ModelService modelService;
	private UserService userService;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayNotificationModel> getNotifications(final NotificationSearchParamData notificationSearchParam)
	{
		validateParameterNotNullStandardMessage(PAGEABLE_DATA, notificationSearchParam.getPageableData());
		return getAmwayApacNotificationDao().getNotifications(notificationSearchParam, getBaseSiteService().getCurrentBaseSite());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayNotificationModel getNotificationByCode(final String notificationCode)
	{
		validateParameterNotNullStandardMessage(NOTIFICATION_STRING, notificationCode);

		final BaseSiteModel currentBasesite = getBaseSiteService().getCurrentBaseSite();
		final Map<String, Object> params = new HashMap<>(2);
		params.put(CODE, notificationCode);
		params.put(SITE, currentBasesite);
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(100).append("Searching AmwayNotificationModels for notificationCode [")
					.append(notificationCode).append("] and basesite [").append(currentBasesite.getName()).append("].").toString());
		}
		final List<AmwayNotificationModel> results = getAmwayApacNotificationDao().find(params);
		return CollectionUtils.isNotEmpty(results) ? results.iterator().next() : null;
	}

	@Override
	public void changeUserNotificationStatus(final AmwayNotificationModel notification, final CustomerModel customer,
			final AmwayNotificationUserActionStatus newStatus)
	{
		validateParameterNotNullStandardMessage(NOTIFICATION_STRING, notification);
		validateParameterNotNullStandardMessage(CUSTOMER_STRING, customer);
		validateParameterNotNullStandardMessage(NEW_STATUS_STRING, newStatus);

		final AmwayNotificationUserActionModel actionResult = getNotificationAction(notification, customer);

		if (Objects.nonNull(actionResult))
		{
			LOGGER.info(new StringBuilder(100).append("Notification action found. Updating the status to ")
					.append(newStatus.getCode()).toString());
			actionResult.setStatus(newStatus);
			getModelService().save(actionResult);
		}
		else
		{
			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(100)
						.append("Could not find any instance of AmwayNotificationUserAction. Creating a new instance.").toString());
			}
			final AmwayNotificationUserActionModel userNotificationMapping = getModelService()
					.create(AmwayNotificationUserActionModel.class);
			userNotificationMapping.setNotification(notification);
			userNotificationMapping.setUser(customer);
			userNotificationMapping.setStatus(newStatus);
			getModelService().save(userNotificationMapping);
		}
	}

	@Override
	public AmwayNotificationUserActionModel getNotificationActionByUserAndNotification(final CustomerModel customer,
			final AmwayNotificationModel notification)
	{
		return getNotificationAction(notification, customer);
	}

	@Override
	public AmwayNotificationUserActionModel getNotificationActionForCurrentUser(final AmwayNotificationModel notification)
	{
		final UserModel user = getUserService().getCurrentUser();
		AmwayNotificationUserActionModel userAction = null;

		if (user instanceof CustomerModel)
		{
			final CustomerModel customer = (CustomerModel) user;

			userAction = getNotificationAction(notification, customer);

		}
		return userAction;
	}

	protected AmwayNotificationUserActionModel getNotificationAction(final AmwayNotificationModel notification,
			final CustomerModel customer)
	{
		if (LOGGER.isDebugEnabled())
		{
			LOGGER.info(new StringBuilder(100).append("Searching AmwayNotificationUserActionModels for notificationCode [")
					.append(notification.getCode()).append("] and user [").append(customer.getCustomerID()).append("].").toString());
		}

		//		final List<AmwayNotificationUserActionModel> notificationActions = getAmwayApacNotificationDao()
		//				.getNotificationAction(notification, customer);
		//		validateIfSingleResult(notificationActions, "No notification action found.",
		//				new StringBuilder(50).append(notificationActions.size()).append(" notification actions found.").toString());

		return getAmwayApacNotificationDao().getNotificationAction(notification, customer);
	}

	/**
	 * @return the amwayApacNotificationDao
	 */
	public AmwayApacNotificationDao getAmwayApacNotificationDao()
	{
		return amwayApacNotificationDao;
	}

	/**
	 * @param amwayApacNotificationDao
	 *           the amwayApacNotificationDao to set
	 */
	@Required
	public void setAmwayApacNotificationDao(final AmwayApacNotificationDao amwayApacNotificationDao)
	{
		this.amwayApacNotificationDao = amwayApacNotificationDao;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}
