package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.components.CMSParagraphComponentModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amway.apac.facades.notification.AmwayApacNotificationFacade;
import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.storefront.controllers.ControllerConstants;
import com.amway.apac.storefront.forms.AmwayApacNotificationEntryForm;
import com.amway.apacfacades.notification.data.AmwayApacNotificationSectionData;


/**
 * @author Aaron Yong : Controller for Business Center
 *
 */
@Controller
@RequestMapping("/business-center")
public class BusinessCenterPageController extends AbstractSearchPageController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessCenterPageController.class);

	private static final String BREADCRUMBS_ATTR = "breadcrumbs";
	// CMS Pages
	private static final String MESSAGE_CENTER_CMS_PAGE = "message-center";

	@Resource(name = "businessCenterBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder businessCenterBreadcrumbBuilder;

	@Autowired
	private AmwayApacNotificationFacade amwayApacNotificationFacade;

	@RequestMapping(value = "/message-center", method = RequestMethod.GET)
	@RequireHardLogIn
	public String businessInformation(final Model model, @RequestParam(value = "page", defaultValue = "1") final int page,
			@RequestParam(value = "pageSize", defaultValue = "10") final int pageSize,
			@RequestParam(value = "sortCode", required = false) final String sortCode,
			@RequestParam(value = "status", required = false) final String status, final RedirectAttributes redirectModel)
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
		final AmwayApacNotificationSectionData amwayApacNotificationSectionData = amwayApacNotificationFacade
				.getAmwayNotificationSectionForCurrentUser(page - 1, pageSize, sortCode, statuses, "");
		model.addAttribute("notification", amwayApacNotificationSectionData);

		return getViewForPage(model);
	}

	/**
	 * Gets the detailed message.
	 *
	 * @param model
	 *           the model
	 * @param compId
	 *           the component id
	 * @param request
	 *           the request
	 * @param response
	 *           the response
	 * @return the detailed message
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
