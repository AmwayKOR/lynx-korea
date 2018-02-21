package com.amway.core.apigateway.strategy.impl;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.apigateway.strategy.AmwayApiGatewayRequestStrategy;
import com.amway.core.charon.data.ApiGatewayRequestData;

/**
 * Created by aiueq92 on 9/6/17.
 */
@AmwayBean(ext="amwaycore",docs="https://jira.amway.com:8444/display/HC/amwayapigateway")
public class AmwayApiGatewayRequestStrategyImpl implements AmwayApiGatewayRequestStrategy {

    public ApiGatewayRequestData apiSessionRequestData() {
        ApiGatewayRequestData request = new ApiGatewayRequestData();

        // Default is an open token.  For local installs override this stratgey with abo, party and aff session data
        return request;


    }
}
