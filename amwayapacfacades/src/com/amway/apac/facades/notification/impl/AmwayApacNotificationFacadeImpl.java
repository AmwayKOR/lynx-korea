/**
 *
 */
package com.amway.apac.facades.notification.impl;

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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.apac.facades.notification.AmwayApacNotificationFacade;
import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.notification.services.AmwayApacNotificationService;
import com.amway.apacfacades.notification.data.AmwayApacNotificationSectionData;


/**
 * @author Aaron Yong
 *
 */
public class AmwayApacNotificationFacadeImpl implements AmwayApacNotificationFacade
{
	private static final Logger LOGGER = Logger.getLogger(AmwayApacNotificationFacadeImpl.class);
	public static final int MAX_PAGE_LIMIT = 100;

	private UserService userService;

	private AmwayApacNotificationService amwayApacNotificationService;
	private CMSComponentService cmsComponentService;
	private Converter<SearchPageData<AmwayNotificationModel>, AmwayApacNotificationSectionData> amwayApacNotificationSectionConverter;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.facades.notification.AmwayApacNotificationFacade#getAmwayNotificationSection(int, int,
	 * java.lang.String)
	 */
	@Override
	public AmwayApacNotificationSectionData getAmwayNotificationSection(final int pageNumber, final int pageSize,
			final String sortCode)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.facades.notification.AmwayApacNotificationFacade#getDetailedMessage(java.lang.String)
	 */
	@Override
	public CMSParagraphComponentModel getDetailedMessage(final String compId) throws CMSItemNotFoundException
	{
		// YTODO Auto-generated method stub
		return null;
	}

	@Override
	public AmwayApacNotificationSectionData getAmwayNotificationSectionForCurrentUser(final int pageNumber, final int pageSize,
			final String sortCode, final AmwayNotificationUserActionStatus[] statuses)
	{
		return getAmwayNotificationSectionForCurrentUser(pageNumber, pageSize, sortCode, statuses, StringUtils.EMPTY);

	}

	@Override
	public AmwayApacNotificationSectionData getAmwayNotificationSectionForCurrentUser(final int pageNumber, final int pageSize,
			final String sortCode, final AmwayNotificationUserActionStatus[] statuses, final String searchText)
	{
		final PageableData pageableData = createPageableData(pageNumber, pageSize, sortCode);

		List<AmwayNotificationUserActionStatus> notificationStatuses = null;
		if (statuses != null && statuses.length > 0)
		{
			notificationStatuses = Arrays.asList(statuses);
		}

		final SearchPageData<AmwayNotificationModel> searchData = amwayApacNotificationService.getNotifications(pageableData,
				(CustomerModel) getUserService().getCurrentUser(), notificationStatuses, searchText);
		//		final SearchPageData<AmwayNotificationModel> searchData = amwayApacNotificationService.getNotifications(pageableData,
		//				(CustomerModel) getUserService().getCurrentUser(), notificationStatuses, searchText);
		return getAmwayApacNotificationSectionConverter().convert(searchData);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.facades.notification.AmwayApacNotificationFacade#changeUserNotificationStatus(java.lang.String,
	 * com.amway.apac.core.constants.GeneratedAmwayapacCoreConstants.Enumerations.AmwayApacNotificationStatusEnum)
	 */
	@Override
	public boolean changeUserNotificationStatus(final String notificationCode, final AmwayNotificationUserActionStatus newStatus)
	{
		// YTODO Auto-generated method stub
		return false;
	}

	protected PageableData createPageableData(final int pageNumber, final int pageSize, final String sortCode)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(pageNumber);
		pageableData.setPageSize(pageSize);
		pageableData.setSort(sortCode);
		return pageableData;
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
	public Converter<SearchPageData<AmwayNotificationModel>, AmwayApacNotificationSectionData> getAmwayApacNotificationSectionConverter()
	{
		return amwayApacNotificationSectionConverter;
	}

	/**
	 * @param amwayApacNotificationSectionConverter
	 *           the amwayApacNotificationSectionConverter to set
	 */
	public void setAmwayApacNotificationSectionConverter(
			final Converter<SearchPageData<AmwayNotificationModel>, AmwayApacNotificationSectionData> amwayApacNotificationSectionConverter)
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
