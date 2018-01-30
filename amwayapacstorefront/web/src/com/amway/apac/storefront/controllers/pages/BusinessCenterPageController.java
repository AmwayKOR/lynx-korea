package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.components.CMSParagraphComponentModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
import com.amway.apac.message.center.notification.facades.AmwayApacNotificationFacade;
import com.amway.apac.storefront.controllers.ControllerConstants;
import com.amway.apac.storefront.forms.AmwayApacNotificationEntryForm;


/**
 * @author Aaron Yong : Controller for Business Center
 *
 */
@Controller
@RequestMapping("/business-center")
public class BusinessCenterPageController extends AbstractSearchPageController
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessCenterPageController.class);

	/** The BREADCRUMBS Constant String. */
	private static final String BREADCRUMBS_ATTR = "breadcrumbs";

	/** MESSAGE CENTER CMS PAGE Constant String. */
	private static final String MESSAGE_CENTER_CMS_PAGE = "message-center";

	@Resource(name = "businessCenterBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder businessCenterBreadcrumbBuilder;

	@Resource(name = "amwayApacNotificationFacade")
	private AmwayApacNotificationFacade amwayApacNotificationFacade;

	/**
	 * Returns message center main page containing notifications list
	 *
	 * @param model
	 * @param page
	 * @param showMode
	 * @param pageSize
	 * @param sortCode
	 * @param status
	 * @return message center CMS page
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/message-center", method = RequestMethod.GET)
	@RequireHardLogIn
	public String notificationMain(final Model model, @RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "pageSize", defaultValue = "5") final int pageSize,
			@RequestParam(value = "type", required = false) final String messageType,
			@RequestParam(value = "sortCode", required = false) final String sortCode,
			@RequestParam(value = "status", required = false) final String status,
			@RequestHeader(value = "x-requested-with", required = false) final String requestType,
			final RedirectAttributes redirectModel)

			throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(MESSAGE_CENTER_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MESSAGE_CENTER_CMS_PAGE));
		model.addAttribute(BREADCRUMBS_ATTR, businessCenterBreadcrumbBuilder
				.getBreadcrumbs(ControllerConstants.GeneralConstants.MESSAGE_CENTER_PAGE_BREADCRUMB_KEY));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

		AmwayNotificationUserActionStatus[] statuses = new AmwayNotificationUserActionStatus[]
		{ AmwayNotificationUserActionStatus.UNREAD, AmwayNotificationUserActionStatus.READ };
		if (status != null)
		{
			statuses = new AmwayNotificationUserActionStatus[]
			{ AmwayNotificationUserActionStatus.valueOf(status) };
		}

		final PageableData pageableData = createPageableData(page, pageSize, sortCode, showMode);
		final SearchPageData<AmwayApacNotificationData> searchPageData = amwayApacNotificationFacade
				.getAmwayNotificationSectionForCurrentUserWithPageData(pageableData, statuses, "");
		model.addAttribute("countOfNotifications", Long.valueOf(searchPageData.getPagination().getTotalNumberOfResults()));
		populateModel(model, searchPageData, showMode);

		if ("XMLHttpRequest".equals(requestType))
		{
			return ControllerConstants.Views.Fragments.MessageCenter.MessageCenterFragment;
		}
		else
		{
			return getViewForPage(model);
		}

	}

	/**
	 * Returns CMS component of detailed message.
	 *
	 * @param model
	 * @param compId
	 *           the component id
	 * @param messageCode
	 *           message code
	 * @param request
	 *           HTTP request
	 * @param response
	 *           HTTP response
	 * @return detailed message CMS component
	 */
	@RequestMapping(value = "/detailed-message", method = RequestMethod.GET)
	@RequireHardLogIn
	public String getDetailedMessage(final Model model, @RequestParam(value = "compId") final String compId,
			@RequestParam(value = "messageCode") final String messageCode, final HttpServletRequest request,
			final HttpServletResponse response)
	{
		CMSParagraphComponentModel messageComponent = null;
		try
		{
			model.addAttribute("messageCode", messageCode);
			messageComponent = amwayApacNotificationFacade.getDetailedMessage(compId);
		}
		catch (final CMSItemNotFoundException e)
		{
			LOGGER.error(new StringBuilder().append("NO CMS component found with ID : ").append(compId).toString());
		}
		model.addAttribute("messageContent", messageComponent);

		return ControllerConstants.Views.Pages.MessageCenter.DetailedMessagePage;
	}

	/**
	 * Change notification status of notification passed through form.
	 *
	 * @param formentries
	 *           notification form entries
	 * @return true, if status changed successfully
	 */
	@ResponseBody
	@RequestMapping(value = "/change-status", method = RequestMethod.POST)
	public boolean changeNotificationStatus(@ModelAttribute final AmwayApacNotificationEntryForm formentries)
	{
		final String[] checkedMessageCodes = formentries.getCheckedMessagesCodes().split(",");
		final String messageStatus = formentries.getMessageStatus();
		boolean isSuccess = true;

		for (final String messageCode : checkedMessageCodes)
		{
			isSuccess = isSuccess && amwayApacNotificationFacade.changeUserNotificationStatus(messageCode,
					AmwayNotificationUserActionStatus.valueOf(messageStatus));
		}
		return isSuccess;
	}

}
