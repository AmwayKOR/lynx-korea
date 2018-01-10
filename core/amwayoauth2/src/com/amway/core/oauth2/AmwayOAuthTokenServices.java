package com.amway.core.oauth2;

import de.hybris.platform.webservicescommons.oauth2.token.provider.HybrisOAuthTokenServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;


/**
 * Custom token services for enhancing the oauth/token response with custom enhancer {@link AmwayAccessTokenEnhancer}
 *
 * @author jatinarora
 *
 */
public class AmwayOAuthTokenServices extends HybrisOAuthTokenServices
{

	private static final Logger LOG = Logger.getLogger(HybrisOAuthTokenServices.class);
	private TokenEnhancer amwayTokenEnhancer;

	/**
	 * @return the amwayTokenEnhancer
	 */
	public TokenEnhancer getAmwayTokenEnhancer()
	{
		return amwayTokenEnhancer;
	}

	/**
	 * @param amwayTokenEnhancer
	 *           the amwayTokenEnhancer to set
	 */
	@Required
	public void setAmwayTokenEnhancer(final TokenEnhancer amwayTokenEnhancer)
	{
		this.amwayTokenEnhancer = amwayTokenEnhancer;
	}

	@Override
	public OAuth2AccessToken createAccessToken(final OAuth2Authentication authentication) throws AuthenticationException
	{
		final OAuth2AccessToken token = super.createAccessToken(authentication);
		LOG.info("enhance the token with terminal_id");
		return ((getAmwayTokenEnhancer() != null) ? getAmwayTokenEnhancer().enhance(token, authentication) : token);
	}



}
