package com.amway.apac.message.center.notification.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE;
import static com.amway.apac.message.center.model.AmwayNotificationModel.CODE;
import static com.amway.apac.message.center.model.AmwayNotificationModel.SITE;
import static com.amway.apac.message.center.model.AmwayNotificationUserActionModel.NOTIFICATION;
import static com.amway.apac.message.center.model.AmwayNotificationUserActionModel.USER;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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

	private static final String ERROR_MESSAGE_NULL_PAGEABLE_DATA = "[pageableData] must not be null";
	private static final String ERROR_MESSAGE_NULL_CUSTOMER = "[customer] must not be null";
	private static final String ERROR_MESSAGE_NULL_NOTIFICATION_CODE = "[notificationCode] field can not be null";

	private AmwayApacNotificationDao amwayApacNotificationDao;
	private GenericDao<AmwayNotificationUserActionModel> amwayNotificationUserActionDao;
	private GenericDao<AmwayNotificationModel> amwayNotificationDao;
	private BaseSiteService baseSiteService;
	private ModelService modelService;
	private SessionService sessionService;


	@Override
	public SearchPageData<AmwayNotificationModel> getNotificationsByMapping(final PageableData pageableData,
			final CustomerModel customer, final List<AmwayNotificationUserActionStatus> statuses, final String searchText)
	{
		ServicesUtil.validateParameterNotNull(pageableData, ERROR_MESSAGE_NULL_PAGEABLE_DATA);
		ServicesUtil.validateParameterNotNull(customer, ERROR_MESSAGE_NULL_CUSTOMER);
		final String accountClassficationCode = getSessionService().getAttribute(ACCOUNT_CLASSIFICATION_CODE);

		return getAmwayApacNotificationDao().getNotificationsByMapping(pageableData, getBaseSiteService().getCurrentBaseSite(),
				customer, statuses, searchText, accountClassficationCode);
	}

	@Override
	public AmwayNotificationModel getNotificationByCode(final String notificationCode)
	{
		ServicesUtil.validateParameterNotNull(notificationCode, ERROR_MESSAGE_NULL_NOTIFICATION_CODE);
		final Map<String, Object> params = new HashMap<>();
		params.put(CODE, notificationCode);
		params.put(SITE, getBaseSiteService().getCurrentBaseSite());
		final List<AmwayNotificationModel> results = getAmwayNotificationDao().find(params);
		return results.iterator().next();
	}

	@Override
	public void changeUserNotificationStatus(final AmwayNotificationModel notification, final CustomerModel customer,
			final AmwayNotificationUserActionStatus newStatus)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put(NOTIFICATION, notification);
		params.put(USER, customer);
		final List<AmwayNotificationUserActionModel> results = getAmwayNotificationUserActionDao().find(params);

		if (CollectionUtils.isNotEmpty(results) && results.size() == 1)
		{
			final AmwayNotificationUserActionModel notificationMapping = results.get(0);
			notificationMapping.setStatus(newStatus);
			modelService.save(notificationMapping);
		}
		else
		{
			final AmwayNotificationUserActionModel userNotificationMapping = modelService
					.create(AmwayNotificationUserActionModel.class);
			userNotificationMapping.setNotification(notification);
			userNotificationMapping.setUser(customer);
			userNotificationMapping.setStatus(newStatus);
			modelService.save(userNotificationMapping);
		}
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
