package com.amway.core.apigateway.service.impl;


import com.amway.core.annotations.AmwayBean;
import com.amway.core.apigateway.model.ApiGatewayResponse;
import com.amway.core.charon.client.ApiGatewayClient;
import com.amway.core.charon.data.ApiGatewayRequestData;
import com.hybris.charon.Charon;
import org.springframework.stereotype.Component;
import com.amway.core.apigateway.AmwayApiGatewayService;

/**
 * Created by aiueq92 on 9/1/17.
 */
@AmwayBean(ext="amwaycore",docs="https://jira.amway.com:8444/display/HC/amwayapigateway")
@Component
public class AmwayApiGatewayServiceImpl implements AmwayApiGatewayService {

    private String url = "";
    private String clientId = "";
    private String clientSecret = "";
    private String grantType = "";
    private static String TOKEN_TYPE = "Bearer";

    /**
     * Gather bearer token
     * @param input
     * @return
     */
    public String getAuthToken(ApiGatewayRequestData input) {

        //Build Charon client with the base url
        ApiGatewayClient client = Charon.from(ApiGatewayClient.class).url(url).build();
        //Get the token access token
        ApiGatewayResponse response =  client.getMasheryAuthToken(getClientId(),getClientSecret(),
                getGrantType(),buildScope(input));

        return TOKEN_TYPE + " " + response.getAccessToken();

    }

    /**
     * Build token scope context
     * @param input
     * @return
     */
    public String buildScope(ApiGatewayRequestData input) {
        String scope = "";
        if (input.getAboNumber() != null) {
            scope = "aboNum="+input.getAboNumber() + " " + "salesPlanAff="+input.getAffiliate() + " " + "partyId="+input.getParty();
        }
        return scope;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }
    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
