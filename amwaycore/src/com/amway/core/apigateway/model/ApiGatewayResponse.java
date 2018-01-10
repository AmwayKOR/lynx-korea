package com.amway.core.apigateway.model;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by aiueq92 on 9/1/17.
 */
public class ApiGatewayResponse {

    private String accessToken = null;

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }




}
