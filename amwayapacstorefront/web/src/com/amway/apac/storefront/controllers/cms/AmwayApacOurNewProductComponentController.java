package com.amway.apac.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amway.apac.storefront.components.model.AmwayApacOurNewProductComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS {@link AmwayApacOurNewProductComponentModel}
 *
 * @author Parvesh Goyal
 */
@Controller("AmwayApacOurNewProductComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.AmwayApacOurNewProductComponent)
public class AmwayApacOurNewProductComponentController
		extends AmwayApacAbstractResponsiveBannerComponentController<AmwayApacOurNewProductComponentModel>
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
			final AmwayApacOurNewProductComponentModel component)
	{
		populateData(model, component);
	}
}
