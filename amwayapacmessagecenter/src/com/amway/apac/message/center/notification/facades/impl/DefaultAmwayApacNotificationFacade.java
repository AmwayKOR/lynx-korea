package com.amway.apac.message.center.notification.facades.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.components.CMSParagraphComponentModel;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
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

	/** The cms component service. */
	private CMSComponentService cmsComponentService;

	/** The amway apac notification service. */
	private AmwayApacNotificationService amwayApacNotificationService;

	/** The amway apac notification section populator. */
	private Converter<SearchPageData<AmwayNotificationModel>, SearchPageData<AmwayApacNotificationData>> amwayApacNotificationSectionConverter;

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public CMSParagraphComponentModel getDetailedMessage(final String compId) throws CMSItemNotFoundException
	{
		return getCmsComponentService().getAbstractCMSComponent(compId);
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public SearchPageData<AmwayApacNotificationData> getAmwayNotificationSectionForCurrentUserWithPageData(
			final PageableData pageableData, final AmwayNotificationUserActionStatus[] statuses, final String messageType)
	{
		final List<AmwayNotificationUserActionStatus> notificationStatuses = Arrays.asList(statuses);
		final SearchPageData<AmwayNotificationModel> searchData = amwayApacNotificationService.getNotifications(pageableData,
				(CustomerModel) getUserService().getCurrentUser(), notificationStatuses, "");

		final SearchPageData<AmwayApacNotificationData> searchPageData = amwayApacNotificationSectionConverter.convert(searchData);
		return searchPageData;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public boolean changeUserNotificationStatus(final String notificationCode, final AmwayNotificationUserActionStatus newStatus)
	{
		validateParameterNotNullStandardMessage("Notification code", notificationCode);
		validateParameterNotNullStandardMessage("New Status", newStatus);

		LOGGER.debug(new StringBuilder(100).append("Parameters=[").append(notificationCode).append(", ").append(newStatus)
				.append("]").toString());

		boolean successful = true;
		try
		{
			final AmwayNotificationModel notification = amwayApacNotificationService.getNotificationByCode(notificationCode);
			amwayApacNotificationService.changeUserNotificationStatus(notification, (CustomerModel) userService.getCurrentUser(),
					newStatus);
		}
		catch (final Exception e)
		{
			LOGGER.error(new StringBuilder(100).append("Error updating status of notification=").append(notificationCode)
					.append(", customer=").append(userService.getCurrentUser()).append(" to new status=").append(newStatus).toString(),
					e);
			successful = false;
		}
		return successful;
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
	 * Gets the cmsComponentService the cmsComponentService.
	 *
	 * @return the cmsComponentService
	 */
	public CMSComponentService getCmsComponentService()
	{
		return cmsComponentService;
	}

	/**
	 * @param cmsComponentService
	 *           the cmsComponentService to set
	 */
	public void setCmsComponentService(final CMSComponentService cmsComponentService)
	{
		this.cmsComponentService = cmsComponentService;
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
