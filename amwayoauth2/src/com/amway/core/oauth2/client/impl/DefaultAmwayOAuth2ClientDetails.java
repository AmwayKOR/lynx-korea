package com.amway.core.oauth2.client.impl;

import com.amway.core.oauth2.client.AmwayOAuthClientDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

@JsonSerialize(
        include = JsonSerialize.Inclusion.NON_DEFAULT
)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(
        ignoreUnknown = true
)
public class DefaultAmwayOAuth2ClientDetails extends BaseClientDetails implements AmwayOAuthClientDetails {

    @JsonProperty("sales_application")
    @com.fasterxml.jackson.annotation.JsonProperty("sales_application")
    private SalesApplication salesApplication;

    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public SalesApplication getSalesApplication() {
        return salesApplication;
    }

    public void setSalesApplication(SalesApplication salesApplication) {
        this.salesApplication = salesApplication;
    }
}
