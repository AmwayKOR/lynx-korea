package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
import com.amway.apac.message.center.notification.facades.AmwayApacNotificationFacade;
import com.amway.apac.storefront.controllers.ControllerConstants;
import com.amway.apac.storefront.forms.AmwayApacNotificationEntryForm;


/**
 * @author Aaron Yong : Controller for Message Center
 *
 */
@Controller
@RequestMapping("/message-center")
public class MessageCenterPageController extends AbstractSearchPageController
{
	/** The BREADCRUMBS Constant String. */
	private static final String BREADCRUMBS_ATTR = "breadcrumbs";

	/** MESSAGE CENTER CMS PAGE Constant String. */
	private static final String MESSAGE_CENTER_CMS_PAGE = "message-center";

	@Resource(name = "businessCenterBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder businessCenterBreadcrumbBuilder;

	@Resource(name = "amwayApacNotificationFacade")
	private AmwayApacNotificationFacade amwayApacNotificationFacade;

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

		final PageableData pageableData = createPageableData(page, pageSize, sortCode, showMode);
		final SearchPageData<AmwayApacNotificationData> searchPageData = amwayApacNotificationFacade
				.getNotificationsForCurrentUser(pageableData, messageType);

		populateModel(model, searchPageData, showMode);

		model.addAttribute("countOfNotifications", Long.valueOf(searchPageData.getPagination().getTotalNumberOfResults()));

		return searchPageData;
	}

	/**
	 * Change notification status of notification passed through form.
	 *
	 * @param formentries
	 *           notification form entries
	 */
	@RequestMapping(value = "/change-status", method = RequestMethod.POST)
	public void changeNotificationStatus(@ModelAttribute final AmwayApacNotificationEntryForm formentries)
	{
		final String[] checkedMessageCodes = formentries.getCheckedMessagesCodes().split(",");
		final String messageStatus = formentries.getMessageStatus();

		for (final String messageCode : checkedMessageCodes)
		{
			amwayApacNotificationFacade.changeUserNotificationStatus(messageCode,
					AmwayNotificationUserActionStatus.valueOf(messageStatus));
		}
	}
}
