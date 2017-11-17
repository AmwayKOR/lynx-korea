package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacYourOpportunityComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacYourOpportunityComponentModel}
 */
@Controller("AmwayApacYourOpportunityComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacYourOpportunityComponent)
public class AmwayApacYourOpportunityComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacYourOpportunityComponentModel>
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
			final AmwayApacYourOpportunityComponentModel component)
	{
		populateData(model, component);
	}
}
