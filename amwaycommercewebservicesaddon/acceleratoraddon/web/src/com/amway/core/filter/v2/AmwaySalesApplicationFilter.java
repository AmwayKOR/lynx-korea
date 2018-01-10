package com.amway.core.filter.v2;

import com.amway.core.oauth2.client.AmwayOAuthClientDetails;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.jalo.JaloSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.amway.core.controllers.AmwaycommercewebservicesaddonControllerConstants.Role.TRUSTED_CLIENT;


public class AmwaySalesApplicationFilter extends OncePerRequestFilter {

    @Resource(name="oauthClientDetails")
    ClientDetailsService clientDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        Authentication client = SecurityContextHolder.getContext().getAuthentication();
        String clientId = getClientId(client);

        if(StringUtils.isNotBlank(clientId)){

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if(clientDetails != null && clientDetails instanceof AmwayOAuthClientDetails){
                SalesApplication salesApplication = ((AmwayOAuthClientDetails)clientDetails).getSalesApplication();
                if(salesApplication != null){
                    JaloSession.getCurrentSession().setAttribute("currentChannel", salesApplication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    protected String getClientId(Authentication client) {
        //Authentication client = SecurityContextHolder.getContext().getAuthentication();
        if(!client.isAuthenticated()) {
            throw new InsufficientAuthenticationException("The client is not authenticated.");
        } else if(client instanceof OAuth2Authentication) {
            return ((OAuth2Authentication)client).getOAuth2Request().getClientId();
        }
        return null;
    }

    protected boolean hasRole(final String role, final Authentication auth)
    {
        for (final GrantedAuthority ga : auth.getAuthorities())
        {
            if (ga.getAuthority().equals(role))
            {
                return true;
            }
        }
        return false;
    }
}
