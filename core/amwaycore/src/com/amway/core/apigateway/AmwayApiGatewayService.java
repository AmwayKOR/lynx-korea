package com.amway.core.apigateway;

import com.amway.core.charon.data.ApiGatewayRequestData;

/**
 * Created by aiueq92 on 9/1/17.
 */
public interface AmwayApiGatewayService {

    public String getAuthToken(ApiGatewayRequestData input);

}
