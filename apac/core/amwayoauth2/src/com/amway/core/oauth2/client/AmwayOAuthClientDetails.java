package com.amway.core.oauth2.client;

import de.hybris.platform.commerceservices.enums.SalesApplication;
import org.springframework.security.oauth2.provider.ClientDetails;

public interface AmwayOAuthClientDetails extends ClientDetails{

    SalesApplication getSalesApplication();

}
