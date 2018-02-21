package com.amway.amwaydms.subscription.services.impl;

import com.amway.amwaydms.common.mock.CommonMockService;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.SubscriptionData;
import com.amway.core.dms.data.SubscriptionDataResponse;
import com.amway.core.dms.data.SubscriptionOption;
import com.amway.core.dms.data.SubscriptionOptionRequestData;
import com.amway.core.model.AmwayAccountModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Created by aiueq92 on 10/10/17.
 */
public class DefaultUpdateSubscriptionServiceTest extends ServicelayerTest
{
    @Resource
    private BaseSiteService baseSiteService;

    @Resource
    private SessionService sessionService;

    @Resource(name = "mockUpdateSubscriptionService")
    private CommonMockService mockUpdateSubscriptionService;

    private SubscriptionOptionRequestData requestData;

    @Before
    public void setUp() throws Exception
    {
        importCsv("/amwaycore/test/common.csv", "windows-1252");
        importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");


        baseSiteService.setCurrentBaseSite("storetemplate", true);

        final AmwayAccountModel acountModel = new AmwayAccountModel();
        acountModel.setCode("3109215040");
        sessionService.getCurrentSession().setAttribute(AmwaycoreConstants.SessionVariables.ACCOUNT, acountModel);

        requestData = new SubscriptionOptionRequestData();
        final SubscriptionData data = new SubscriptionData();
        data.setAboNumber("3109215040");
        final SubscriptionOption option = new SubscriptionOption();
        option.setName("AD");
        option.setValue(true);
        data.setSubscriptionOptionList(Collections.singletonList(option));
        data.setSubscriptionStatus("SUBSCRIBED");

        requestData.setSubscriptionDataList(Collections.singletonList(data));
    }

    @Test
    public void shouldUpdateSubscriptionService()
    {
        final CommonResponseFieldsData response = mockUpdateSubscriptionService.process(requestData);
        org.junit.Assert.assertTrue(response.getReturnCd() == 1);
    }
}
