package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacBuildYourBusinessComponentModel;
import com.amway.apac.storefront.components.model.AmwayApacNewProductComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacBuildYourBusinessComponentModel}
 */
@Controller("AmwayApacNewProductComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacNewProductComponent)
public class AmwayApacNewProductComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacNewProductComponentModel>
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
			final AmwayApacNewProductComponentModel component)
	{
		populateData(model, component);
	}
}
