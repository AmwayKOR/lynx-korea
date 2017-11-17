package com.amway.apac.storefront.controllers.cms;

import de.hybris.platform.acceleratorfacades.device.ResponsiveMediaFacade;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.commerceservices.url.UrlResolver;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.Model;

import com.amway.apac.storefront.components.model.AmwayApacAbstractTextLinkImageComponentModel;
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

		final List<ImageData> mediaDataList = getResponsiveMediaFacade()
				.getImagesFromMediaContainer(component.getMedia(getCommerceCommonI18NService().getCurrentLocale()));

		if (null != component.getMedia())
		{
			model.addAttribute(ControllerConstants.ModelParameters.MEDIAS_STRING, mediaDataList);
		}
		if (null != component.getLink())
		{
			model.addAttribute(ControllerConstants.ModelParameters.LINK_URL_STRING,
					getAmwayApacCmsLinkComponentUrlResolver().resolve(component.getLink()));
		}
	}

	/**
	 * @return the responsiveMediaFacade
	 */
	public ResponsiveMediaFacade getResponsiveMediaFacade()
	{
		return responsiveMediaFacade;
	}

	/**
	 * @param responsiveMediaFacade the responsiveMediaFacade to set
	 */
	public void setResponsiveMediaFacade(ResponsiveMediaFacade responsiveMediaFacade)
	{
		this.responsiveMediaFacade = responsiveMediaFacade;
	}

	/**
	 * @return the commerceCommonI18NService
	 */
	public CommerceCommonI18NService getCommerceCommonI18NService()
	{
		return commerceCommonI18NService;
	}

	/**
	 * @param commerceCommonI18NService the commerceCommonI18NService to set
	 */
	public void setCommerceCommonI18NService(CommerceCommonI18NService commerceCommonI18NService)
	{
		this.commerceCommonI18NService = commerceCommonI18NService;
	}

	/**
	 * @return the amwayApacCmsLinkComponentUrlResolver
	 */
	public UrlResolver<CMSLinkComponentModel> getAmwayApacCmsLinkComponentUrlResolver()
	{
		return amwayApacCmsLinkComponentUrlResolver;
	}

	/**
	 * @param amwayApacCmsLinkComponentUrlResolver the amwayApacCmsLinkComponentUrlResolver to set
	 */
	public void setAmwayApacCmsLinkComponentUrlResolver(UrlResolver<CMSLinkComponentModel> amwayApacCmsLinkComponentUrlResolver)
	{
		this.amwayApacCmsLinkComponentUrlResolver = amwayApacCmsLinkComponentUrlResolver;
	}
}
