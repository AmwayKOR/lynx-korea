package com.amway.core.oauth2.client.impl;

import com.amway.core.model.AmwayTerminalModel;
import com.amway.core.oauth2.constants.Amwayoauth2Constants;
import com.amway.core.pos.service.AmwayPOSService;
import com.google.common.collect.Sets;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.webservicescommons.model.OAuthClientDetailsModel;
import de.hybris.platform.webservicescommons.oauth2.client.ClientDetailsDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by surajverma on 19-09-2017.
 */

public class DefaultAmwayOAuthClientDetailsService implements ClientDetailsService {

    private AmwayPOSService amwayPOSService;
    private ClientDetailsDao clientDetailsDao;

    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            return loadClient(clientId);
        } catch (ClientRegistrationException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new ClientRegistrationException("loadClientByClientId failed", var4);
        }
    }

    protected ClientDetails convertClient(OAuthClientDetailsModel model) {
        DefaultAmwayOAuth2ClientDetails result = new DefaultAmwayOAuth2ClientDetails();
        result.setAccessTokenValiditySeconds(model.getAccessTokenValiditySeconds());
        result.setAdditionalInformation(Collections.emptyMap());
        List authorities = (List)emptyIfNull(model.getAuthorities()).stream().map((s) -> {
            return new SimpleGrantedAuthority(s);
        }).collect(Collectors.toList());
        result.setAuthorities(authorities);
        result.setAuthorizedGrantTypes(emptyIfNull(model.getAuthorizedGrantTypes()));
        result.setAutoApproveScopes(emptyIfNull(model.getAutoApprove()));
        result.setClientId(model.getClientId());
        result.setClientSecret(model.getClientSecret());
        result.setRefreshTokenValiditySeconds(model.getRefreshTokenValiditySeconds());
        result.setRegisteredRedirectUri(emptyIfNull(model.getRegisteredRedirectUri()));
        result.setResourceIds(emptyIfNull(model.getResourceIds()));
        result.setScope(emptyIfNull(model.getScope()));
        result.setSalesApplication(model.getChannel());

        if(result.getSalesApplication() != null && result.getSalesApplication() == SalesApplication.POS){
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            AmwayTerminalModel terminalModel = null;
            String terminalMacAddress = attr.getRequest().getParameter(Amwayoauth2Constants.POS.PARAM_MAC_ADDRESS);
            if (StringUtils.isNotEmpty(terminalMacAddress))
            {
                    terminalModel = getAmwayPOSService().getPOSTerminalByMacAddress(terminalMacAddress);
            }
            if(terminalModel == null){
                result.setScope(Sets.newHashSet(Amwayoauth2Constants.POS.SCOPE_REPORTS));
            }
        }

        return result;
    }

    private ClientDetails loadClient(String clientId) {
        OAuthClientDetailsModel model = getClientDetailsDao().findClientById(clientId);
        if(model == null) {
            throw new NoSuchClientException("Unknown client " + clientId);
        } else if(model.getDisabled() != null && model.getDisabled().booleanValue()) {
            throw new ClientRegistrationException(String.format("Authentication for clientId:\'%s\' is disabled", new Object[]{clientId}));
        } else {
            return convertClient(model);
        }
    }

    protected <T> Set<T> emptyIfNull(Set<T> collection) {
        return collection == null?Collections.emptySet():collection;
    }


    public AmwayPOSService getAmwayPOSService() {
        return amwayPOSService;
    }

    public void setAmwayPOSService(AmwayPOSService amwayPOSService) {
        this.amwayPOSService = amwayPOSService;
    }

    public ClientDetailsDao getClientDetailsDao() {
        return clientDetailsDao;
    }

    public void setClientDetailsDao(ClientDetailsDao clientDetailsDao) {
        this.clientDetailsDao = clientDetailsDao;
    }
}
