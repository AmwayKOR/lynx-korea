package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.core.servicelayer.data.SortData;
import de.hybris.platform.servicelayer.search.paginated.util.PaginatedSearchUtils;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
import com.amway.apac.message.center.notification.facades.AmwayApacNotificationFacade;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * @author Aaron Yong : Controller for Message Center
 *
 */
@Controller
@RequestMapping("/message-center")
public class MessageCenterPageController extends AbstractPageController
{
	/** The BREADCRUMBS Constant String. */
	private static final String BREADCRUMBS_ATTR = "breadcrumbs";

	/** MESSAGE CENTER CMS PAGE Constant String. */
	private static final String MESSAGE_CENTER_CMS_PAGE = "message-center";

	private static final int RESULTS_COUNT_FOR_PAGINATION = 5;
	public static final int MAX_PAGE_LIMIT = 100;

	@Resource(name = "businessCenterBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder businessCenterBreadcrumbBuilder;

	@Resource(name = "amwayApacNotificationFacade")
	private AmwayApacNotificationFacade amwayApacNotificationFacade;

	public enum ShowMode
	{
		// Constant names cannot be changed due to their usage in dependant extensions, thus nosonar
		Page, // NOSONAR
		All // NOSONAR
	}

	/**
	 * Returns message center main page containing notifications list.
	 *
	 * @param model
	 *           the model
	 * @return message center CMS page
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String getMessageCenter(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(MESSAGE_CENTER_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MESSAGE_CENTER_CMS_PAGE));
		model.addAttribute(BREADCRUMBS_ATTR, businessCenterBreadcrumbBuilder
				.getBreadcrumbs(ControllerConstants.GeneralConstants.MESSAGE_CENTER_PAGE_BREADCRUMB_KEY));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

		return getViewForPage(model);
	}

	@ResponseBody
	@RequestMapping(value = "/messagelist-data", method = RequestMethod.GET)
	public SearchPageData getNotificationSectionData(final Model model,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "pageSize", defaultValue = "5") final int pageSize,
			@RequestParam(value = "sortCode", required = false) final String sortCode,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "messageType", required = false) final String messageType)
	{

		final SearchPageData<AmwayApacNotificationData> searchPageData = amwayApacNotificationFacade
				.getNotificationsForCurrentUser(setUserCustomParams(page, pageSize, showMode, sortCode), messageType);

		populateModel(model, searchPageData, showMode);

		return searchPageData;
	}

	/**
	 * @param model
	 * @param searchPageData
	 * @param showMode
	 */
	private void populateModel(final Model model, final SearchPageData<AmwayApacNotificationData> searchPageData,
			final ShowMode showMode)
	{
		model.addAttribute("numberPagesShown", Integer.valueOf(RESULTS_COUNT_FOR_PAGINATION));
		model.addAttribute("searchPageData", searchPageData);

		model.addAttribute("countOfNotifications", Long.valueOf(searchPageData.getPagination().getTotalNumberOfResults()));
	}


	/**
	 * Sets the user params.
	 *
	 * @param page
	 *           the page
	 * @param pageSize
	 *           the page size
	 * @param showMode
	 *           the show mode
	 * @param sortCode
	 *           the sort code
	 * @return the search page data
	 */
	private SearchPageData setUserCustomParams(final int page, final int pageSize, final ShowMode showMode, final String sortCode)
	{
		final SearchPageData userParams = new SearchPageData();

		final PaginationData paginationData = PaginatedSearchUtils.createPaginationData(pageSize, page, false);

		if (ShowMode.All == showMode)
		{
			paginationData.setNeedsTotal(true);
		}

		userParams.setPagination(paginationData);

		if (!StringUtils.isEmpty(sortCode))
		{
			final SortData sortData = PaginatedSearchUtils.createSortData(sortCode, false);
			userParams.setResults(Arrays.asList(sortData));
		}

		return userParams;
	}

	/**
	 * Change notification status of notification passed through form.
	 *
	 * @param messageCode
	 *           the message code
	 * @param newStatus
	 *           the new status
	 */
	@RequestMapping(value = "/change-status", method = RequestMethod.POST)
	public void changeNotificationStatus(@RequestParam(value = "messageCode", required = true) final String messageCode,
			@RequestParam(value = "newStatus", required = true) final String newStatus)
	{
		amwayApacNotificationFacade.changeUserNotificationStatus(messageCode, AmwayNotificationUserActionStatus.valueOf(newStatus));

	}

	@RequestMapping(value = "/dummyDetail", method = RequestMethod.GET)
	public String getPopUp(final Model model)
	{
		return "fragments/messagecenter/messageNotificationPopUp";
	}
}
