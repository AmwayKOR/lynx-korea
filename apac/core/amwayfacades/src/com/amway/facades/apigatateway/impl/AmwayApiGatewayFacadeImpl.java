package com.amway.facades.apigatateway.impl;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.charon.data.ApiGatewayRequestData;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.facades.apigatateway.AmwayApiGatewayFacade;
import org.springframework.beans.factory.annotation.Autowired;
import com.amway.core.apigateway.AmwayApiGatewayService;
import com.amway.core.apigateway.strategy.AmwayApiGatewayRequestStrategy;
import de.hybris.platform.servicelayer.session.SessionService;
import java.util.Map;

/**
 * Created by aiueq92 on 9/6/17.
 */
@AmwayBean(ext="amwayfacades",docs="https://jira.amway.com:8444/display/HC/amwayapigateway")
public class AmwayApiGatewayFacadeImpl implements AmwayApiGatewayFacade {


    @Autowired
    private AmwayApiGatewayService apiGatewayService;
    @Autowired
    private AmwayApiGatewayRequestStrategy apiGatewayRequestStrategy;
    @Autowired
    private SessionService sessionService;
    /**
     * Applies session strategy for token requests and then returns api gateway token
     * @return
     */
    public String getAuthToken() {

        ApiGatewayRequestData request = apiGatewayRequestStrategy.apiSessionRequestData();
        String apiToken = apiGatewayService.getAuthToken(request);
        sessionService.getCurrentSession().setAttribute(AmwaycoreConstants.SessionVariables.API_AUTH_TOKEN, apiToken);
        return apiToken;

    }

    public AmwayApiGatewayRequestStrategy getApiGatewayRequestStrategy() {
        return apiGatewayRequestStrategy;
    }

    public void setApiGatewayRequestStrategy(AmwayApiGatewayRequestStrategy apiGatewayRequestStrategy) {
        this.apiGatewayRequestStrategy = apiGatewayRequestStrategy;
    }

    public AmwayApiGatewayService getApiGatewayService() {
        return apiGatewayService;
    }

    public void setApiGatewayService(AmwayApiGatewayService apiGatewayService) {
        this.apiGatewayService = apiGatewayService;
    }
    public SessionService getSessionService()
    {
        return sessionService;
    }

    public void setSessionService(final SessionService sessionService)
    {
        this.sessionService = sessionService;
    }


}
