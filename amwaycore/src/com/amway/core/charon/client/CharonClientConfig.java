package com.amway.core.charon.client;

/**
 * Created by aiueq92 on 10/3/17.
 */
public class CharonClientConfig extends BaseCharonClientConfig {


    String clientId = null;
    String clientSecret = null;


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

}
