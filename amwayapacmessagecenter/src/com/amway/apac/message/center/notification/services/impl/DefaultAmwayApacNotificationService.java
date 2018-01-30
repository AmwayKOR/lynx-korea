package com.amway.apac.message.center.notification.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE;
import static com.amway.apac.message.center.model.AmwayNotificationModel.CODE;
import static com.amway.apac.message.center.model.AmwayNotificationModel.SITE;
import static com.amway.apac.message.center.model.AmwayNotificationUserActionModel.NOTIFICATION;
import static com.amway.apac.message.center.model.AmwayNotificationUserActionModel.USER;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.model.AmwayNotificationUserActionModel;
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

	private static final String ERROR_MESSAGE_NULL_PAGEABLE_DATA = "[pageableData] must not be null";
	private static final String ERROR_MESSAGE_NULL_CUSTOMER = "[customer] must not be null";
	private static final String ERROR_MESSAGE_NULL_NOTIFICATION_CODE = "[notificationCode] field can not be null";
	private static final String NOTIFICATION_STRING = "Notification";
	private static final String CUSTOMER_STRING = "customer";
	private static final String NEW_STATUS_STRING = "newStatus";

	private AmwayApacNotificationDao amwayApacNotificationDao;
	private GenericDao<AmwayNotificationUserActionModel> amwayNotificationUserActionDao;
	private GenericDao<AmwayNotificationModel> amwayNotificationDao;
	private BaseSiteService baseSiteService;
	private ModelService modelService;
	private SessionService sessionService;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<AmwayNotificationModel> getNotifications(final PageableData pageableData, final CustomerModel customer,
			final List<AmwayNotificationUserActionStatus> statuses, final String searchText)
	{
		validateParameterNotNull(pageableData, ERROR_MESSAGE_NULL_PAGEABLE_DATA);
		validateParameterNotNull(customer, ERROR_MESSAGE_NULL_CUSTOMER);
		final String accountClassficationCode = getSessionService().getAttribute(ACCOUNT_CLASSIFICATION_CODE);

		return getAmwayApacNotificationDao().getNotifications(pageableData, getBaseSiteService().getCurrentBaseSite(), customer,
				statuses, searchText, accountClassficationCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayNotificationModel getNotificationByCode(final String notificationCode)
	{
		validateParameterNotNull(notificationCode, ERROR_MESSAGE_NULL_NOTIFICATION_CODE);

		final BaseSiteModel currentBasesite = getBaseSiteService().getCurrentBaseSite();
		final Map<String, Object> params = new HashMap<>(2);
		params.put(CODE, notificationCode);
		params.put(SITE, currentBasesite);
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(100).append("Searching AmwayNotificationModels for notificationCode [")
					.append(notificationCode).append("] and basesite [").append(currentBasesite.getName()).append("].").toString());
		}
		final List<AmwayNotificationModel> results = getAmwayNotificationDao().find(params);
		return results.iterator().next();
	}

	@Override
	public void changeUserNotificationStatus(final AmwayNotificationModel notification, final CustomerModel customer,
			final AmwayNotificationUserActionStatus newStatus)
	{
		validateParameterNotNullStandardMessage(NOTIFICATION_STRING, notification);
		validateParameterNotNullStandardMessage(CUSTOMER_STRING, customer);
		validateParameterNotNullStandardMessage(NEW_STATUS_STRING, newStatus);

		final List<AmwayNotificationUserActionModel> results = getNotificationActionByUserAndNotification(customer, notification);

		if (CollectionUtils.isNotEmpty(results))
		{
			LOGGER.info(new StringBuilder(100).append(results.size()).append(" match found. Updating the status to ")
					.append(newStatus.getCode()).toString());
			final AmwayNotificationUserActionModel notificationMapping = results.get(0);
			notificationMapping.setStatus(newStatus);
			getModelService().save(notificationMapping);
		}
		else
		{
			if (LOGGER.isInfoEnabled())
			{
				LOGGER.info(new StringBuilder(100)
						.append("Could not find any instance of AmwayNotificationUserActionModels. Creating a new instance.")
						.toString());
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
	public List<AmwayNotificationUserActionModel> getNotificationActionByUserAndNotification(final CustomerModel customer,
			final AmwayNotificationModel notification)
	{
		final Map<String, Object> params = new HashMap<>(2);
		params.put(NOTIFICATION, notification);
		params.put(USER, customer);
		if (LOGGER.isDebugEnabled())
		{
			LOGGER.info(new StringBuilder(100).append("Searching AmwayNotificationUserActionModels for notificationCode [")
					.append(notification.getCode()).append("] and user [").append(customer.getCustomerID()).append("].").toString());
		}

		return getAmwayNotificationUserActionDao().find(params);
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
	 * @return the amwayNotificationUserActionDao
	 */
	public GenericDao<AmwayNotificationUserActionModel> getAmwayNotificationUserActionDao()
	{
		return amwayNotificationUserActionDao;
	}

	/**
	 * @param amwayNotificationUserActionDao
	 *           the amwayNotificationUserActionDao to set
	 */
	@Required
	public void setAmwayNotificationUserActionDao(
			final GenericDao<AmwayNotificationUserActionModel> amwayNotificationUserActionDao)
	{
		this.amwayNotificationUserActionDao = amwayNotificationUserActionDao;
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
	 * @return the amwayNotificationDao
	 */
	public GenericDao<AmwayNotificationModel> getAmwayNotificationDao()
	{
		return amwayNotificationDao;
	}

	/**
	 * @param amwayNotificationDao
	 *           the amwayNotificationDao to set
	 */
	@Required
	public void setAmwayNotificationDao(final GenericDao<AmwayNotificationModel> amwayNotificationDao)
	{
		this.amwayNotificationDao = amwayNotificationDao;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
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


}
