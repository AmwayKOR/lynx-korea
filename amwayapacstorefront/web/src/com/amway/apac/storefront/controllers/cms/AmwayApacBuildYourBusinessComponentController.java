package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacBuildYourBusinessComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacBuildYourBusinessComponentModel}
 *
 * @author Parvesh Goyal
 */
@Controller("AmwayApacBuildYourBusinessComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacBuildYourBusinessComponent)
public class AmwayApacBuildYourBusinessComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacBuildYourBusinessComponentModel>
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
			final AmwayApacBuildYourBusinessComponentModel component)
	{
		populateData(model, component);
	}
}
