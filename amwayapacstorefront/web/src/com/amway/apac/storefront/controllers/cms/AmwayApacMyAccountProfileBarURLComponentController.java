package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacMyAccountProfileBarURLComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacMyAccountProfileBarURLComponentModel}
 *
 * @author Aaron Yong
 */
@Controller("AmwayApacMyAccountProfileBarURLComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacMyAccountProfileBarURLComponent)
public class AmwayApacMyAccountProfileBarURLComponentController
		extends AmwayApacAbstractCMSLinkComponentController<AmwayApacMyAccountProfileBarURLComponentModel>
{

	/**
	 * Populates the url for the link in the view model.
	 *
	 * @param request
	 *           http request
	 * @param model
	 *           view model
	 * @param component
	 *           component
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AmwayApacMyAccountProfileBarURLComponentModel component)
	{
		populateLinkUrl(model, component);
	}
}

