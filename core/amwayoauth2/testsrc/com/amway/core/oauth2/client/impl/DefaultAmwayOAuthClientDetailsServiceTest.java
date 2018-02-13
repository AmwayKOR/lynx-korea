package com.amway.core.oauth2.client.impl;

import com.amway.core.pos.service.AmwayPOSService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.webservicescommons.model.OAuthClientDetailsModel;
import de.hybris.platform.webservicescommons.oauth2.client.ClientDetailsDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@UnitTest
public class DefaultAmwayOAuthClientDetailsServiceTest {

    private static final String OAUTH_CLIENT_ID = "pos-client";
    private static final String EMPTY_ID = null;
    private static final String INVALID_CLIENT_ID = "invalid-client";

    @InjectMocks
    private DefaultAmwayOAuthClientDetailsService amwayOAuthClientDetailsService = new DefaultAmwayOAuthClientDetailsService();

    @Mock
    private AmwayPOSService amwayPOSService;
    @Mock
    private ClientDetailsDao clientDetailsDao;

    private OAuthClientDetailsModel oauthClientDetailsModel;
    private ClientDetails expectedClientDetails;
    private HttpServletRequest httpServletRequest;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        expectedClientDetails   = mock(BaseClientDetails.class);
        oauthClientDetailsModel = mock(OAuthClientDetailsModel.class);
        httpServletRequest      = mock(HttpServletRequest.class);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(httpServletRequest));

        given(oauthClientDetailsModel.getClientId()).willReturn(OAUTH_CLIENT_ID);
        expectedClientDetails = amwayOAuthClientDetailsService.convertClient(oauthClientDetailsModel);
    }

    @Test
    public void testLoadClientByClientId(){

        when(clientDetailsDao.findClientById(OAUTH_CLIENT_ID)).thenReturn(oauthClientDetailsModel);

        ClientDetails actualResult = amwayOAuthClientDetailsService.loadClientByClientId(OAUTH_CLIENT_ID);

        assertNotNull("No OCC client found", actualResult);

        assertEquals(expectedClientDetails, actualResult);

        verify(clientDetailsDao, times(1)).findClientById(OAUTH_CLIENT_ID);

    }

    @Test(expected = NoSuchClientException.class)
    public void testLoadClientByClientIdIfClientIdIsNull(){
        amwayOAuthClientDetailsService.loadClientByClientId(EMPTY_ID);
    }

    @Test(expected = NoSuchClientException.class)
    public void testLoadClientByClientIdIfClientIdIsNotValid(){
        amwayOAuthClientDetailsService.loadClientByClientId(INVALID_CLIENT_ID);
    }

    @Test(expected = ClientRegistrationException.class)
    public void testLoadClientByClientIdIfClientIsDisabled(){

        given(oauthClientDetailsModel.getDisabled()).willReturn(Boolean.TRUE);

        when(clientDetailsDao.findClientById(OAUTH_CLIENT_ID)).thenReturn(oauthClientDetailsModel);

        amwayOAuthClientDetailsService.loadClientByClientId(OAUTH_CLIENT_ID);
    }

}
