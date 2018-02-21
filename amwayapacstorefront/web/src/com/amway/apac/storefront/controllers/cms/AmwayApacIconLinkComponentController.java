package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacIconLinkComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacIconLinkComponentModel}
 *
 * @author Avnish Alaugh
 */
@Controller("AmwayApacIconLinkComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacIconLinkComponent)
public class AmwayApacIconLinkComponentController
		extends AmwayApacAbstractCMSLinkComponentController<AmwayApacIconLinkComponentModel>
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
	protected void fillModel(final HttpServletRequest request, final Model model, final AmwayApacIconLinkComponentModel component)
	{
		populateLinkUrl(model, component);
	}

}