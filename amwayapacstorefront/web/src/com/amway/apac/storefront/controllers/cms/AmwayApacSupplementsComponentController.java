package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacSupplementsComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacSupplementsComponentModel}
 */
@Controller("AmwayApacSupplementsComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacSupplementsComponent)
public class AmwayApacSupplementsComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacSupplementsComponentModel>
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
			final AmwayApacSupplementsComponentModel component)
	{
		populateData(model, component);
	}
}
