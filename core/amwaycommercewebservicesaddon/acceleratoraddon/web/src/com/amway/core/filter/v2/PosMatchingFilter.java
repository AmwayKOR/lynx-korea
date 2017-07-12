package com.amway.core.v2.filter;

import com.amway.core.service.AmwayAccountCommerceService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.jalo.JaloSession;
import com.amway.core.util.AmwayClientIPHelper;


/**
 * Created by aiueq92 on 4/5/16.
 */
public class PosMatchingFilter extends AbstractUrlMatchingFilter
{
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String ROLE_CUSTOMERGROUP = "ROLE_CUSTOMERGROUP";
    public static final String ROLE_CUSTOMERMANAGERGROUP = "ROLE_CUSTOMERMANAGERGROUP";
    public static final String ROLE_TRUSTED_CLIENT = "ROLE_TRUSTED_CLIENT";
    private static final String CURRENT_USER = "current";
    private String regexp;
    @Resource(name = "amwayAccountCommerceService")
    private AmwayAccountCommerceService accountCommerceService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException
    {
        final Authentication auth = getAuth();

        final String accountUID = getValue(request, regexp);

        //this filter comes into play only for POS
        JaloSession.getCurrentSession().setAttribute("currentChannel", SalesApplication.POS);
        JaloSession.getCurrentSession().setAttribute("clientIpAddress", AmwayClientIPHelper.getClientIpAddr(request));

        if (accountUID != null)
        {
            if (hasRole(ROLE_TRUSTED_CLIENT, auth) || hasRole(ROLE_CUSTOMERMANAGERGROUP, auth))
            {
                setCurrentAccount(accountUID);
            }
            else
            {
                throw new AccessDeniedException("Access is denied");
            }
        }

        filterChain.doFilter(request, response);
    }

    protected Authentication getAuth()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    protected String getRegexp()
    {
        return regexp;
    }

    @Required
    public void setRegexp(final String regexp)
    {
        this.regexp = regexp;
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

    protected void setCurrentAccount(final String uid)
    {
        accountCommerceService.setCurrentAccount(uid);
    }
}
