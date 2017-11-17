package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacExperienceTheStarterKitComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacExperienceTheStarterKitComponentModel}
 */
@Controller("AmwayApacExperienceTheStarterKitComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacExperienceTheStarterKitComponent)
public class AmwayApacExperienceTheStarterKitComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacExperienceTheStarterKitComponentModel>
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
			final AmwayApacExperienceTheStarterKitComponentModel component)
	{
		populateData(model, component);
	}
}
