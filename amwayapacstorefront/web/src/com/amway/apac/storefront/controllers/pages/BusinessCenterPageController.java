package com.amway.apac.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amway.apac.facades.notification.AmwayApacNotificationFacade;
import com.amway.apac.message.center.enums.AmwayNotificationUserActionStatus;
import com.amway.apac.storefront.controllers.ControllerConstants;
import com.amway.apacfacades.notification.data.AmwayApacNotificationSectionData;


/**
 * @author Aaron Yong : Controller for Business Center
 *
 */
@Controller
@RequestMapping("/business-center")
public class BusinessCenterPageController extends AbstractSearchPageController
{
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


}
