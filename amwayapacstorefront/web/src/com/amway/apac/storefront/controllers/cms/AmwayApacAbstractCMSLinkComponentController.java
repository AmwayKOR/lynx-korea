/**
 *
 */
package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commerceservices.url.UrlResolver;

import javax.annotation.Resource;

import org.springframework.ui.Model;

import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * @author avnishalaugh
 *
 */
public abstract class AmwayApacAbstractCMSLinkComponentController<T extends CMSLinkComponentModel>
		extends AbstractAcceleratorCMSComponentController<T>
{
	@Resource(name = "amwayApacCmsLinkComponentUrlResolver")
	private UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver;

	protected void populateLinkUrl(final Model model, final T component)
	{
		model.addAttribute(ControllerConstants.ModelParameters.LINK_URL_STRING,
				amwayApacCmsLinkComponentUrlResolver.resolve(component));
	}
}

