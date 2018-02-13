package com.amway.core.charon.client;

/**
 * Created by aiueq92 on 10/3/17.
 */
public class BaseCharonClientConfig {


    String oauthUrl = null;
    String oauthRetries = "-1";
    String oauthRetryInterval = "500";
    String oauthTimeout = "10000";
    String clientRetries = "1";
    String clientRetryInterval = "500";
    String clientTimeout = "10000";


    public String getClientRetries() {return clientRetries;}

    public void setClientRetries(String clientRetries) {this.clientRetries = clientRetries;}

    public String getClientRetryInterval() {return clientRetryInterval;}

    public void setClientRetryInterval(String clientRetryInterval) {this.clientRetryInterval = clientRetryInterval;}

    public String getClientTimeout() {return clientTimeout;}

    public void setClientTimeout(String clientTimeout) {this.clientTimeout = clientTimeout;}

    public String getOauthUrl() {
        return oauthUrl;
    }

    public void setOauthUrl(String oauthUrl) {
        this.oauthUrl = oauthUrl;
    }

    public String getOauthRetries() {
        return oauthRetries;
    }

    public void setOauthRetries(String oauthRetries) {
        this.oauthRetries = oauthRetries;
    }

    public String getOauthRetryInterval() {
        return oauthRetryInterval;
    }

    public void setOauthRetryInterval(String oauthRetryInterval) {
        this.oauthRetryInterval = oauthRetryInterval;
    }

    public String getOauthTimeout() {
        return oauthTimeout;
    }

    public void setOauthTimeout(String oauthTimeout) {
        this.oauthTimeout = oauthTimeout;
    }


}
