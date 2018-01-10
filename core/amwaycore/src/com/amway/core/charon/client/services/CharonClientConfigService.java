package com.amway.core.charon.client.services;

import java.util.Map;

/**
 * Created by aiueq92 on 10/3/17.
 */
public interface CharonClientConfigService {

    public Map<String,String> buildCharonClientConfig(String appId, String scope);
}
