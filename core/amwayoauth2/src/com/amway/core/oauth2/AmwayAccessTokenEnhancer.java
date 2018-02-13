package com.amway.core.oauth2;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.jalo.JaloItemNotFoundException;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.amway.core.model.AmwayTerminalModel;
import com.amway.core.pos.service.AmwayPOSService;


/**
 * Enhancer for access token to add custom fields -terminal_id for the mac address provided in request.
 *
 */
public class AmwayAccessTokenEnhancer implements TokenEnhancer
{
	/**
	 *
	 */
	private static final String STORE = "store";
	private static final String EMPLOYEE_ID = "employeeId";
	private static final String EMPLOYEE_NAME = "employeeName";
	private static final String API_CONTEXT_ROOT = "apiContextRoot";
	private static final String AMWAYCOMMERCEWEBSERVICES_WEBROOT = "amwaycommercewebservices.webroot";
	private static final String TERMINAL_ID = "terminalId";
	private static final String MAC_ADDRESS = "mac_address";

	@Resource(name = "amwayPOSService")
	private AmwayPOSService amwayPOSService;

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	@Override
	public OAuth2AccessToken enhance(final OAuth2AccessToken accessToken, final OAuth2Authentication authentication)
			throws AuthenticationException
	{
		final DefaultOAuth2AccessToken tempResult = (DefaultOAuth2AccessToken) accessToken;
		final Map<String, String> params = authentication.getOAuth2Request().getRequestParameters();
		final Map<String, Object> additionalInformation = new HashMap<>();
		final String macAddress = params.get(MAC_ADDRESS);
		AmwayTerminalModel terminalModel = null;
		if (StringUtils.isNotBlank(macAddress))
		{
			terminalModel = amwayPOSService.getPOSTerminalByMacAddress(macAddress);
			if (terminalModel != null)
			{
				additionalInformation.put(TERMINAL_ID, terminalModel.getId());
				final PointOfServiceModel pos = terminalModel.getPointOfService();
				if (pos != null)
				{
					additionalInformation.put(STORE, pos.getName());
				}
			}
		}
		final String webroot = configurationService.getConfiguration().getString(AMWAYCOMMERCEWEBSERVICES_WEBROOT);
		if (!webroot.isEmpty())
		{
			additionalInformation.put(API_CONTEXT_ROOT, webroot);
		}
		try {
			final User user = UserManager.getInstance().getUserByLogin((String) authentication.getPrincipal());
			additionalInformation.put(EMPLOYEE_NAME, user.getName());
			additionalInformation.put(EMPLOYEE_ID, user.getUid());
		} catch (JaloItemNotFoundException e){
			// thown by UserManager   no null is being returned
		}

		tempResult.setAdditionalInformation(additionalInformation);
		final OAuth2AccessToken result = tempResult;
		return result;
	}
}
