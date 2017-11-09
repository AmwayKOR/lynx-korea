package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commerceservices.url.UrlResolver;

import javax.annotation.Resource;

import org.springframework.ui.Model;

import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Abstract controller to populate the url for link components.
 *
 * @author Avnish Alaugh
 *
 */
public abstract class AmwayApacAbstractCMSLinkComponentController<T extends CMSLinkComponentModel>
		extends AbstractAcceleratorCMSComponentController<T>
{
	@Resource(name = "amwayApacCmsLinkComponentUrlResolver")
	private UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver;

	/**
	 * Populates the url for the link component.
	 *
	 * @param model
	 *           view model
	 * @param component
	 *           component
	 */
	protected void populateLinkUrl(final Model model, final T component)
	{
		model.addAttribute(ControllerConstants.ModelParameters.LINK_URL, amwayApacCmsLinkComponentUrlResolver.resolve(component));
	}
}

