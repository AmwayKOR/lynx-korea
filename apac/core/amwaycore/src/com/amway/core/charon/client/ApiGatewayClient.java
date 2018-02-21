package com.amway.core.charon.client;

import com.hybris.charon.RawResponse;
import com.hybris.charon.annotations.Control;
import com.hybris.charon.annotations.Http;

import javax.ws.rs.*;

import com.amway.core.apigateway.model.ApiGatewayResponse;

/**
 */
@Http()
@Control(retries = "1", retriesInterval = "200", timeout = "5000")
public interface ApiGatewayClient {

    @POST
    @Path("/oauth2/v1/token")
    ApiGatewayResponse getMasheryAuthToken(@FormParam(value = "client_id") String client,
                                        @FormParam(value = "client_secret") String clientSecret,
                                        @FormParam(value = "grant_type") final String grantType,
                                        @FormParam(value = "scope") final String scope);

}