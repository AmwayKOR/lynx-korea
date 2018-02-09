package com.amway.apac.message.center.notification.facades.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
import com.amway.apac.message.center.notification.NotificationSearchParamData;
import com.amway.apac.message.center.notification.facades.AmwayApacNotificationFacade;
import com.amway.apac.message.center.notification.services.AmwayApacNotificationService;


/**
 * Default implementation of {@link AmwayApacNotificationFacade}
 *
 * @author Aaron Yong
 *
 */
public class DefaultAmwayApacNotificationFacade implements AmwayApacNotificationFacade
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacNotificationFacade.class);

	/** The MAX_PAGE_LIMIT Constant. */
	public static final int MAX_PAGE_LIMIT = 100;

	/** The user service. */
	private UserService userService;

	/** The session service. */
	private SessionService sessionService;

	/** The amway apac notification service. */
	private AmwayApacNotificationService amwayApacNotificationService;

	/** The amway apac notification section populator. */
	private Converter<SearchPageData<AmwayNotificationModel>, SearchPageData<AmwayApacNotificationData>> amwayApacNotificationSectionConverter;

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public SearchPageData<AmwayApacNotificationData> getNotificationsForCurrentUser(final SearchPageData userParams,
			final String messageType)
	{
		final NotificationSearchParamData notificationSearchParam = setNotificationSearchParams(userParams, messageType);
		final SearchPageData<AmwayNotificationModel> searchData = amwayApacNotificationService
				.getNotifications(notificationSearchParam);

		return amwayApacNotificationSectionConverter.convert(searchData);
	}

	private NotificationSearchParamData setNotificationSearchParams(final SearchPageData userParams, final String messageType)
	{
		final NotificationSearchParamData searchParams = new NotificationSearchParamData();
		searchParams.setSearchPageData(userParams);
		searchParams.setCurrentCustomer((CustomerModel) getUserService().getCurrentUser());
		searchParams.setSearchText(StringUtils.EMPTY);
		searchParams.setAccountClassficationCode(getSessionService().getAttribute(ACCOUNT_CLASSIFICATION_CODE));

		if (!StringUtils.isEmpty(messageType))
		{
			searchParams.setNotificationStatuses(Arrays.asList(AmwayNotificationUserActionStatus.valueOf(messageType)));
		}
		else
		{
			final List<AmwayNotificationUserActionStatus> notificationStatuses = Arrays
					.asList(AmwayNotificationUserActionStatus.UNREAD, AmwayNotificationUserActionStatus.READ);
			searchParams.setNotificationStatuses(notificationStatuses);
		}

		return searchParams;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public void changeUserNotificationStatus(final String notificationCode, final AmwayNotificationUserActionStatus newStatus)
	{
		validateParameterNotNullStandardMessage("Notification code", notificationCode);
		validateParameterNotNullStandardMessage("New Status", newStatus);

		if (LOGGER.isInfoEnabled())
		{
			LOGGER.debug(new StringBuilder(100).append("Parameters=[").append(notificationCode).append(", ").append(newStatus)
					.append("]").toString());
		}

		final AmwayNotificationModel notification = amwayApacNotificationService.getNotificationByCode(notificationCode);
		amwayApacNotificationService.changeUserNotificationStatus(notification, (CustomerModel) userService.getCurrentUser(),
				newStatus);
	}

	/**
	 * @return the amwayApacNotificationService
	 */
	public AmwayApacNotificationService getAmwayApacNotificationService()
	{
		return amwayApacNotificationService;
	}

	/**
	 * @param amwayApacNotificationService
	 *           the amwayApacNotificationService to set
	 */
	public void setAmwayApacNotificationService(final AmwayApacNotificationService amwayApacNotificationService)
	{
		this.amwayApacNotificationService = amwayApacNotificationService;
	}

	/**
	 * @return the amwayApacNotificationSectionConverter
	 */
	public Converter<SearchPageData<AmwayNotificationModel>, SearchPageData<AmwayApacNotificationData>> getAmwayApacNotificationSectionConverter()
	{
		return amwayApacNotificationSectionConverter;
	}

	/**
	 * @param amwayApacNotificationSectionConverter
	 *           the amwayApacNotificationSectionConverter to set
	 */
	public void setAmwayApacNotificationSectionConverter(
			final Converter<SearchPageData<AmwayNotificationModel>, SearchPageData<AmwayApacNotificationData>> amwayApacNotificationSectionConverter)
	{
		this.amwayApacNotificationSectionConverter = amwayApacNotificationSectionConverter;
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
}
