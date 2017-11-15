package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.acceleratorfacades.device.ResponsiveMediaFacade;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.commerceservices.url.UrlResolver;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.Model;

import com.amway.apac.core.model.components.AmwayApacAbstractTextLinkImageComponentModel;
import com.amway.apac.storefront.controllers.ControllerConstants;


/**
 * Abstract Controller for {@link AmwayApacAbstractTextLinkImageComponentModel}
 *
 * @author Avnish Alaugh
 *
 */
public abstract class AmwayApacAbstractResponsiveBannerComponentController<T extends AmwayApacAbstractTextLinkImageComponentModel>
		extends AbstractAcceleratorCMSComponentController<T>
{
	@Resource(name = "responsiveMediaFacade")
	private ResponsiveMediaFacade responsiveMediaFacade;

	@Resource(name = "commerceCommonI18NService")
	private CommerceCommonI18NService commerceCommonI18NService;

	@Resource(name = "amwayApacCmsLinkComponentUrlResolver")
	private UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver;

	/**
	 * Populates the media list and link url
	 *
	 * @param model
	 *           view model
	 * @param component
	 * 
	 */
	protected void populateData(final Model model, final T component)
	{

		final List<ImageData> mediaDataList = responsiveMediaFacade
				.getImagesFromMediaContainer(component.getMedia(commerceCommonI18NService.getCurrentLocale()));

		if (null != component.getMedia())
		{
			model.addAttribute(ControllerConstants.ModelParameters.MEDIAS_STRING, mediaDataList);
		}
		if (null != component.getLink())
		{
			model.addAttribute(ControllerConstants.ModelParameters.LINK_URL_STRING,
					amwayApacCmsLinkComponentUrlResolver.resolve(component.getLink()));
		}
	}
}
