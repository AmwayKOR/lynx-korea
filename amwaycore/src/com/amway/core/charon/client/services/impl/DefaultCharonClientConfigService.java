package com.amway.core.charon.client.services.impl;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.charon.client.CharonClientConfig;
import com.amway.core.charon.client.services.CharonClientConfigService;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by aiueq92 on 10/3/17.
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/DefaultCharonClientConfigService")
public class DefaultCharonClientConfigService implements CharonClientConfigService {

    private Map<String, CharonClientConfig> amwayCharonClientRegistry;

    public Map<String,String> buildCharonClientConfig(String appId, String oauthScope) {
        HashMap<String,String> config = new HashMap<>();

        CharonClientConfig clientConfig = amwayCharonClientRegistry.get(appId);
        config.put("oauth.url",clientConfig.getOauthUrl());
        config.put("oauth.timeout",clientConfig.getOauthTimeout());
        config.put("oauth.retries",clientConfig.getOauthRetries());
        config.put("oauth.retriesInterval",clientConfig.getOauthRetryInterval());

        config.put("oauth.clientId",clientConfig.getClientId());
        config.put("oauth.clientSecret",clientConfig.getClientSecret());
        config.put("oauth.scope",oauthScope);

        config.put("timeout",clientConfig.getClientTimeout());
        config.put("retries",clientConfig.getClientRetries());
        config.put("retriesInterval",clientConfig.getClientRetryInterval());

        return config;
    }

    public Map<String, CharonClientConfig> getAmwayCharonClientRegistry() {
        return amwayCharonClientRegistry;
    }

    public void setAmwayCharonClientRegistry(Map<String, CharonClientConfig> amwayCharonClientRegistry) {
        this.amwayCharonClientRegistry = amwayCharonClientRegistry;
    }
}
